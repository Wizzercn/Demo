package cn.wizzer.auto.socket;

import cn.wizzer.auto.rabbit.RabbitMessage;
import cn.wizzer.auto.rabbit.RabbitProducer;
import org.nutz.integration.jedis.RedisService;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.lang.Strings;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;
import org.tio.utils.json.Json;

import java.io.File;
import java.util.Date;

/**
 * Created by Wizzer on 2017/9/18.
 */
@IocBean
public class MyServerAioHandler extends MyAbsAioHandler implements ServerAioHandler {
    private final static Log log= Logs.get();
    @Inject
    private PropertiesProxy conf;
    @Inject
    private RabbitProducer rabbitProducer;
    @Inject
    private RedisService redisService;

    /**
     * 处理消息
     */
    @Override
    public void handler(Packet packet, ChannelContext channelContext) throws Exception {
        MyPacket helloPacket = (MyPacket) packet;
        Aio.bindToken(channelContext, helloPacket.getImei());
        String day = Times.format("yyyyMMdd",new Date());
        File file = new File(conf.get("server.file.path", "./") + helloPacket.getImei() + "/" + day + ".log");
        Files.createFileIfNoExists(file);
        Files.appendWrite(file, Json.toJson(helloPacket) + "\r\n");
        String exchange = "sweeper-tioTopicExchange";
        String routeKey = "tio.data.get";
        RabbitMessage msg = new RabbitMessage(exchange, routeKey, Json.toJson(helloPacket));
        rabbitProducer.sendMessage(msg);
        String cmd = redisService.get("tio:cmd:" + helloPacket.getImei());
        if (!Strings.isEmpty(cmd)) {
            log.info("cmd:::"+helloPacket.getImei()+":::"+cmd);
            SendPacket sendPacket = new SendPacket();
            cmd = cmd + "\r\n";
            sendPacket.setBody(cmd.getBytes());
            Aio.send(channelContext, packet);
            redisService.del("tio:cmd:" + helloPacket.getImei());
        }
    }
}