package interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;
import com.jfinal.ext.render.DwzRender;

/**
 * BlogInterceptor
 */
public class CommonInterceptor implements Interceptor {
	private static Controller c;
	private static String controllerKey;
	private static String actionKey;
	private static String MethodName;
	private static boolean flag = true;

	public void init(ActionInvocation ai) {
		c = ai.getController();
		controllerKey = ai.getControllerKey();
		actionKey = ai.getActionKey();
		MethodName = ai.getMethodName();
	}

	public void intercept(ActionInvocation ai) {
		init(ai);
		setControllerKey();// 设置访问controllerKey
		// flag = true; //hhj_test
		if (flag) {
			ai.invoke();
		} else {
			c.render(DwzRender.error("无此操作权限！"));
		}
	}

	public static void setControllerKey() {
		c.setAttr("controllerKey", handleString(controllerKey));// 去除controllerKey前面的"/"
		if (MethodName.equals("add")) {
			c.setAttr("method", "save");
		} else if (MethodName.equals("edit")) {
			c.setAttr("method", "update");
		}
	}


	private static String handleString(String st) {
		String str = st.substring(1, st.length());
		return str;
	}

}
