package action.ckglManage;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


import util.ImageResizer;
import model.Goods;

import com.jfinal.core.Controller;
import com.jfinal.ext.render.DwzRender;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;

import common.LocalConstant;

/**
 * 经济体管理
 * @author Administrator
 *
 */
public class xjspController extends Controller{
	
	/**
	 * 查看库存方法：
	 */
	public void index(){
		StringBuffer sql = new StringBuffer();
		String id = getPara("id","");
		String key = getPara("key","");
		sql.append("from goods where 1=1");
		// System.out.println("SQL:"+sql.toString());
		if(key.length()>0){
			sql.append(" and (code like '%"+key+"%' or showname like '%"+key+"%')");
		}
		sql.append(" and zt = 1");
		setAttr("listPage", Db.paginate(getParaToInt("pageNum", LocalConstant.PAGE_NUM), getParaToInt("numPerPage", LocalConstant.NUM_PER_PAGE), "select * ", sql.toString()));
		List<Record> sizelist = Db.find("select * from size order by id");
		setAttr("sizelist",sizelist);
		render("/business/ckglManage/xjsp/xjsp.jsp");
	}
	
	public void goods_xq(){
		String id = getPara("id");
		Record good = Db.findFirst("select a.* ,b.name sname from goods a left join season b on a.season_id = b.id where a.id ="+id);
		setAttr("good", good);
		render("/business/ckglManage/xjsp/goods_xq.jsp");
	}
	

	
	public void goods_sj(){
		String id = getPara("id","");
		String[] ids = getParaValues("ids");
		if(ids==null){
			Goods good = Goods.dao.findById(id);
			good.set("zt", 0);
			good.update();
		}else{
			for(String u_id:ids){
				Goods good = Goods.dao.findById(u_id);
				good.set("zt", 0);
				good.update();
			}
		}
		render(DwzRender.success());
	}
	
}
