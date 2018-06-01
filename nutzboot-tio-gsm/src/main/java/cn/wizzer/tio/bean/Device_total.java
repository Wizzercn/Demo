package cn.wizzer.tio.bean;

import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * 设备数据统计表
 * Created by JF on 2018/5/14.
 */
@Table("device_total")
public class Device_total implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("设备ID")
    @ColDefine(type = ColType.INT, width = 32)
    private long equipID;

    @Column
    @Comment("日期")
    @ColDefine(type = ColType.CHAR, width = 8)
    private String dateTime;

    @Column
    @Comment("最大序列号")
    @ColDefine(type = ColType.INT, width = 32)
    private long maxSeq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getEquipID() {
        return equipID;
    }

    public void setEquipID(long equipID) {
        this.equipID = equipID;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public long getMaxSeq() {
        return maxSeq;
    }

    public void setMaxSeq(long maxSeq) {
        this.maxSeq = maxSeq;
    }
}
