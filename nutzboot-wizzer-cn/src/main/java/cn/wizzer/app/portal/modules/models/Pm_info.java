package cn.wizzer.app.portal.modules.models;

import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Times;

import java.io.Serializable;

/**
 * Created by wizzer on 2018/3/24.
 */
@Table("pm_info")
public class Pm_info implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String nickname;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 500)
    private String note;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String contact;

    @Column
    @ColDefine(type = ColType.INT)
    private int money;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String payat;

    @Column
    @ColDefine(type = ColType.BOOLEAN)
    private boolean disabled;

    @Column
    @Comment("操作时间")
    @Prev(els = @EL("$me.now()"))
    //Long不要用ColDefine定义,兼容oracle/mysql,支持2038年以后的时间戳
    private Long opAt;

    public Long now() {
        return Times.getTS();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getPayat() {
        return payat;
    }

    public void setPayat(String payat) {
        this.payat = payat;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public Long getOpAt() {
        return opAt;
    }

    public void setOpAt(Long opAt) {
        this.opAt = opAt;
    }
}
