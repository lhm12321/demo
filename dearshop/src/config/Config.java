package config;

import interceptor.CommonInterceptor;

import java.util.Properties;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import action.MainController;
import handler.SessionHandler;

public class Config extends JFinalConfig{
	public void configConstant(Constants me) { 
	    me.setDevMode(true); 
	    me.setViewType(ViewType.JSP);
	} 
	public void configRoute(Routes me) {
		me.add("/", MainController.class);
		me.add(new AutoBindRoutes());//自动注册路由，默认规则例：TreeMenuController注册路由为/treeMenu，首字母小写
	} 
	public void configPlugin(Plugins me) {
		//System.setProperty("oracle.jdbc.V8Compatible", "true");//处理Oracle10驱动包Date类型数据取出时只有日期没有时间的问题
		// 配置Druid数据库连接池插件
		Properties ps = loadPropertyFile("classes/druid.properties");
		DruidPlugin druidPlugin = new DruidPlugin(ps.getProperty("url"),ps.getProperty("username"),ps.getProperty("password"),ps.getProperty("driverClass"));
		me.add(druidPlugin);
		
		//自动绑定表【有了它就不再需要 ActiveRecordPlugin了】
		AutoTableBindPlugin atbp = new AutoTableBindPlugin(druidPlugin);
		me.add(atbp);
		
		atbp.setContainerFactory(new CaseInsensitiveContainerFactory());//忽略数据库字段大小写
		atbp.setDialect(new MysqlDialect());//设置数据库方言为Mysql
		
	} 
	public void configInterceptor(Interceptors me) {
		me.add(new CommonInterceptor());//全局拦截器初始化callBackJson相关信息
		me.add(new SessionInViewInterceptor());
	} 
	public void configHandler(Handlers me) {
		me.add(new SessionHandler(loadPropertyFile("classes/filter.properties"))); //拦截器
	} 
	
	public void afterJFinalStart(){
	}
	
	public void beforeJFinalStop(){
	}
}
