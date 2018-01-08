package cn.wizzer.auto.models;

import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/15.
 */
@Table("device_data_old_${m}")
public class Device_data_old implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("设备ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String deviceId;

    @Column
    @Comment("设备IMEI")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String imei;

    @Column
    @Comment("设备IP")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String ip;

    @Column
    @Comment("经度")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String longitude;

    @Column
    @Comment("纬度")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String latitude;

    @Column
    @Comment("设备时间")
    @ColDefine(type = ColType.INT)
    private Integer device_time;

    @Column
    @Comment("定位状态")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String gps_status;

    @Column
    @Comment("卫星个数")
    @ColDefine(type = ColType.INT)
    private Integer gps_num;

    @Column
    @Comment("GSM信号强度")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String gsm_status;

    @Column
    @Comment("速度")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String speed;

    @Column
    @Comment("方向")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String direction;

    @Column
    @Comment("水平定位精度")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String hpa;

    @Column
    @Comment("海拔高度")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String height;

    @Column
    @Comment("里程数")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String mileage;

    @Column
    @Comment("运行时间")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String runtimes;

    @Column
    @Comment("基站信息")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String basestation;

    @Column
    @Comment("输入输出口状态")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String iostatus;

    @Column
    @Comment("模拟量输入口值")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String analog;

    @Column
    @Comment("数据同步状态")
    @ColDefine(type = ColType.INT)
    private Integer dataState;  // 0未同步 ，1是已经同步

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getDevice_time() {
        return device_time;
    }

    public void setDevice_time(Integer device_time) {
        this.device_time = device_time;
    }

    public String getGps_status() {
        return gps_status;
    }

    public void setGps_status(String gps_status) {
        this.gps_status = gps_status;
    }

    public Integer getGps_num() {
        return gps_num;
    }

    public void setGps_num(Integer gps_num) {
        this.gps_num = gps_num;
    }

    public String getGsm_status() {
        return gsm_status;
    }

    public void setGsm_status(String gsm_status) {
        this.gsm_status = gsm_status;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getHpa() {
        return hpa;
    }

    public void setHpa(String hpa) {
        this.hpa = hpa;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getRuntimes() {
        return runtimes;
    }

    public void setRuntimes(String runtimes) {
        this.runtimes = runtimes;
    }

    public String getBasestation() {
        return basestation;
    }

    public void setBasestation(String basestation) {
        this.basestation = basestation;
    }

    public String getIostatus() {
        return iostatus;
    }

    public void setIostatus(String iostatus) {
        this.iostatus = iostatus;
    }

    public String getAnalog() {
        return analog;
    }

    public void setAnalog(String analog) {
        this.analog = analog;
    }

    public Integer getDataState() {
        return dataState;
    }

    public void setDataState(Integer dataState) {
        this.dataState = dataState;
    }
}