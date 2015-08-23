package model;

import java.util.Map;

import com.jfinal.plugin.activerecord.Model;
@SuppressWarnings("serial")
public class Thgoods extends Model<Thgoods>{
	public static final Thgoods dao = new Thgoods();
	
	public Map<String,Object> map = getAttrs();

	public Map<String,Object> getMap() {
		return map;
	}
}
