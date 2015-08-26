package model;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.jfinal.plugin.activerecord.Model;

/**
 * Blog model.

将表结构放在此，消除记忆负担
mysql> desc blog;
+---------+--------------+------+-----+---------+----------------+
| Field   | Type         | Null | Key | Default | Extra          |
+---------+--------------+------+-----+---------+----------------+
| id      | int(11)      | NO   | PRI | NULL    | auto_increment |
| title   | varchar(200) | NO   |     | NULL    |                |
| content | mediumtext   | NO   |     | NULL    |                |
+---------+--------------+------+-----+---------+----------------+

数据库字段名建议使用驼峰命名规则，便于与 java 代码保持一致，如字段名： userId
 */

@SuppressWarnings("serial")
public class Tblog extends Model<Tblog>{
	public static final Tblog dao = new Tblog();
	
	public Map<String,Object> map = getAttrs();

	public Map<String,Object> getMap() {
		return map;
	}
	
	public List<Tblog> findAll() {
		return find("select * from Tblog order by id asc");
	}
	
	public boolean saveauto() {
		return dao.set("id",UUID.randomUUID()).save();
	}
	
}
