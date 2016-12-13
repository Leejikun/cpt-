package main.java.edu.hebtu.shop.modle;

import com.jfinal.plugin.activerecord.Model;
import org.apache.log4j.Logger;

import java.util.UUID;


@SuppressWarnings("serial")
public class User extends Model<User> {

    private static final Logger LOGGER = Logger.getLogger(User.class);

    public static final User userDao = new User();

    public Result save(String name, String pswd) {
        try {
            new User().set("name", name).set("pswd", pswd).set("token", UUID.randomUUID().toString()).set("point", 0).save();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new Result().success(false).message("注册失败").datas(e.getMessage());
        }
        return new Result().success(true).message("注册成功").datas(userDao.findById(name));
    }

    public Result find(String name, String pswd) {
        User user = new User();
        try {
            user = findByIdLoadColumns(name, "pswd");
            if (!user.getStr("pswd").equals(pswd)) {
                return new Result().success(false).message("用户名密码不对").datas(null);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new Result().success(false).message("登陆失败").datas(e.getMessage());
        }
        return new Result().success(true).message("登陆成功").datas(userDao.findById(name));
    }

    public Boolean check(String name, String token) {
        User user = new User();
        try {
            user = findByIdLoadColumns(name, "token");
            if (!user.getStr("token").equals(token)) {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
}
