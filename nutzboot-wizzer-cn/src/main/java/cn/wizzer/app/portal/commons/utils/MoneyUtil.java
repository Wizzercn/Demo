package cn.wizzer.app.portal.commons.utils;

import org.nutz.ioc.loader.annotation.IocBean;

import java.math.BigDecimal;

/**
 * Created by wizzer on 2018/3/24.
 */
@IocBean
public class MoneyUtil {
    public String getMoney(int money) {
        try {
            return new BigDecimal(money).divide(new BigDecimal(100)).setScale(2).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.00";
    }
}
