package cn.wizzer.app.portal.modules.controllers;

import cn.wizzer.app.portal.commons.utils.MoneyUtil;
import cn.wizzer.app.portal.modules.models.Pm_info;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wizzer on 2018/3/24.
 */
@IocBean
public class PcIndexController {
    private static final Log log = Logs.get();
    @Inject
    private Dao dao;
    @Inject
    private MoneyUtil moneyUtil;

    @At(value = {"/", "/index"}, top = true)
    @Ok("beetl:/index.html")
    public void index( HttpServletRequest req) {
        req.setAttribute("nav","index");
    }

    @At(value = {"/case", "/case/?"})
    @Ok("re")
    public String casePage(String p, HttpServletRequest req) {
        req.setAttribute("nav","case");
        if (Strings.isNumber(p) && Integer.valueOf(p) == 2) {
            return "beetl:/case_2.html";
        } else
            return "beetl:/case_1.html";

    }

    @At(value = {"/donation/", "/donation/?"})
    @Ok("beetl:/donation.html")
    public void donation(String p, HttpServletRequest req) {
        int page;
        int size = 10;
        if (!Strings.isNumber(p)) {
            page = 1;
        } else page = Integer.valueOf(p);
        Pager pager = new Pager();
        pager.setPageSize(size);
        pager.setPageNumber(page);
        pager.setRecordCount(dao.count(Pm_info.class));
        List<Pm_info> list = dao.query(Pm_info.class, Cnd.orderBy().desc("payAt"), pager);
        Sql sql = Sqls.create("select sum(money) from pm_info");
        sql.setCallback(Sqls.callback.integer());
        dao.execute(sql);
        int totalMoney = sql.getInt();
        req.setAttribute("nav","donation");
        req.setAttribute("list", list);
        req.setAttribute("total", pager.getRecordCount());
        req.setAttribute("totalMoney", totalMoney);
        req.setAttribute("page", pager.getPageNumber());
        req.setAttribute("pageCount", pager.getPageCount());
        req.setAttribute("moneyUtil", moneyUtil);
    }
}
