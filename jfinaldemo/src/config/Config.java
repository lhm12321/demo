package config;

import interceptor.CommonInterceptor;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.Tblog;
import model.Tuser;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.ext.quartz.QuartzPlugin;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import common.LoadConfigXml;

import action.IndexController;
import handler.SessionHandler;

public class Config extends JFinalConfig {

	// 检测是否在服务器端
	boolean devMode = LoadConfigXml.loadConfigXml("/usr/AP/config/corex.xml");

	// 基础设置
	public void configConstant(Constants me) {
		// 设置开发模式
		me.setDevMode(devMode);

		// 默认编码UTF-8
		// me.setEncoding("UTF-8");

	}

	// 路由配置
	public void configRoute(Routes me) {
		me.add("/", IndexController.class);
		me.add(new AutoBindRoutes());// 自动注册路由，默认规则例：TreeMenuController注册路由为/treeMenu，首字母小写
	}

	// 数据库配置，model注册
	public void configPlugin(Plugins me) {
		ActiveRecordPlugin arp = null;
		if (devMode) {
			Properties local = loadPropertyFile("druid.properties");
			DruidPlugin druidPlugin = new DruidPlugin(local.getProperty("url"), local.getProperty("username"), local.getProperty("password"), local.getProperty("driverClass"));
			me.add(druidPlugin);
			arp = new ActiveRecordPlugin(druidPlugin);
			System.out.println("druid---OK");
		} else {
			try {
				Properties jndi = loadPropertyFile("jndi.properties");
				DataSource datasource = (DataSource) new InitialContext().lookup(jndi.getProperty("datasource"));
				arp = new ActiveRecordPlugin(datasource);
				System.out.println("jndi---OK");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		arp.addMapping("tblog", Tblog.class);
		arp.addMapping("tuser", Tuser.class);
		me.add(arp);
		arp.setContainerFactory(new CaseInsensitiveContainerFactory());// 忽略数据库字段大小写
		arp.setDialect(new MysqlDialect());//设置数据库方言为Mysql
		if (!devMode) {
			me.add(new QuartzPlugin("job.properties"));
		}

	}

	// 拦截器配置
	public void configInterceptor(Interceptors me) {
		me.add(new CommonInterceptor()); // 全局拦截器初始化callBackJson相关信息
		me.add(new SessionInViewInterceptor()); // jsp 页面可以获取session
	}

	// web请求拦截器
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("basePath"));// 配置basepath
		me.add(new SessionHandler(loadPropertyFile("filter.properties")));
	}

	public void afterJFinalStart() {
	}

	public void beforeJFinalStop() {
	}
}
