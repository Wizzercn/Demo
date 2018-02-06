package cn.wizzer.auto.http;

import cn.wizzer.auto.Globals;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.tio.http.common.HttpConfig;
import org.tio.http.server.HttpServerStarter;
import org.tio.http.server.handler.DefaultHttpRequestHandler;
import org.tio.http.server.mvc.Routes;
import org.tio.http.server.mvc.intf.ControllerFactory;

/**
 * Created by Administrator on 2017/12/19.
 */
@IocBean
public class HttpServer implements ControllerFactory {
    private static final Log log = Logs.get();
    @Inject
    private PropertiesProxy conf;

    @IocBean
    public HttpConfig getHttpConfig() {
        int port = conf.getInt("server.http.port", 9900);//启动端口
        HttpConfig httpConfig = new HttpConfig(port, null, null, null);
        httpConfig.setCharset("UTF-8");
        //更多参数及用法可参考 nutzboot-starter-tio-mvc 代码
        return httpConfig;
    }

    @IocBean
    public HttpServerStarter getHttpServerStarter(HttpConfig httpConfig) {
        String[] scanPackages = new String[]{HttpServer.class.getPackage().getName()};//tio mvc需要扫描的根目录包
        Routes routes = new Routes(scanPackages, this);
        DefaultHttpRequestHandler requestHandler = new DefaultHttpRequestHandler(httpConfig, routes);
        return new HttpServerStarter(httpConfig, requestHandler);
    }


    public Object getInstance(Class<?> controllerClazz) throws Exception {
        if (controllerClazz.getAnnotation(IocBean.class) == null)
            return controllerClazz.newInstance();
        return Globals.ioc.get(controllerClazz);
    }
}
