package action;

import model.User;

import com.jfinal.core.Controller;
import com.jfinal.ext.render.DwzRender;

import common.LocalConstant;

/**
 * CommonController
 */
public class IndexController extends Controller {
	public void index() {
		String url = "/login/login.jsp";
		User loginUser = (User) getSessionAttr(LocalConstant.SESSION_USER);
		if (loginUser != null) {
			setAttr("truename", loginUser.get("truename"));
			url = "/index/index.jsp";
		}
		render(url);
	}
	
	public void changepwd() {
		String oldpwd = getPara("oldpwd");
		String newpwd = getPara("newpwd");
		User loginUser = (User) getSessionAttr(LocalConstant.SESSION_USER);
		if(oldpwd.equals(loginUser.getStr("password"))){
			loginUser.set("password", newpwd);
			loginUser.update();
			setSessionAttr(LocalConstant.SESSION_USER, loginUser);
			render(new DwzRender("密码修改成功", getPara("navTabId"), getPara("callbackType")));
		}else{
			render(DwzRender.error("老密码错误"));
		}
	}
	
	/**
	 * Session失效,登陆超时
	 */
	public void timeOut() {
		String requestType = getRequest().getHeader("X-Requested-With");
		if (requestType != null && requestType.equals("XMLHttpRequest")) {// 框架链接返回JSON串
			DwzRender timeout = new DwzRender();
			timeout.message("会话超时").statusCode("301");
			render(timeout);
		} else {
			render("/login/login.jsp");
		}
	}
}
