package main.java.edu.hebtu.shop.modle;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import org.apache.log4j.Logger;


@SuppressWarnings("serial")
public class Goods extends Model<Goods> {

    @SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Goods.class);

    public static final Goods goodsDao = new Goods();

    public Result paginate(int pageNumber, int pageSize) {
        Page<Goods> goods = paginate(pageNumber, pageSize, "select id,name,price", "from goods order by id asc");
        return new Result().success(true).message("获取成功").datas(goods);
    }

    public Result getDetail(int id) {
        return new Result().success(true).message("获取成功").datas(findById(id));
    }
}
