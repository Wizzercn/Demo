package cn.wizzer.tio.packet;

import org.tio.core.intf.Packet;

import java.io.Serializable;

/**
 * Created by JF on 2018/5/14.
 */
public class MyPacket extends Packet implements Serializable {
    public static final int HEADER_LENGHT = 3;//消息头的长度

    private int length;
    private int msgType;
    private byte[] data;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
