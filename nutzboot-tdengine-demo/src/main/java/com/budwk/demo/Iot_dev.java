package com.budwk.demo;

import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 注意 TDengine 表及字段名都为小写字母
 */
@Table("iot_dev")
public class Iot_dev implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column
    @Comment("ID")
    @ColDefine(type = ColType.TIMESTAMP)
    private Date ts;

    @Column("devid") //字段名都为小写字母
    @Comment("设备ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String devId;

    @Column("devtype") //字段名都为小写字母
    @Comment("设备类型")
    @ColDefine(type = ColType.BINARY, width = 32)
    private String devType;

    @Column
    @Comment("状态")
    @ColDefine(type = ColType.BOOLEAN)
    private Boolean status;

    @Column
    @Comment("读数1")
    @ColDefine(type = ColType.DOUBLE)
    private Double val1;

    @Column
    @Comment("读数2")
    @ColDefine(type = ColType.INT)
    private Integer val2;

    @Column
    @Comment("读数3")
    @ColDefine(type = ColType.INT,width = 3)
    private Integer val3;

    @Column
    @Comment("读数4")
    @ColDefine(type = ColType.INT,width = 2)
    private Integer val4;

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Double getVal1() {
        return val1;
    }

    public void setVal1(Double val1) {
        this.val1 = val1;
    }

    public Integer getVal2() {
        return val2;
    }

    public void setVal2(Integer val2) {
        this.val2 = val2;
    }

    public Integer getVal3() {
        return val3;
    }

    public void setVal3(Integer val3) {
        this.val3 = val3;
    }

    public Integer getVal4() {
        return val4;
    }

    public void setVal4(Integer val4) {
        this.val4 = val4;
    }
}
