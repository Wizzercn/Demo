package cn.wizzer.auto.rabbit;

import org.nutz.lang.Lang;

/**
 * Created by Administrator on 2017/12/18.
 */
public class RabbitMessage {
    private static final long serialVersionUID = -6778170718151494509L;

    private String exchange;//交换器

    private byte[] body;

    private String routeKey;//路由key

    public RabbitMessage() {
    }

    public RabbitMessage(String exchange, String routeKey, String json) {
        this.exchange = exchange;
        this.routeKey = routeKey;
        this.body = json.getBytes();
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }
}
