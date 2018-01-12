package cn.wizzer.bbc.http.services;

import cn.wizzer.bbc.http.models.Bbc_test;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

/**
 * Created by Wizzer on 2018/1/8.
 */
@IocBean
public class BbcTestSerivce {
    @Inject
    private Dao dao;

    public List<Bbc_test> list(Cnd cnd) {
        return this.dao.query(Bbc_test.class, cnd);
    }
}
