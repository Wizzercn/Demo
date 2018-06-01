package cn.wizzer.tio.hander;

import cn.hutool.core.io.BufferUtil;
import cn.wizzer.tio.bean.Device_info;
import cn.wizzer.tio.intf.MyBsHandlerIntf;
import cn.wizzer.tio.packet.MyPacket;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.core.utils.ByteBufferUtils;

import java.nio.ByteBuffer;

import static cn.wizzer.tio.common.Constants.MSG_GSM_START_ACK;

/**
 * 初始化连接
 * Created by JF on 2018/5/14.
 */
@IocBean
public class MyConnectHandler implements MyBsHandlerIntf {
    private final static Log log = Logs.get();
    @Inject
    private Dao dao;

    @Override
    public Object handler(MyPacket packet, ChannelContext channelContext) throws Exception {
        ByteBuffer buffer = ByteBuffer.wrap(packet.getData());
        long equipID = ByteBufferUtils.readUB4WithBigEdian(buffer);
        int num = dao.count(Device_info.class, Cnd.where("equipID", "=", equipID));
        //判断设备ID是否存在，存在则返回0
        if (num > 0) {
            MyPacket sendPacket = new MyPacket();
            sendPacket.setLength(4);//2+1+1
            sendPacket.setMsgType(MSG_GSM_START_ACK);
            sendPacket.setData("0".getBytes());
            //绑定设备ID到通道
            Aio.bindToken(channelContext, "" + equipID);
            log.info("has bind equipID:::" + equipID);
            Aio.send(channelContext, sendPacket);
        } else {//设备号不存在
            MyPacket sendPacket = new MyPacket();
            sendPacket.setLength(4);//2+1+1
            sendPacket.setMsgType(MSG_GSM_START_ACK);
            sendPacket.setData("1".getBytes());
            Aio.send(channelContext, sendPacket);
        }
        return null;
    }

}
