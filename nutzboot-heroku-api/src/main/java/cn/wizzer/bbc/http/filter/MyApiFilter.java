package cn.wizzer.bbc.http.filter;

import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.UTF8JsonView;
import sun.awt.AppContext;

/**
 * Created by Wizzer on 2018/1/12.
 */
public class MyApiFilter implements ActionFilter {
    private static final Log log = Logs.get();

    public View match(ActionContext context) {
        PropertiesProxy conf = context.getIoc().get(PropertiesProxy.class, "conf");
        NutMap map = NutMap.NEW();
        String appid = Strings.sNull(context.getRequest().getParameter("appid"));
        String key = Strings.sNull(context.getRequest().getParameter("appid"));
        String server_appid = conf.get("server.api.appid", "");
        String server_key = conf.get("server.api.key", "");
        if (!appid.equals(server_appid)) {
            //签名机制的DEMO，并未实现功能，自己要实现啊！！
            map.addv("code", -1);
            map.addv("msg", "签名验证失败");
            return new UTF8JsonView(JsonFormat.compact()).setData(map);
        }
        return null;
    }
}
