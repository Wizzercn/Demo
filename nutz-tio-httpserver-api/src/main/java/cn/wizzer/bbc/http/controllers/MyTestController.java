package cn.wizzer.bbc.http.controllers;

import cn.wizzer.bbc.Globals;
import cn.wizzer.bbc.http.services.BbcTestSerivce;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.http.server.annotation.RequestPath;
import org.tio.http.server.util.Resps;

/**
 * Created by Wizzer on 2018/1/8.
 */
@IocBean
@RequestPath(value = "/test")
public class MyTestController {
    private static final Log log = Logs.get();
    @Inject
    private BbcTestSerivce bbcTestSerivce;

    @RequestPath(value = "/list")
    public HttpResponse list(HttpRequest request) throws Exception {
        String id = Strings.sNull(request.getParam("id"));
        NutMap map = NutMap.NEW();
        try {
            Cnd cnd = Cnd.NEW();
            if (Strings.isNotBlank(id)) {
                cnd.and("id", "=", id);
            }
            map.setv("code", 0);
            map.setv("list", bbcTestSerivce.list(cnd));
        } catch (Exception e) {
            e.printStackTrace();
            map.setv("code", -1);
            map.setv("msg", "查询失败");
        }
        return Resps.json(request, Json.toJson(map));
    }
}
