package cn.wizzer.tio.hander;

import cn.wizzer.tio.bean.Device_data;
import cn.wizzer.tio.intf.MyBsHandlerIntf;
import cn.wizzer.tio.packet.MyPacket;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.tio.core.ChannelContext;
import org.tio.core.utils.ByteBufferUtils;

import java.nio.ByteBuffer;

/**
 * 数据
 * Created by wizzer on 2018/5/14.
 */
@IocBean
public class MyDatalHandler implements MyBsHandlerIntf {
    private final static Log log = Logs.get();
    @Inject
    private Dao dao;

    @Override
    public Object handler(MyPacket packet, ChannelContext channelContext) throws Exception {
        ByteBuffer buffer = ByteBuffer.wrap(packet.getData());
        long equipID = ByteBufferUtils.readUB4WithBigEdian(buffer);
        int IMSINum = buffer.get();
        for (int i = 0; i < IMSINum * 38; i += 38) {
            int Result = getInt(ByteBufferUtils.readBytes(buffer, 4), 0, 4);
            long SequenceNumber = getInt(ByteBufferUtils.readBytes(buffer, 4), 0, 4);
            long Timestamp = getInt(ByteBufferUtils.readBytes(buffer, 4), 0, 4);
            String IMSI = getBCD(ByteBufferUtils.readBytes(buffer, 8));
            String IMEI = getBCD(ByteBufferUtils.readBytes(buffer, 8));
            long TMSI = getInt(ByteBufferUtils.readBytes(buffer, 4), 0, 4);
            int SourceLac = getInt(ByteBufferUtils.readBytes(buffer, 2), 0, 2);
            long EquipmentID = ByteBufferUtils.readUB4WithBigEdian(buffer);
            //Timestamp + equipID 复合主键,重复则插入失败
            try {
                Device_data deviceData = new Device_data();
                deviceData.setEquipID(equipID);
                deviceData.setResult(Result);
                deviceData.setSequenceNumber(SequenceNumber);
                deviceData.setTimestamp(Timestamp);
                deviceData.setIMSI(IMSI);
                deviceData.setIMEI(IMEI);
                deviceData.setTMSI(TMSI);
                deviceData.setSourceLac(SourceLac);
                deviceData.setEquipmentID(EquipmentID);
                dao.insert(deviceData);
            } catch (Exception e) {

            }
        }
        return null;
    }

    private static String getBCD(byte[] b) {
		//这个算法有问题，结果总是差了一点点
		//todo
        byte[] bytes = new byte[8];
        for (int i = 0; i < b.length; i++) {
            byte s = b[i];
            byte high4 = (byte) (s & 240);
            byte low4 = (byte) (s & 15);
            byte j = (byte) ((low4 << 4) + (high4 >> 4));
            bytes[i] = j;
        }
        return Lang.fixedHexString(bytes).substring(1);
    }

    private static int getInt(byte[] b, int start, int len) {
        int sum = 0;
        int end = start + len;
        for (int i = start; i < end; i++) {
            int n = ((int) b[i]) & 0xff;
            n <<= (--len) * 8;
            sum += n;
        }
        return sum;
    }
}
