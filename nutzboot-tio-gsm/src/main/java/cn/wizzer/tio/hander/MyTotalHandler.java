package cn.wizzer.tio.hander;

import cn.wizzer.tio.bean.Device_total;
import cn.wizzer.tio.common.utils.ByteUtil;
import cn.wizzer.tio.intf.MyBsHandlerIntf;
import cn.wizzer.tio.packet.MyPacket;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.tio.core.ChannelContext;
import org.tio.core.exception.LengthOverflowException;
import org.tio.core.utils.ByteBufferUtils;

import java.nio.ByteBuffer;

/**
 * 心跳
 * Created by wizzer on 2018/5/14.
 */
@IocBean
public class MyTotalHandler implements MyBsHandlerIntf {
    private final static Log log = Logs.get();
    @Inject
    private Dao dao;

    @Override
    public Object handler(MyPacket packet, ChannelContext channelContext) throws Exception {
        //从通道获取设备ID
        long equipID = Long.valueOf(channelContext.getToken());
        if (equipID > 0) {
            byte[] bytes = packet.getData();
            for (int i = 0; i < bytes.length; i = i + 12) {
                byte[] temp1 = new byte[]{bytes[i], bytes[i + 1], bytes[i + 2], bytes[i + 3], bytes[i + 4], bytes[i + 5], bytes[i + 6], bytes[i + 7]};
                byte[] temp2 = new byte[]{bytes[i + 8], bytes[i + 9], bytes[i + 10], bytes[i + 11]};
                String date = new String(temp1);
                int total = getInt(temp2, 0, 4);
                log.debug("date::" + date + " total::" + total);
                if (total <= 0)
                    break;
                int num = dao.count(Device_total.class, Cnd.where("equipID", "=", equipID).and("dateTime", "=", date));
                //若存在则更新，循环体性能可以优化 todo
                if (num > 0) {
                    dao.update(Device_total.class, Chain.make("maxSeq", total), Cnd.where("equipID", "=", equipID).and("dateTime", "=", date));
                } else {
                    Device_total deviceTotal = new Device_total();
                    deviceTotal.setEquipID(equipID);
                    deviceTotal.setDateTime(date);
                    deviceTotal.setMaxSeq(total);
                    dao.insert(deviceTotal);
                }

            }
        }
        return null;
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
