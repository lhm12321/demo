package action;

import model.Tuser;

import com.jfinal.core.Controller;
import common.LocalConstant;

/**
 * CommonController
 */
public class LoginController extends Controller {
	public void index() {
		String username = getPara("username");
		String password = getPara("password");
		Tuser user = Tuser.dao.findFirst("select * from Tuser where username = ? and password = ?", username,password);
		if(user!=null){
			setSessionAttr(LocalConstant.SESSION_USER, user);
			redirect("/index");
		}else{
			if(username==null){//非弹出框登陆时
				render("/login/login.html");
			}else{
				redirect("/login/error");
			}
			
		}
	}
	
	public void register() {
		render("/login/register.html");
	}
	
	public void registeruser() {
		getModel(Tuser.class).save();
		renderText("");
	}
	
	public void error() {
		setAttr("error","用户不存在或用户名或密码错误");
		render("/login/login.html");
	}
	
	public void logout() {
		getSession().removeAttribute(LocalConstant.SESSION_USER);
		getSession().invalidate();
		render("/login/login.html");
	}
	
	public void lock() {
		Tuser user = getSessionAttr(LocalConstant.SESSION_USER);
		if(user!=null){
			user.set("password", null);
			setSessionAttr(LocalConstant.SESSION_USER, user);
		}
		render("/login/lock.html");
	}
	
	public void unlock() {
		String username = getPara("username");
		String password = getPara("password");
		Tuser user = Tuser.dao.findFirst("select * from Tuser where username = ? and password = ?", username,password);
		if(user!=null){
			setSessionAttr(LocalConstant.SESSION_USER, user);
			redirect("/");
		}else{
			redirect("/login/lock");
		}
	}
	
	public void checkusername() {
		String username = getPara("tuser.username");
		Tuser user = Tuser.dao.findFirst("select * from Tuser where username = ? ", username);
		if(user!=null){
			renderText("false");
		}else{
			renderText("true");
		}
	}
	
	public void existname() {
		String username = getPara("username");
		Tuser user = Tuser.dao.findFirst("select * from Tuser where username = ? ", username);
		if(user!=null){
			renderText("true");
		}else{
			renderText("false");
		}
	}
	
	public void forgotpassword() {
		render("/login/forgotpassword.html");
	}
	
	public void reset() {
		redirect("/login/logout");
	}
	
}
