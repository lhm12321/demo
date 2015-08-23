package model;

import java.util.Map;

import com.jfinal.plugin.activerecord.Model;
@SuppressWarnings("serial")
public class User extends Model<User>{
	public static final User dao = new User();
	
	public Map<String,Object> map = getAttrs();

	public Map<String,Object> getMap() {
		return map;
	}
}
