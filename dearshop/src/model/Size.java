package model;

import java.util.Map;

import com.jfinal.plugin.activerecord.Model;
@SuppressWarnings("serial")
public class Size extends Model<Size>{
	public static final Size dao = new Size();
	
	public Map<String,Object> map = getAttrs();

	public Map<String,Object> getMap() {
		return map;
	}
}
