package cn.wizzer.tio.server;

import cn.wizzer.tio.common.utils.ByteUtil;
import cn.wizzer.tio.packet.MyPacket;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.AioHandler;
import org.tio.core.intf.Packet;
import org.tio.core.utils.ByteBufferUtils;

import java.nio.ByteBuffer;

/**
 * Created by wizzer on 2018/5/14.
 */
@IocBean
public abstract class MyServerAbsAioHandler implements AioHandler {
    private final static Log log = Logs.get();

    @Override
    public ByteBuffer encode(Packet packet, GroupContext groupContext, ChannelContext channelContext) {
        MyPacket sendPacket = (MyPacket) packet;
        ByteBuffer buffer = ByteBuffer.allocate(sendPacket.getLength());
        //设置字节序
        buffer.order(groupContext.getByteOrder());
        try {
            ByteBufferUtils.writeUB2WithBigEdian(buffer,sendPacket.getLength());
            buffer.put((byte) sendPacket.getMsgType());
            buffer.put(sendPacket.getData());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return buffer;
    }

    @Override
    public MyPacket decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext) throws AioDecodeException {
        //收到的数据组不了业务包，则返回null以告诉框架数据不够
        if (readableLength < MyPacket.HEADER_LENGHT) {
            return null;
        }
        int bodyLength = ByteBufferUtils.readUB2WithBigEdian(buffer);//通信包长度，包含本字段
        if (bodyLength < 0) {
            throw new AioDecodeException("bodyLength [" + bodyLength + "] is not right, remote:" + channelContext.getClientNode());
        }
        int size = readableLength - bodyLength;
        if (size < 0) // 不够消息体长度(剩下的buffe组不了消息体)
        {
            return null;
        } else {//组包成功
            MyPacket myPacket = new MyPacket();
            myPacket.setLength(bodyLength);
            myPacket.setMsgType(ByteBufferUtils.readUB1(buffer));
            myPacket.setData(ByteBufferUtils.readBytes(buffer, bodyLength - MyPacket.HEADER_LENGHT));
            return myPacket;
        }
    }
}
