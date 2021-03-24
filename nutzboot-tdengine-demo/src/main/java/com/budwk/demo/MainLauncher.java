package com.budwk.demo;

import org.nutz.boot.NbApp;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.Date;
import java.util.List;
import java.util.Map;

@IocBean(create = "init")
public class MainLauncher {
    private static final Log log = Logs.get();
    @Inject("refer:$ioc")
    private Ioc ioc;
    @Inject
    private PropertiesProxy conf;
    @Inject
    private Dao dao;

    public static void main(String[] args) throws Exception {
        NbApp nb = new NbApp().setArgs(args).setPrintProcDoc(true);
        nb.getAppContext().setMainPackage("com.budwk");
        nb.run();
    }

    public void init() {
        log.debug("DatabaseType:::::::" + dao.getJdbcExpert().getDatabaseType());
        boolean a=dao.exists(Iot_dev.class);
        // 创建表结构
        try {
            dao.create(Iot_dev.class, false);
        }catch (Exception e){

        }

        // 插入数据
        Iot_dev iotDev = new Iot_dev();
        for (int i = 0; i < 10; i++) {
            iotDev.setTs(new Date());
            iotDev.setDevId("test");
            iotDev.setDevType("nb");
            iotDev.setVal1(Double.valueOf(i + ".1234"));
            iotDev.setVal2(i);
            iotDev.setVal3(i);
            iotDev.setVal4(123);
            iotDev.setStatus(true);
            dao.insert(iotDev);
        }

        // 统计条数
        try {
            Sql sql0 = Sqls.create("select count(*) from iot_dev ");
            sql0.setCallback(Sqls.callback.integer());
            dao.execute(sql0);
            log.debug("count1:::" + sql0.getInt());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 统计条数
        try {
            log.debug("count2:::" + dao.count(Iot_dev.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 通过实体类查询
        List<Iot_dev> list = dao.query(Iot_dev.class, Cnd.NEW());
        log.debug("list::\n" + Json.toJson(list, JsonFormat.full()));

        // 分页查询
        Pager pager = new Pager();
        pager.setPageNumber(1);
        pager.setPageSize(3);
        List<Iot_dev> pageList = dao.query(Iot_dev.class, Cnd.NEW(), pager);
        log.debug("pageList::\n" + Json.toJson(pageList, JsonFormat.full()));

        // 返回 map 查询 (map key区分大小写，因为 TDengine 全是小写，所以map和record 无差别）
        Sql sql = Sqls.create("select * from iot_dev order by ts desc limit 1");
        sql.setCallback(Sqls.callback.maps());
        dao.execute(sql);
        log.debug("mapList::\n" + Json.toJson(sql.getList(Map.class), JsonFormat.full()));

        // 返回 record 数据(record key 不区分大小写，因为 TDengine 全是小写，所以map和record 无差别）
        Sql sql2 = Sqls.create("select * from iot_dev order by ts desc limit 1");
        sql2.setCallback(Sqls.callback.records());
        dao.execute(sql2);
        log.debug("recordList::\n" + Json.toJson(sql2.getList(Record.class), JsonFormat.full()));

    }
}
