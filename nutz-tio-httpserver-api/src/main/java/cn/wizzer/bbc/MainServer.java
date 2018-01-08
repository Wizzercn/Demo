package cn.wizzer.bbc;

import cn.wizzer.bbc.http.HttpServer;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * Created by Wizzer on 2018/1/8.
 */
@IocBean
public class MainServer {
    private static final Log log = Logs.get();

    public static void main(String[] args) {
        try {
            ComboIocLoader loader = new ComboIocLoader(
                    new String[]{"*json", "config/ioc/", "*anno", "cn.wizzer"}
            );
            NutIoc ioc = new NutIoc(loader);
            Globals.ioc = ioc;
            //http
            ioc.get(HttpServer.class).init();
            //初始化表
            Daos.createTablesInPackage(ioc.get(Dao.class), "cn.wizzer.bbc.http.models", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
