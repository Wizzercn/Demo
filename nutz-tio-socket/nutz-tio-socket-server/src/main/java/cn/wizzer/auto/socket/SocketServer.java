package cn.wizzer.auto.socket;

import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.tio.server.AioServer;
import org.tio.server.ServerGroupContext;
import org.tio.server.intf.ServerAioListener;

/**
 * Created by Wizzer on 2017/9/13.
 */
@IocBean
public class SocketServer {
    private static final Log log = Logs.get();
    //handler, 包括编码、解码、消息处理
    @Inject
    private MyServerAioHandler myServerAioHandler;
    //事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
    private ServerAioListener aioListener;
    //一组连接共用的上下文对象
    public static ServerGroupContext serverGroupContext;
    //aioServer对象
    private AioServer aioServer;
    //有时候需要绑定ip，不需要则null
    private String serverIp;
    @Inject
    private PropertiesProxy conf;

    public void init() throws Exception {
        int port = conf.getInt("server.socket.port", 8600);
        log.debug("socket port::" + port);
        serverGroupContext = new ServerGroupContext("tio", myServerAioHandler, aioListener);
        serverGroupContext.setHeartbeatTimeout(30000);
        aioServer = new AioServer(serverGroupContext);
        aioServer.start(serverIp, port);
    }
}