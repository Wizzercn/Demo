package cn.wizzer.tio.intf;

import cn.wizzer.tio.packet.MyPacket;
import org.tio.core.ChannelContext;

/**
 * Created by wizzer on 2018/5/14.
 */
public interface MyBsHandlerIntf {
    Object handler(MyPacket packet, ChannelContext channelContext) throws Exception;
}
