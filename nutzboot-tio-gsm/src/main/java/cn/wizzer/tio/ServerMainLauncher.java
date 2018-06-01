package cn.wizzer.tio;

import cn.wizzer.tio.bean.Device_info;
import org.nutz.boot.NbApp;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.Modules;

/**
 * Created by JF on 2018/5/14.
 */
@IocBean(create = "init", depose = "close")
@Modules(packages = "cn.wizzer")
public class ServerMainLauncher {
    private final static Log log = Logs.get();
    @Inject("refer:$ioc")
    private Ioc ioc;
    @Inject
    private Dao dao;

    public static void main(String[] args) throws Exception {
        NbApp nb = new NbApp().setArgs(args).setPrintProcDoc(true);
        nb.getAppContext().setMainPackage("cn.wizzer");
        nb.run();
    }


    public void init() {
        //通过POJO类创建表结构
        try {
            Daos.createTablesInPackage(dao, "cn.wizzer", false);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
//        Device_info info=new Device_info();
//        info.setEquipID(4005L);
//        info.setEquipName("移动");
//        dao.insert(info);
//        Device_info info2=new Device_info();
//        info2.setEquipID(4006L);
//        info.setEquipName("联通");
//        dao.insert(info2);

    }

    public void close() {

    }
}
