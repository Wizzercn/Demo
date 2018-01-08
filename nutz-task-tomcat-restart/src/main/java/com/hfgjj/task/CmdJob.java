package com.hfgjj.task;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by wizzer on 2017/2/23.
 */
@IocBean
public class CmdJob implements Job {
    private static final Log log = Logs.get();

    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getJobDetail().getJobDataMap();
        String path=data.getString("path");
        log.debug("path::"+path);
        Runtime runtime=Runtime.getRuntime();
        try{
            runtime.exec("cmd /c start "+path);
        }catch(Exception e){
            log.debug(e.getMessage());
        }
    }
}
