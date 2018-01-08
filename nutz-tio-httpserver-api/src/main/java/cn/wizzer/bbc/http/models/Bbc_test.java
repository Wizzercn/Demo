package cn.wizzer.bbc.http.models;

import org.nutz.dao.entity.annotation.*;
import org.nutz.ioc.loader.annotation.IocBean;

import java.io.Serializable;

/**
 * Created by Wizzer on 2018/1/8.
 */
@IocBean
@Table("bbc_test")
public class Bbc_test implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("名称")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String name;

    @Column
    @Comment("时间")
    @ColDefine(type =ColType.INT)
    private Integer opAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOpAt() {
        return opAt;
    }

    public void setOpAt(Integer opAt) {
        this.opAt = opAt;
    }
}
