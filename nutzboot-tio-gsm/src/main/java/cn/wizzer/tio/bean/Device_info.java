package cn.wizzer.tio.bean;

import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by JF on 2018/5/14.
 */
@Table("device_info")
public class Device_info implements Serializable {
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
    @Comment("设备名称")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String equipName;

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

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }
}
