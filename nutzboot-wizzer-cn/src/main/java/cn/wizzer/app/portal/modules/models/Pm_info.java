package cn.wizzer.app.portal.modules.models;

import cn.wizzer.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by wizzer on 2018/3/24.
 */
@Table("pm_info")
public class Pm_info extends BaseModel implements Serializable {
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
}
