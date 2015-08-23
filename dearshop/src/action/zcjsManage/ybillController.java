package action.zcjsManage;

import java.util.ArrayList;
import java.util.List;

import util.Tbill;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * 经济体管理
 * @author Administrator
 *
 */
public class ybillController extends Controller{
	
	/**
	 * 查看库存方法：
	 */
	public void index(){		
		String stime = getPara("stime");
		List<Tbill> tbilllist = new ArrayList<Tbill>();
		if(stime==null||stime.length()==0){
			stime="2014";
		}
		for(int i=1 ;i<=12;i++){
			tbilllist.add(getTbill(stime,i));
		}
		Record in = Db.findFirst("select sum(total) total,sum(sum) sum from inbill where DATE_FORMAT(addtime,'%Y')='" + stime + "'");
		Record out = Db.findFirst("select sum(total) total,sum(sum) sum from outbill where DATE_FORMAT(addtime,'%Y')='" + stime + "'");
		Record thzj = Db.findFirst("select sum(total) total from thbill where  DATE_FORMAT(addtime,'%Y')='" + stime + "'");
		Record thsum = Db.findFirst("select sum(a.amount) sum from thgoods a left join thbill b on a.billid = b.id where a.lx = 0 and DATE_FORMAT(b.addtime,'%Y')='" + stime + "'");
		Record hqsum = Db.findFirst("select sum(a.amount) sum from thgoods a left join thbill b on a.billid = b.id where a.lx = 1 and DATE_FORMAT(b.addtime,'%Y')='" + stime + "'");
		setAttr("cs_sum", (out.get("sum")==null?0:out.get("sum"))+(hqsum.get("sum")==null?"":"("+hqsum.get("sum")+")"));
		setAttr("rk_sum", (in.get("sum")==null?0:in.get("sum"))+(thsum.get("sum")==null?"":"("+thsum.get("sum")+")"));
		setAttr("cs_total", (out.get("total")==null?0:out.get("total"))+(thzj.get("total")==null?"":"("+thzj.get("total")+")"));
		setAttr("rk_total", in.get("total")==null?0:in.get("total"));
		setAttr("yl", Long.parseLong((out.get("total")==null?0:out.get("total")).toString())-Long.parseLong((in.get("total")==null?0:in.get("total")).toString())+(thzj.get("total")==null?"":"("+thzj.get("total")+")"));
		setAttr("tbilllist", tbilllist);
		render("/business/zcjsManage/ybill/ybill.jsp");
	}
	
	public Tbill getTbill(String time,int i){
		//统计
		String stime;
		if(i<10){
			stime = time+"-0"+i;
		}else{
			stime = time+"-"+i;
		}
		String month = i+"月";
		Record rkd = Db.findFirst("select count(*) sum from inbill where DATE_FORMAT(addtime,'%Y-%m')='" + stime + "'");
		Record ckd = Db.findFirst("select count(*) sum from outbill where DATE_FORMAT(addtime,'%Y-%m')='" + stime + "'");
		Record thd = Db.findFirst("select count(*) sum from thbill where DATE_FORMAT(addtime,'%Y-%m')='" + stime + "'");
		Record in = Db.findFirst("select sum(total) total,sum(sum) sum from inbill where DATE_FORMAT(addtime,'%Y-%m')='" + stime + "'");
		Record out = Db.findFirst("select sum(total) total,sum(sum) sum from outbill where DATE_FORMAT(addtime,'%Y-%m')='" + stime + "'");
		Record thzj = Db.findFirst("select sum(total) total from thbill where  DATE_FORMAT(addtime,'%Y-%m')='" + stime + "'");
		Record thsum = Db.findFirst("select sum(a.amount) sum from thgoods a left join thbill b on a.billid = b.id where a.lx = 0 and DATE_FORMAT(b.addtime,'%Y-%m')='" + stime + "'");
		Record hqsum = Db.findFirst("select sum(a.amount) sum from thgoods a left join thbill b on a.billid = b.id where a.lx = 1 and DATE_FORMAT(b.addtime,'%Y-%m')='" + stime + "'");
		String rkd_s = rkd.getLong("sum").toString();
		String ckd_s = ckd.getLong("sum").toString();
		String thd_s = thd.getLong("sum").toString();
		String cs_sum = (out.get("sum")==null?0:out.get("sum"))+(hqsum.get("sum")==null?"":"("+hqsum.get("sum")+")");
		String rk_sum = (in.get("sum")==null?0:in.get("sum"))+(thsum.get("sum")==null?"":"("+thsum.get("sum")+")");
		String cs_total = (out.get("total")==null?0:out.get("total"))+(thzj.get("total")==null?"":"("+thzj.get("total")+")");
		String rk_total = (in.get("total")==null?0:in.get("total")).toString();
		String yl = Integer.parseInt((out.get("total")==null?0:out.get("total")).toString())-Integer.parseInt((in.get("total")==null?0:in.get("total")).toString())+(thzj.get("total")==null?"":"("+thzj.get("total")+")");
		return new Tbill(month,ckd_s,rkd_s,thd_s,cs_sum,rk_sum,cs_total,rk_total,yl);
	}
}
