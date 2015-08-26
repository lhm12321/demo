package single;

import com.jfinal.ext.kit.Reflect;
import config.Config;

public class RunNow {

	public static void main(String[] args) {
		Config config = new Config();
		Reflect.on("com.jfinal.core.Config").call("configJFinal", config);
		// start your single job
	}
}
