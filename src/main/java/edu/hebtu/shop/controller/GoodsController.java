package main.java.edu.hebtu.shop.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

import main.java.edu.hebtu.shop.modle.Goods;
import main.java.edu.hebtu.shop.modle.Result;

import org.apache.log4j.Logger;


public class GoodsController extends Controller {
    @SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(GoodsController.class);

    @ActionKey("/goodslist")
    public void goodsList() {
        Integer start = Integer.valueOf(getPara("start"));
        Integer size = Integer.valueOf(getPara("size"));
        renderJson(Goods.goodsDao.paginate(start, size).getMap());
    }

    @ActionKey("/goodsdetail")
    public void goodsDetail() {
        Integer id = Integer.valueOf(getPara("id"));
        renderJson(Goods.goodsDao.getDetail(id).getMap());
    }

    @ActionKey("/add")
    public void add() {
        getModel(Goods.class).save();
        renderJson(new Result().success(true).message("发布成功").getMap());
    }
}
