package handler;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

import com.jfinal.handler.Handler;
import common.LocalConstant;

public class SessionHandler extends Handler {
	private Properties prop;
	
	public SessionHandler(Properties ps) {
		this.prop = ps;
	}
	
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		String filters = prop.getProperty("filterUrl");
		String[] filter = filters.split(",");
		HttpSession session = request.getSession();
		boolean validdaor = true;
		for(String s:filter){
			String url = "/"+s+"/";
			if(target.startsWith(url)){
				validdaor = false;
				break;
			}
		}
		if(validdaor){
			User user = (User)session.getAttribute(LocalConstant.SESSION_USER);
			if(user==null){
				target = "/main/timeOut";
			}
		}
		nextHandler.handle(target, request, response, isHandled);
	}
}
