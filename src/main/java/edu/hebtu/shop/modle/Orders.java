package main.java.edu.hebtu.shop.modle;

import com.jfinal.plugin.activerecord.Model;
import org.apache.log4j.Logger;


@SuppressWarnings("serial")
public class Orders extends Model<Orders> {

    @SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Orders.class);

    public static final Orders ordersDao = new Orders();

    public Result paginate(int pageNumber, int pageSize) {
        return new Result().success(true).message("获取成功")
                .datas(paginate(pageNumber, pageSize, "select name,", "from orders order by id asc"));
    }

    public Result getDetail(int id) {
        return new Result().success(true).message("获取成功")
                .datas(findById(id));
    }

    public Result add(String user, int address, int goods) {
        new Orders();
        save();
        return new Result().success(true).message("获取成功")
                .datas(null);
    }
    public Result returnOrder(int id) {
        new Orders();
        save();
        return new Result().success(true).message("获取成功")
                .datas(null);
    }
}
