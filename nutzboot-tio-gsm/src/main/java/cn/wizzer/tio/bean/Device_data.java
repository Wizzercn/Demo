package cn.wizzer.tio.bean;

import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * 设备数据表
 * Created by wizzer on 2018/5/14.
 */
@Table("device_data")
@TableIndexes({@Index(name = "INDEX_DATA", fields = {"equipID","timestamp"}, unique = true)})
public class Device_data implements Serializable {
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
    private Long equipID;

    @Column
    @Comment("设备号")
    @ColDefine(type = ColType.INT, width = 32)
    private Long equipmentID;

    @Column
    @Comment("源LAC号码")
    @ColDefine(type = ColType.INT, width = 4)
    private Integer sourceLac;

    @Column
    @Comment("临时卡号")
    @ColDefine(type = ColType.INT, width = 32)
    private Long TMSI;

    @Column
    @Comment("机身码（BCD码）")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String IMEI;

    @Column
    @Comment("卡号（BCD码）")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String IMSI;

    @Column
    @Comment("时间戳")
    @ColDefine(type = ColType.INT, width = 32)
    private Long timestamp;

    @Column
    @Comment("序号")
    @ColDefine(type = ColType.INT, width = 32)
    private Long sequenceNumber;

    @Column
    @Comment("0：成功 1：失败")
    @ColDefine(type = ColType.INT, width = 32)
    private Integer result;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getEquipID() {
        return equipID;
    }

    public void setEquipID(Long equipID) {
        this.equipID = equipID;
    }

    public Long getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(Long equipmentID) {
        this.equipmentID = equipmentID;
    }

    public Integer getSourceLac() {
        return sourceLac;
    }

    public void setSourceLac(Integer sourceLac) {
        this.sourceLac = sourceLac;
    }

    public Long getTMSI() {
        return TMSI;
    }

    public void setTMSI(Long TMSI) {
        this.TMSI = TMSI;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getIMSI() {
        return IMSI;
    }

    public void setIMSI(String IMSI) {
        this.IMSI = IMSI;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

}
