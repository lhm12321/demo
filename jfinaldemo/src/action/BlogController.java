package action;

import model.Tblog;
import model.Tuser;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * BlogController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
public class BlogController extends Controller {
	
	public void index() {
		setAttr("blogList", Tblog.dao.findAll());
		render("blog.jsp");
	}
	
	public void add() {
	}
	
	public void save() {
		getModel(Tblog.class).save();
		redirect("/blog");
	}
	
	public void edit() {
		setAttr("blog", Tblog.dao.findById(getParaToInt()));
	}
	
	public void update() {
		getModel(Tblog.class).update();
		redirect("/blog");
	}
	
	public void delete() {
		Tblog.dao.deleteById(getParaToInt());
		redirect("/blog");
	}
	public void dashboard() {
		render("/theme/ajax/dashboard.html");
	}
	
}


