package main.java.edu.hebtu.shop.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import main.java.edu.hebtu.shop.controller.AddressController;
import main.java.edu.hebtu.shop.controller.GoodsController;
import main.java.edu.hebtu.shop.controller.OrdersController;
import main.java.edu.hebtu.shop.controller.UserController;
import main.java.edu.hebtu.shop.modle.Address;
import main.java.edu.hebtu.shop.modle.Goods;
import main.java.edu.hebtu.shop.modle.Orders;
import main.java.edu.hebtu.shop.modle.User;

/**
 * API引导式配置
 */
@SuppressWarnings("unused")
public class ShopConfig extends JFinalConfig {

    /**
     * 配置常量
     */
	public void configConstant(Constants me){
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
        PropKit.use("sys_config.txt");
        me.setDevMode(PropKit.getBoolean("devMode", false));
	}

    /**
     * 配置路由
     */
    public void configRoute(Routes me) {
        me.add("user", UserController.class);
        me.add("orders", OrdersController.class);
        me.add("goods", GoodsController.class);
        me.add("address", AddressController.class);
    }

    public static C3p0Plugin createC3p0Plugin() {
        return new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
    }

    /**
     * 配置插件
     */
    public void configPlugin(Plugins me) {
        // 配置C3p0数据库连接池插件
        C3p0Plugin C3p0Plugin = createC3p0Plugin();
        me.add(C3p0Plugin);

        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(C3p0Plugin);
        me.add(arp);

        arp.addMapping("user", "name", User.class);    // 映射
        arp.addMapping("address", "id", Address.class);
        arp.addMapping("goods", "id", Goods.class);
        arp.addMapping("orders", "id", Orders.class);
    }

    /**
     * 配置全局拦截器
     */
    public void configInterceptor(Interceptors me) {

    }

    /**
     * 配置处理器
     */
    public void configHandler(Handlers me) {

    }

    /**
     * 建议使用 JFinal 手册推荐的方式启动项目
     * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
     */
    public static void main(String[] args) {

    }
}
