package cn.wizzer.auto;

import cn.wizzer.auto.socket.SocketServer;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.tio.http.server.HttpServerStarter;

/**
 * Created by Wizzer on 2017/9/13.
 */
@IocBean
public class MainServer {
    private static final Log log = Logs.get();

    public static void main(String[] args) {
        try {
            ComboIocLoader loader = new ComboIocLoader(
                    new String[]{"*json", "config/ioc/", "*anno", "cn.wizzer", "*rabbitmq", "*jedis"}
            );
            NutIoc ioc = new NutIoc(loader);
            Globals.ioc = ioc;
            //socket
            ioc.get(SocketServer.class).init();
            //http
            ioc.get(HttpServerStarter.class).start();
            //mq
            String topicQueue = "sweeper-tioTopicQueue";
            ConnectionFactory factory = ioc.get(ConnectionFactory.class, "rabbitmq_cf");
            Connection rabbitmq_conn = factory.newConnection();
            Channel rabbitmq_channel = rabbitmq_conn.createChannel();
            rabbitmq_channel.queueDeclare(topicQueue, true, false, false, null);
            rabbitmq_channel.exchangeDeclare("sweeper-tioTopicExchange", BuiltinExchangeType.TOPIC, true);
            rabbitmq_channel.queueBind(topicQueue, "sweeper-tioTopicExchange", "tio.#");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}