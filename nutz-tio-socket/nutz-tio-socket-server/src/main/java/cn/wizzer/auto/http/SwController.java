package cn.wizzer.auto.http;

import cn.wizzer.auto.socket.SendPacket;
import cn.wizzer.auto.socket.SocketServer;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.http.server.annotation.RequestPath;
import org.tio.http.server.util.Resps;
import org.tio.utils.lock.SetWithLock;

import java.util.Set;

/**
 * Created by Administrator on 2017/12/19.
 */
@IocBean
@RequestPath(value = "/sw")
public class SwController {
    private static final Log log = Logs.get();

    @RequestPath(value = "/send")
    public HttpResponse send(HttpRequest request) throws Exception {
        String imei = Strings.sNull(request.getParam("imei"));
        String cmd = Strings.sNull(request.getParam("cmd"));
        log.info("imei::" + imei + "  cmd:::" + cmd);
        NutMap map = NutMap.NEW().addv("imei", imei);
        try {
            SetWithLock<ChannelContext> setWithLock = Aio.getChannelContextsByToken(SocketServer.serverGroupContext, imei);
            if(null!=setWithLock){
                Set<ChannelContext> contexts = setWithLock.getObj();
                if (contexts != null) {
                    for (ChannelContext context : contexts) {
                        SendPacket packet = new SendPacket();
                        cmd = cmd + "\r\n";
                        packet.setBody(cmd.getBytes());
                        Aio.send(context, packet);
                    }
                }
                map.addv("code", 0);
                map.addv("msg", "发送成功");
            }else{
                map.addv("code", -1);
                map.addv("msg", "maybe ip error");
            }

            return Resps.json(request, Json.toJson(map));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            map.addv("code", -1);
            map.addv("msg", e.getMessage());
            return Resps.json(request, Json.toJson(map));
        }
    }
}
