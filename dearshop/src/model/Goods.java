package model;

import java.util.Map;

import com.jfinal.plugin.activerecord.Model;
@SuppressWarnings("serial")
public class Goods extends Model<Goods>{
	public static final Goods dao = new Goods();
	
	public Map<String,Object> map = getAttrs();

	public Map<String,Object> getMap() {
		return map;
	}
}
