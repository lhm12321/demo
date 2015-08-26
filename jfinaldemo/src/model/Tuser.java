package model;

import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Tuser extends Model<Tuser> {
	public static final Tuser dao = new Tuser();

	public Map<String, Object> map = getAttrs();

	public Map<String, Object> getMap() {
		return map;
	}

	public List<Tuser> findAll() {
		return find("select * from Tuser order by id asc");
	}
}
