package cn.wizzer.tio.mvc;

import cn.wizzer.tio.common.MsgType;
import cn.wizzer.tio.packet.MyPacket;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.core.utils.ByteBufferUtils;
import org.tio.server.ServerGroupContext;
import org.tio.utils.lock.SetWithLock;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Set;

/**
 * HTTP API 触发获取数据事件
 * Created by JF on 2018/5/15.
 */
@IocBean
@At("/api")
public class MyAction {
    private final static Log log = Logs.get();
    @Inject("refer:$ioc")
    private Ioc ioc;

    @At("/total")
    @Ok("json")
    public Object total(@Param("equipID") String equipID) {
        NutMap map = NutMap.NEW();
        SetWithLock<ChannelContext> setWithLock = Aio.getChannelContextsByToken(ioc.get(ServerGroupContext.class), equipID);
        if (null != setWithLock) {
            Set<ChannelContext> contexts = setWithLock.getObj();
            if (contexts != null) {
                for (ChannelContext context : contexts) {
                    MyPacket packet = new MyPacket();
                    packet.setLength(3);
                    packet.setMsgType(MsgType.COUNTREQ.value());
                    Aio.send(context, packet);
                }
            }
            map.addv("code", 0);
            map.addv("msg", "发送成功");
        } else {
            map.addv("code", -1);
            map.addv("msg", "发送失败");
        }
        return map;
    }

    @At("/data")
    @Ok("json")
    public Object data(@Param("equipID") long equipID, @Param("dateTime") String dateTime, @Param("startNum") long startNum, @Param("endNum") long endNum) {
        NutMap map = NutMap.NEW();
        try {
            SetWithLock<ChannelContext> setWithLock = Aio.getChannelContextsByToken(ioc.get(ServerGroupContext.class), "" + equipID);
            if (null != setWithLock) {
                Set<ChannelContext> contexts = setWithLock.getObj();
                if (contexts != null) {
                    for (ChannelContext context : contexts) {
                        MyPacket packet = new MyPacket();
                        packet.setLength(23);
                        packet.setMsgType(MsgType.DATAREQ.value());
                        ByteBuffer buffer = ByteBuffer.allocate(20);
                        buffer.order(ByteOrder.BIG_ENDIAN);
                        ByteBufferUtils.writeUB4WithBigEdian(buffer, equipID);
                        buffer.put(dateTime.getBytes());
                        ByteBufferUtils.writeUB4WithBigEdian(buffer, startNum);
                        ByteBufferUtils.writeUB4WithBigEdian(buffer, endNum);
                        buffer.position(0);
                        packet.setData(ByteBufferUtils.readBytes(buffer, 20));
                        Aio.send(context, packet);
                    }
                }
                map.addv("code", 0);
                map.addv("msg", "发送成功");
            } else {
                map.addv("code", -1);
                map.addv("msg", "发送失败");
            }
        }catch (Exception e){
            map.addv("code", -1);
            map.addv("msg", "发送失败");
            log.error(e.getMessage(),e);
        }
        return map;
    }
}
