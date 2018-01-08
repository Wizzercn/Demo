package com.hfgjj.task;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

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
@IocBean
public class KillJob implements Job {
    private static final Log log = Logs.get();

    // 通过获取当前运行主机的pidName，截取获得他的pid
    public static String getCurrentPid() throws Exception {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String pidName = runtime.getName();// 5296@dell-PC
        String pid = pidName.substring(0, pidName.indexOf("@"));
        return pid;
    }

    public static void killByName(String name) throws Exception {
        Runtime.getRuntime().exec("TASKKILL /F /IM " + name);
    }

    public static void killById(String id) throws Exception {
        Runtime.getRuntime().exec("TASKKILL /F /PID " + id);
    }

    // 根据PidName获取当前的Pid的list集合
    public static List<String> getPIDListByPidName(String pidName) throws Exception {
        List<String> pidList = new ArrayList<String>();
        InputStream is = null;
        InputStreamReader ir = null;
        BufferedReader br = null;
        String line = null;
        String[] array = (String[]) null;
        try {
            String imageName = pidName + ".exe";
            Process p = Runtime.getRuntime().exec("TASKLIST /NH /FO CSV /FI \"IMAGENAME EQ " + imageName + "\"");
            is = p.getInputStream();
            ir = new InputStreamReader(is);
            br = new BufferedReader(ir);
            while ((line = br.readLine()) != null) {
                if (line.indexOf(imageName) != -1) {
                    array = line.split(",");
                    line = array[1].replaceAll("\"", "");
                    pidList.add(line);
                }
            }
        } catch (IOException localIOException) {
            throw new Exception("获取进程ID出错！");
        } finally {
            if (br != null) {
                br.close();
            }
            if (ir != null) {
                ir.close();
            }
            if (is != null) {
                is.close();
            }
        }
        return pidList;
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            String cpid = getCurrentPid();
            log.info("pid::" + cpid);
            List<String> pidList = getPIDListByPidName("java");
            log.info("pid list::" + Json.toJson(pidList));
            for (String pid : pidList) {
                if (!cpid.equalsIgnoreCase(pid.trim())) {
                    killById(pid);
                    log.info("kill pid::" + pid);
                }
            }
            log.info("kill cmd.exe");
            killByName("cmd.exe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}