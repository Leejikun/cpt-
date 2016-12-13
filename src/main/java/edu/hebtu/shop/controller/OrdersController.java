package main.java.edu.hebtu.shop.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

import main.java.edu.hebtu.shop.modle.Goods;
import main.java.edu.hebtu.shop.modle.Orders;
import main.java.edu.hebtu.shop.modle.Result;
import main.java.edu.hebtu.shop.modle.User;

import org.apache.log4j.Logger;


public class OrdersController extends Controller {

    @SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(OrdersController.class);

    @ActionKey("/orderlist")
    public void orderList() {
        String name = getPara("name");
        String token = getPara("token");
        if (User.userDao.check(name, token)) {
            renderJson(Orders.ordersDao.paginate(1, 10));
        } else {
            renderJson(new Result().success(false).message("非法请求"));
        }
    }

    @ActionKey("/orderdetail")
    public void goodsDetail() {
        String name = getPara("name");
        String token = getPara("token");
        if (User.userDao.check(name, token)) {
            renderJson(Goods.goodsDao.getDetail(1));
        } else {
            renderJson(new Result().success(false).message("非法请求"));
        }
    }

    @ActionKey("/buy")
    public void buy() {
        String name = getPara("name");
        Integer address = Integer.valueOf(getPara("address")).intValue();
        Integer goods = Integer.valueOf(getPara("goods")).intValue();
        String token = getPara("token");
        if (User.userDao.check(name, token)) {
            renderJson(Orders.ordersDao.add(name, address, goods));
        } else {
            renderJson(new Result().success(false).message("非法请求"));
        }
    }

    @ActionKey("/return")
    public void returnOrder() {
        String name = getPara("name");
        Integer id = Integer.valueOf(getPara("id")).intValue();
        String token = getPara("token");
        if (User.userDao.check(name, token)) {
            renderJson(Orders.ordersDao.returnOrder(id));
        } else {
            renderJson(new Result().success(false).message("非法请求"));
        }
    }


}
