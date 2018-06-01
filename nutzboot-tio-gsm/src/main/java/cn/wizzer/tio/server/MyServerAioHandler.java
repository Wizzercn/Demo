package cn.wizzer.tio.server;

import cn.wizzer.tio.common.MsgType;
import cn.wizzer.tio.hander.MyConnectHandler;
import cn.wizzer.tio.hander.MyDatalHandler;
import cn.wizzer.tio.hander.MyPingHandler;
import cn.wizzer.tio.hander.MyTotalHandler;
import cn.wizzer.tio.intf.MyBsHandlerIntf;
import cn.wizzer.tio.packet.MyPacket;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JF on 2018/5/14.
 */
@IocBean(create = "init")
public class MyServerAioHandler extends MyServerAbsAioHandler implements ServerAioHandler {
    private final static Log log = Logs.get();

    @Inject("refer:$ioc")
    private Ioc ioc;
    private static Map<MsgType, MyBsHandlerIntf> handlerMap = new HashMap<>();

    public void init() {
        handlerMap.put(MsgType.CONNECT, ioc.get(MyConnectHandler.class));
        handlerMap.put(MsgType.PING, ioc.get(MyPingHandler.class));
        handlerMap.put(MsgType.COUNTACK, ioc.get(MyTotalHandler.class));
        handlerMap.put(MsgType.DATAACK, ioc.get(MyDatalHandler.class));
    }

    @Override
    public void handler(Packet packet, ChannelContext channelContext) throws Exception {
        MyPacket myPacket = (MyPacket) packet;
        MyBsHandlerIntf mqttBsHandler = handlerMap.get(MsgType.valueOf(myPacket.getMsgType()));
        if (mqttBsHandler == null) {
            log.errorf("%s, 找不到处理类，type:%s", channelContext, myPacket.getMsgType());
            return;
        }
        mqttBsHandler.handler(myPacket, channelContext);
    }
}
