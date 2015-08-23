package action;

import java.util.HashMap;
import java.util.Map;

import model.User;

import com.jfinal.core.Controller;
import common.LocalConstant;

/**
 * CommonController
 */
public class LoginController extends Controller {
	public void index() {
		String username = getPara("username");
		String password = getPara("password");
		User user = User.dao.findFirst("select * from user where name = ? and password = ?", username,password);
		Map map = new HashMap();
		setSessionAttr(LocalConstant.SESSION_MAP, map);
		if(user!=null){
			setSessionAttr(LocalConstant.SESSION_USER, user);
			if(getPara("loginType")==null){//非弹出框登陆时
				redirect("/main/");
			}else{
				//render(new DwzRender("200","登陆成功"));
				redirect("/main/");
			}
		}else{
			if(getPara("loginType")==null){//非弹出框登陆时
				setAttr("error","用户不存在或用户名或密码错误");
				render("/login/login.jsp");
			}else{
				setAttr("error","用户不存在或用户名或密码错误");
				render("/login/login.jsp");
			}
			
		}
	}
	
	public void logout() {
		getSession().removeAttribute(LocalConstant.SESSION_USER);
		getSession().invalidate();
		redirect("/login/login.jsp");
	}
}
