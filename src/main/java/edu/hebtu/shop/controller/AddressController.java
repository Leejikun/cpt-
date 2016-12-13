package main.java.edu.hebtu.shop.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

import main.java.edu.hebtu.shop.modle.Orders;
import main.java.edu.hebtu.shop.modle.Result;
import main.java.edu.hebtu.shop.modle.User;

import org.apache.log4j.Logger;


public class AddressController extends Controller {

    @SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(AddressController.class);

    @ActionKey("/addressinfo")
    public void addressinfo() {
        String name = getPara("name");
        String token = getPara("token");
        Integer id = Integer.valueOf(getPara("id"));
        if (!User.userDao.check(name, token)) {
            renderJson(Orders.ordersDao.getDetail(id).getMap());
        } else {
            renderJson(new Result().success(false).message("非法请求"));
        }
    }
}
