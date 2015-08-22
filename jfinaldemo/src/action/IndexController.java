package action;

import model.Tuser;

import com.jfinal.core.Controller;

import common.LocalConstant;

/**
 * CommonController
 */
public class IndexController extends Controller {
	public void index() {
		render("/index.html");
	}
	
	public void changepwd() {
		String oldpwd = getPara("oldpwd");
		String newpwd = getPara("newpwd");
		Tuser user = (Tuser) getSessionAttr(LocalConstant.SESSION_USER);
		if(oldpwd.equals(user.getStr("password"))){
			user.set("password", newpwd);
			user.update();
			setSessionAttr(LocalConstant.SESSION_USER, user);
			render("");
		}else{
			render("");
		}
	}
	
	/**
	 * Session失效,登陆超时
	 */
	public void timeOut() {
		String requestType = getRequest().getHeader("X-Requested-With");
		if (requestType != null && requestType.equals("XMLHttpRequest")) {// 框架链接返回JSON串
			render("/login/login_ajax.html");
		} else {
			render("/login/login.html");
		}
		
	}
	
}
