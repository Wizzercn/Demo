package cn.wizzer.auto.socket;

import org.nutz.ioc.loader.annotation.IocBean;
import org.tio.core.intf.Packet;

/**
 * Created by Administrator on 2017/12/21.
 */
@IocBean
public class SendPacket extends Packet {
    private byte[] body;

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
