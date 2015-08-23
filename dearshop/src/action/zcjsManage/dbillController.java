package action.zcjsManage;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import util.ImageResizer;
import util.Tdate;
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
public class dbillController extends Controller{
	
	/**
	 * 查看库存方法：
	 */
	public void index(){
		
		// System.out.println("SQL:"+sql.toString());
		List<Tdate> datelist = new ArrayList<Tdate>();
		Date time = new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df1=new SimpleDateFormat("MM月dd日");
		Tdate now = new Tdate("今天",df.format(time));
		datelist.add(now);
		for(int i=1;i<30;i++){
			time = new Date(time.getTime()-24*60*60*1000);
			Tdate next = new Tdate(df1.format(time),df.format(time));
			datelist.add(next);
		}
		setAttr("datelist",datelist);
		
		String stime = getPara("stime");
		if(stime==null||stime.length()==0){
			stime=df.format(new Date());
		}
		StringBuffer sql = new StringBuffer();
		sql.append("(select d.showname,d.code,d.picpath,a.price,c.name,a.amount,b.addtime,'出售' bz from outgoods a left join outbill b on a.billid = b.id left join size c on a.sizeid = c.id left join goods d on d.id = a.goodsid  where DATE_FORMAT(b.addtime,'%Y-%m-%d')='" + stime + "')");
		sql.append("union");
		sql.append("(select d.showname,d.code,d.picpath,a.price,c.name,a.amount,b.addtime,'入库' bz from ingoods a left join inbill b on a.billid = b.id left join size c on a.sizeid = c.id left join goods d on d.id = a.goodsid  where DATE_FORMAT(b.addtime,'%Y-%m-%d')='" + stime + "')");
		sql.append("union");
		sql.append("(select d.showname,d.code,d.picpath,a.price,c.name,a.amount,b.addtime,'退货' bz from thgoods a left join thbill b on a.billid = b.id left join size c on a.sizeid = c.id left join goods d on d.id = a.goodsid  where DATE_FORMAT(b.addtime,'%Y-%m-%d')='" + stime + "' and a.lx = 0)");
		sql.append("union");
		sql.append("(select d.showname,d.code,d.picpath,a.price,c.name,a.amount,b.addtime,'换取' bz from thgoods a left join thbill b on a.billid = b.id left join size c on a.sizeid = c.id left join goods d on d.id = a.goodsid  where DATE_FORMAT(b.addtime,'%Y-%m-%d')='" + stime + "' and a.lx = 1)");
		List<Record> dbill = Db.find(sql.toString());
		//统计
		Record in = Db.findFirst("select sum(total) total,sum(sum) sum from inbill where DATE_FORMAT(addtime,'%Y-%m-%d')='" + stime + "'");
		Record out = Db.findFirst("select sum(total) total,sum(sum) sum from outbill where DATE_FORMAT(addtime,'%Y-%m-%d')='" + stime + "'");
		Record thzj = Db.findFirst("select sum(total) total from thbill where  DATE_FORMAT(addtime,'%Y-%m-%d')='" + stime + "'");
		Record thsum = Db.findFirst("select sum(a.amount) sum from thgoods a left join thbill b on a.billid = b.id where a.lx = 0 and DATE_FORMAT(b.addtime,'%Y-%m-%d')='" + stime + "'");
		Record hqsum = Db.findFirst("select sum(a.amount) sum from thgoods a left join thbill b on a.billid = b.id where a.lx = 1 and DATE_FORMAT(b.addtime,'%Y-%m-%d')='" + stime + "'");
		setAttr("cs_sum", (out.get("sum")==null?0:out.get("sum"))+(hqsum.get("sum")==null?"":"("+hqsum.get("sum")+")"));
		setAttr("rk_sum", (in.get("sum")==null?0:in.get("sum"))+(thsum.get("sum")==null?"":"("+thsum.get("sum")+")"));
		setAttr("cs_total", (out.get("total")==null?0:out.get("total"))+(thzj.get("total")==null?"":"("+thzj.get("total")+")"));
		setAttr("rk_total", in.get("total")==null?0:in.get("total"));
		
		setAttr("dbill", dbill);
		render("/business/zcjsManage/dbill/dbill.jsp");
	}

}
