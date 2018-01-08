package cn.wizzer.auto.rabbit;

import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import static org.nutz.integration.rabbitmq.aop.RabbitmqMethodInterceptor.*;

/**
 * Created by Administrator on 2017/12/18.
 */
@IocBean
public class RabbitProducer {
    private static final Log log = Logs.get();

    /**
     * 发送信息
     *
     * @param msg 消息体
     */
    @Aop("rabbitmq")
    public void sendMessage(RabbitMessage msg) {
        try {
            channel().basicPublish(msg.getExchange(), msg.getRouteKey(), null, msg.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("RabbitProducer error::" + e.getMessage());
        }
    }
}
