package cn.wizzer.bbc.http.interceptor;

import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.http.common.RequestLine;
import org.tio.http.server.intf.HttpServerInterceptor;
import org.tio.http.server.util.Resps;

/**
 * Created by Wizzer on 2018/1/8.
 */
@IocBean
public class ApiInterceptor implements HttpServerInterceptor {
    @Inject
    private PropertiesProxy conf;

    public void doAfterHandler(HttpRequest request, RequestLine requestLine, HttpResponse response) throws Exception {

    }

    public HttpResponse doBeforeHandler(HttpRequest request, RequestLine requestLine, HttpResponse responseFromCache) throws Exception {
        NutMap map = NutMap.NEW();
        String appid = Strings.sNull(request.getParam("appid"));
        String key = Strings.sNull(request.getParam("key"));
        String server_appid = conf.get("server.api.appid", "");
        String server_key = conf.get("server.api.key", "");
        if (!appid.equals(server_appid)) {
            //签名机制的DEMO，并未实现功能，自己要实现啊！！
            map.addv("code", -1);
            map.addv("msg", "签名验证失败");
            return Resps.json(request, Json.toJson(map));
        }
        return null;
    }

}
