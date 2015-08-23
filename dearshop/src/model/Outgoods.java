package model;

import java.util.Map;

import com.jfinal.plugin.activerecord.Model;
@SuppressWarnings("serial")
public class Outgoods extends Model<Outgoods>{
	public static final Outgoods dao = new Outgoods();
	
	public Map<String,Object> map = getAttrs();

	public Map<String,Object> getMap() {
		return map;
	}
}
