package cn.wizzer.auto.socket;

import com.xiaoleilu.hutool.date.DateTime;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.exception.LengthOverflowException;
import org.tio.core.intf.AioHandler;
import org.tio.core.intf.Packet;
import org.tio.core.utils.ByteBufferUtils;
import org.tio.utils.json.Json;

import java.nio.ByteBuffer;

/**
 * Created by Wizzer on 2017/9/18.
 */
@IocBean
public abstract class MyAbsAioHandler implements AioHandler {
    private final static Log log = Logs.get();

    /**
     * 编码：把业务消息包编码为可以发送的ByteBuffer
     * 总的消息结构：消息头 + 消息体
     * 消息头结构：    4个字节，存储消息体的长度
     * 消息体结构：   对象的json串的byte[]
     */
    @Override
    public ByteBuffer encode(Packet packet, GroupContext groupContext, ChannelContext channelContext) {
        SendPacket helloPacket = (SendPacket) packet;
        byte[] body = helloPacket.getBody();
        int bodyLen = 0;
        if (body != null) {
            bodyLen = body.length;
        }

        //bytebuffer的总长度是 = 消息头的长度 + 消息体的长度
        int allLen = MyPacket.HEADER_LENGHT + bodyLen;
        //创建一个新的bytebuffer
        ByteBuffer buffer = ByteBuffer.allocate(allLen);
        //设置字节序
        buffer.order(groupContext.getByteOrder());

        //写入消息头----消息头的内容就是消息体的长度
        buffer.putInt(bodyLen);

        //写入消息体
        if (body != null) {
            buffer.put(body);
        }
        return buffer;
    }

    /**
     * 解码：把接收到的ByteBuffer，解码成应用可以识别的业务消息包
     * 总的消息结构：消息头 + 消息体
     * 消息头结构：    4个字节，存储消息体的长度
     * 消息体结构：   对象的json串的byte[]
     */
    @Override
    public MyPacket decode(ByteBuffer buffer, ChannelContext channelContext) throws AioDecodeException {
        int readableLength = buffer.limit() - buffer.position();
        //收到的数据组不了业务包，则返回null以告诉框架数据不够
        if (readableLength < MyPacket.HEADER_LENGHT) {
            return null;
        }
        int startPosition = buffer.position();
        int endPosition = -1;
        try {
            endPosition = ByteBufferUtils.lineEnd(buffer);
        } catch (LengthOverflowException e) {
            e.printStackTrace();
        }
        if (endPosition < startPosition) {
            return null;
        } else {//组包成功
            MyPacket imPacket = new MyPacket();
            imPacket.setIp(channelContext.getClientNode().getIp());
            imPacket.setTime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
            //$$
            byte[] headBytes = new byte[2];
            System.arraycopy(buffer.array(), startPosition, headBytes, 0, headBytes.length);
            imPacket.setHead(new String(headBytes));
            //数据包标识符
            byte[] flagBytes = new byte[1];
            System.arraycopy(buffer.array(), startPosition + 2, flagBytes, 0, flagBytes.length);
            imPacket.setFlag(new String(flagBytes));
            //数据长度
            byte[] lenBytes = new byte[3];
            System.arraycopy(buffer.array(), startPosition + 3, lenBytes, 0, lenBytes.length);
            imPacket.setLength(Integer.valueOf(new String(lenBytes)));
            //IMEI
            byte[] imeiBytes = new byte[15];
            System.arraycopy(buffer.array(), startPosition + 7, imeiBytes, 0, imeiBytes.length);
            imPacket.setImei((new String(imeiBytes)).toLowerCase().replaceAll("f", ""));
            //指令类型 AAA-自动事件报告  AFF-删除缓存区GPRS事件-多一个缓存大小
            byte[] typeBytes = new byte[3];
            System.arraycopy(buffer.array(), startPosition + 23, typeBytes, 0, typeBytes.length);
            imPacket.setType(new String(typeBytes));
            //事件代码 35-定时追踪
            byte[] codeBytes = new byte[2];
            System.arraycopy(buffer.array(), startPosition + 27, codeBytes, 0, codeBytes.length);
            imPacket.setCode(new String(codeBytes));
            //根据指令类型不同 body有所不同
            byte[] bodyBytes = new byte[imPacket.getLength() - 30];
            System.arraycopy(buffer.array(), startPosition + 30, bodyBytes, 0, bodyBytes.length);
            imPacket.setBody(bodyBytes);

            log.debug("imPacket::"+imPacket.getImei()+"  "+imPacket.getType()+"\r\n" + Json.toJson(imPacket));
            return imPacket;
        }
    }
}