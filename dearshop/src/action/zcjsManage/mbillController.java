package action.zcjsManage;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class mbillController extends Controller{
	
	/**
	 * 查看库存方法：
	 */
	public void index(){
		
		// System.out.println("SQL:"+sql.toString());
		List<Tdate> datelist = new ArrayList<Tdate>();
		Date time = new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat df1=new SimpleDateFormat("yyyy年MM月");
		Tdate now = new Tdate("本月",df.format(time));
		datelist.add(now);
		for(int i=1;i<12;i++){
			time.setMonth(time.getMonth()-1);
			Tdate next = new Tdate(df1.format(time),df.format(time));
			datelist.add(next);
		}
		setAttr("datelist",datelist);
		
		String stime = getPara("stime");
		if(stime==null||stime.length()==0){
			stime=df.format(new Date());
		}
		StringBuffer sql = new StringBuffer();
		sql.append("(select id,name,0 sum_in,sum sum_out,total,addtime,'销售单' bz from outbill where DATE_FORMAT(addtime,'%Y-%m')='" + stime + "')");
		sql.append("union");
		sql.append("(select id,name,sum sum_in,0 sum_out,total,addtime,'入库单' bz from inbill  where DATE_FORMAT(addtime,'%Y-%m')='" + stime + "')");
		sql.append("union");
		sql.append("(select id,name,(SELECT sum(amount) from thgoods where billid = a.id and lx = 1 ) sum_in,(SELECT sum(amount) from thgoods where billid = a.id and lx = 0 ) sum_out,total,addtime,'退换单' bz from thbill a where DATE_FORMAT(a.addtime,'%Y-%m')='" + stime + "')");
		List<Record> dbill = Db.find(sql.toString());
		//统计
		Record in = Db.findFirst("select sum(total) total,sum(sum) sum from inbill where DATE_FORMAT(addtime,'%Y-%m')='" + stime + "'");
		Record out = Db.findFirst("select sum(total) total,sum(sum) sum from outbill where DATE_FORMAT(addtime,'%Y-%m')='" + stime + "'");
		Record thzj = Db.findFirst("select sum(total) total from thbill where  DATE_FORMAT(addtime,'%Y-%m')='" + stime + "'");
		Record thsum = Db.findFirst("select sum(a.amount) sum from thgoods a left join thbill b on a.billid = b.id where a.lx = 0 and DATE_FORMAT(b.addtime,'%Y-%m')='" + stime + "'");
		Record hqsum = Db.findFirst("select sum(a.amount) sum from thgoods a left join thbill b on a.billid = b.id where a.lx = 1 and DATE_FORMAT(b.addtime,'%Y-%m')='" + stime + "'");
		setAttr("cs_sum", (out.get("sum")==null?0:out.get("sum"))+(hqsum.get("sum")==null?"":"("+hqsum.get("sum")+")"));
		setAttr("rk_sum", (in.get("sum")==null?0:in.get("sum"))+(thsum.get("sum")==null?"":"("+thsum.get("sum")+")"));
		setAttr("cs_total", (out.get("total")==null?0:out.get("total"))+(thzj.get("total")==null?"":"("+thzj.get("total")+")"));
		setAttr("rk_total", in.get("total")==null?0:in.get("total"));
		
		setAttr("dbill", dbill);
		render("/business/zcjsManage/mbill/mbill.jsp");
	}

}
