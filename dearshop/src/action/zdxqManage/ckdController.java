package action.zdxqManage;

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
public class ckdController extends Controller{
	
	/**
	 * 查看库存方法：
	 */
	public void index(){
		StringBuffer sql = new StringBuffer();
		sql.append("from outbill where 1=1");
		// System.out.println("SQL:"+sql.toString());
		String starttime = getPara("starttime", "");
		String endtime = getPara("endtime", "");
		if(starttime.length()>0){
			sql.append(" and UNIX_TIMESTAMP(addtime) > UNIX_TIMESTAMP('" + starttime + "') ");
		}
		if(endtime.length()>0){
			sql.append(" and UNIX_TIMESTAMP(addtime) < UNIX_TIMESTAMP('" + endtime + "') ");
		}
		setAttr("listPage", Db.paginate(getParaToInt("pageNum", LocalConstant.PAGE_NUM), getParaToInt("numPerPage", LocalConstant.NUM_PER_PAGE), "select * ", sql.toString()));
		render("/business/zdxqManage/ckd/ckd.jsp");
	}
	
	public void ckd_xq(){
		String id = getPara("id");
		List<Record> outgoods = Db.find("select b.code,b.showname,b.picpath,b.price,a.price price_t,a.amount,c.name from outgoods a left join goods b on a.goodsid = b.id left join size c on a.sizeid = c.id where a.billid = "+id);
		setAttr("outgoods", outgoods);
		render("/business/zdxqManage/ckd/ckd_xq.jsp");
	}
}
