package com.hfgjj.task;

import org.nutz.integration.quartz.QuartzJob;
import org.nutz.integration.quartz.QuartzManager;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.quartz.JobKey;
import org.quartz.impl.triggers.SimpleTriggerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wizzer on 2017/2/23.
 */
public class App {
    private final static Log log = Logs.get();
    private static QuartzManager manager;


    public static void main(String[] args) {
        try {
            ComboIocLoader loader = new ComboIocLoader(
                    new String[]{"*anno", "com.hfgjj", "*quartz"}
            );
            NutIoc ioc = new NutIoc(loader);
            manager = ioc.get(QuartzManager.class);
            manager.clear();
            //taskkill /F /IM cmd.exe
            PropertiesProxy conf = new PropertiesProxy("custom");
            log.info(Json.toJson(conf.getKeys()));
            JobKey jobKey0 = new JobKey("shutdown", "tomcat");
            QuartzJob quartzJob0 = new QuartzJob();
            quartzJob0.setCron(conf.get("shutdown.cron"));
            quartzJob0.setClassName("com.hfgjj.task.KillJob");
            quartzJob0.setJobKey(jobKey0);
            manager.add(quartzJob0);
//            //tomcat1.startup
            JobKey jobKey1 = new JobKey("tomcat1.startup", "tomcat");
            QuartzJob quartzJob1 = new QuartzJob();
            quartzJob1.setCron(conf.get("startup.cron"));
            quartzJob1.setClassName("com.hfgjj.task.CmdJob");
            quartzJob1.setJobKey(jobKey1);
            quartzJob1.setDataMap(Json.toJson(NutMap.NEW().addv("path", conf.get("tomcat1.startup.path"))));
            manager.add(quartzJob1);
//            //tomcat2.startup
            JobKey jobKey3 = new JobKey("tomcat2.startup", "tomcat");
            QuartzJob quartzJob3 = new QuartzJob();
            quartzJob3.setCron(conf.get("startup.cron"));
            quartzJob3.setClassName("com.hfgjj.task.CmdJob");
            quartzJob3.setJobKey(jobKey3);
            quartzJob3.setDataMap(Json.toJson(NutMap.NEW().addv("path", conf.get("tomcat2.startup.path"))));
            manager.add(quartzJob3);
//            //tomcat8080.startup
            JobKey jobKey5 = new JobKey("tomcat8080.startup", "tomcat");
            QuartzJob quartzJob5 = new QuartzJob();
            quartzJob5.setCron(conf.get("startup.cron"));
            quartzJob5.setClassName("com.hfgjj.task.CmdJob");
            quartzJob5.setJobKey(jobKey5);
            quartzJob5.setDataMap(Json.toJson(NutMap.NEW().addv("path", conf.get("tomcat8080.startup.path"))));
            manager.add(quartzJob5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
