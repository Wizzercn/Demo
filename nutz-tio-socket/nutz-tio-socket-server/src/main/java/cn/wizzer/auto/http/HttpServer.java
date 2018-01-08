package cn.wizzer.auto.http;

import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.tio.http.common.HttpConfig;
import org.tio.http.common.handler.HttpRequestHandler;
import org.tio.http.server.HttpServerStarter;
import org.tio.http.server.handler.DefaultHttpRequestHandler;
import org.tio.http.server.mvc.Routes;

/**
 * Created by Administrator on 2017/12/19.
 */
@IocBean
public class HttpServer {
    private static final Log log = Logs.get();
    private HttpConfig httpConfig;

    private HttpRequestHandler requestHandler;

    private HttpServerStarter httpServerStarter;
    @Inject
    private PropertiesProxy conf;

    public void init() throws Exception {
        long start = System.currentTimeMillis();
        int port = conf.getInt("server.http.port", 9900);//启动端口
        String[] scanPackages = new String[]{HttpServer.class.getPackage().getName()};//tio mvc需要扫描的根目录包
        HttpConfig httpConfig = new HttpConfig(port, null, null, null);
        Routes routes = new Routes(scanPackages);
        DefaultHttpRequestHandler requestHandler = new DefaultHttpRequestHandler(httpConfig, routes);
        HttpServerStarter httpServerStarter = new HttpServerStarter(httpConfig, requestHandler);
        httpServerStarter.start();
        long end = System.currentTimeMillis();
        long iv = end - start;
        log.infof("Tio Http Server启动完毕,耗时:[%s]ms,访问地址:http://127.0.0.1:%s", iv, port);
    }
}
