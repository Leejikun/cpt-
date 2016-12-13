package main.java.edu.hebtu.shop.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

import main.java.edu.hebtu.shop.modle.User;

import org.apache.log4j.Logger;


public class UserController extends Controller {
    @SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @ActionKey("/regiest")
    public void regiest() {
        String name = getPara("name");
        String pswd = getPara("pswd");
        renderJson(User.userDao.save(name, pswd).getMap());
    }

    @ActionKey("/login")
    public void login() {
        String name = getPara("name");
        String pswd = getPara("pswd");
        renderJson(User.userDao.find(name, pswd).getMap());
    }

}
