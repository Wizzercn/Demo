package cn.wizzer.auto.server;

import cn.wizzer.auto.commons.Globals;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by Wizzer on 2017/9/13.
 */
@IocBean
public class MainServer {
    private static final Log log = Logs.get();

    public static void main(String[] args) {
        try {
            ComboIocLoader loader = new ComboIocLoader(
                    new String[]{"*json", "config/ioc/", "*anno", "cn.wizzer", "*rabbitmq"}
            );
            Globals.IOC = new NutIoc(loader);
            PropertiesProxy conf = Globals.IOC.get(PropertiesProxy.class, "conf");
            //mq
            String topicQueue = "sweeper-tioTopicQueue";
            ConnectionFactory factory = Globals.IOC.get(ConnectionFactory.class, "rabbitmq_cf");
            Connection rabbitmq_conn = factory.newConnection();
            Channel rabbitmq_channel = rabbitmq_conn.createChannel();
            rabbitmq_channel.queueDeclare(topicQueue, true, false, false, null);
            rabbitmq_channel.exchangeDeclare("topicExchange", BuiltinExchangeType.TOPIC, true);
            rabbitmq_channel.queueBind(topicQueue, "topicExchange", "tio.#");
            rabbitmq_channel.basicConsume(topicQueue, false, "sweeperTioConsumerTagTopic",
                    new DefaultConsumer(rabbitmq_channel) {
                        @Override
                        public void handleDelivery(String consumerTag,
                                                   Envelope envelope,
                                                   AMQP.BasicProperties properties,
                                                   byte[] body)
                                throws IOException {
                            String routingKey = envelope.getRoutingKey();
                            String exchange = envelope.getExchange();
                            NutMap params = Lang.fromBytes(body, NutMap.class);
                            log.debug("RabbitMQ topicExchange=" + exchange + ",routingKey=" + routingKey + ",params=" + Json.toJson(params));
                            long deliveryTag = envelope.getDeliveryTag();
                            switch (exchange) {
                                case "topicExchange"://主题模式,只需一个消费者消费
                                    switch (routingKey) {
                                        case "tio.data.get":
                                            //todo
                                            log.debug("topic.data.get.......");
                                            break;
                                    }
                                    break;
                            }
                            // (process the message components here ...)
                            rabbitmq_channel.basicAck(deliveryTag, false);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}