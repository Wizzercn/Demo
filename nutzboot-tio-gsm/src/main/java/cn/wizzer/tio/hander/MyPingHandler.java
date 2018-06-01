package cn.wizzer.tio.hander;

import cn.wizzer.tio.common.utils.ByteUtil;
import cn.wizzer.tio.intf.MyBsHandlerIntf;
import cn.wizzer.tio.packet.MyPacket;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.core.utils.ByteBufferUtils;

import java.nio.ByteBuffer;

import static cn.wizzer.tio.common.Constants.MSG_GSM_HEARTBEAT_ACK;

/**
 * 心跳
 * Created by JF on 2018/5/14.
 */
@IocBean
public class MyPingHandler implements MyBsHandlerIntf {
    private final static Log log = Logs.get();

    @Override
    public Object handler(MyPacket packet, ChannelContext channelContext) throws Exception {
        ByteBuffer buffer = ByteBuffer.wrap(packet.getData());
        long equipID = ByteBufferUtils.readUB4WithBigEdian(buffer);
        MyPacket sendPacket = new MyPacket();
        byte[] data = ByteUtil.getBytes(equipID);
        sendPacket.setLength(3 + data.length);//2+1+n
        sendPacket.setMsgType(MSG_GSM_HEARTBEAT_ACK);
        sendPacket.setData(data);
        try {
            Aio.send(channelContext, sendPacket);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }

}
