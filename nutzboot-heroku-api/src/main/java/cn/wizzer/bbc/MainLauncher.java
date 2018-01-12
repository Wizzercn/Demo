package cn.wizzer.bbc;

import org.nutz.boot.NbApp;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * Created by Wizzer on 2018/1/12.
 */
@IocBean(create = "init")
public class MainLauncher {
    @Inject
    protected Dao dao;

    public void init() {
        Daos.createTablesInPackage(dao, "cn.wizzer.bbc.http.models", false);
    }

    public static void main(String[] args) throws Exception {
        new NbApp().setPrintProcDoc(true).run();
    }
}
