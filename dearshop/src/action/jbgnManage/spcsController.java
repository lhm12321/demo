package action.jbgnManage;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import model.Goods;
import model.Outbill;
import model.Outgoods;

import com.jfinal.core.Controller;
import com.jfinal.ext.render.DwzRender;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Record;

import common.LocalConstant;

/**
 * 经济体管理
 * @author Administrator
 *
 */
public class spcsController extends Controller{
	
	/**
	 * 商品入库方法：
	 */
	public void index(){
		render("/business/jbgnManage/spcs/spcs.jsp");
	}
	
	/**
	 * 商品入库编号查询
	 */
	public void cxbh(){
		String code = getPara("code");
		Goods good = Goods.dao.findFirst("select * from goods where code='"+code+"'");
		if(good==null){
			renderText("null");
		}else if(good.getInt("zt")==1){
			renderText("close");
		}else{
			renderText("open");
		}
	}
	
	/**
	 * 增加商品
	 */
	public void good_add(){
		String code = getPara("code");
		String i = getPara("i");
		//System.out.println(code+"-----"+i);
		StringBuffer str_add = new StringBuffer();
		Map map = getSessionAttr(LocalConstant.SESSION_MAP);
		if(i.equals("1")){
			map=new HashMap();
		}
		map.put(i, i);
		Goods good = Goods.dao.findFirst("select * from Goods where code="+code);
		List<Record> sizelist= Db.find("select * from size order by id");
		str_add.append("<tr id=\""+i+"_tr\">");
		str_add.append("<td><input type=\"hidden\" name=\""+i+"_goodsid\" value=\""+good.getInt("id")+"\" />");
		str_add.append("<img src=\""+good.get("picpath")+"\" style=\"margin:3px;margin-left:20px;width:30px;height:40px;float:left\" />");
		str_add.append("<div style=\"float:left;margin:3px;margin-left:20px;\"><span style=\"font-weight:bold;\">"+code+"</span><br><br><span>"+good.get("showname")+"</span></div></td>");
		str_add.append("<td>"+good.getInt("price")+"</td>");
		str_add.append("<td><select name=\""+i+"_sizeid\" style=\"width:150px;\">");
		for(Record size:sizelist){
			str_add.append("<option value=\""+size.getInt("id")+"\">"+size.getStr("name")+"    (剩余"+good.getInt("size"+size.getInt("id"))+"件)</option>");
		}
		str_add.append("</select></td>");
		str_add.append("<td><input type=\"text\" style=\"width:50px\" id=\""+i+"_price\" name=\""+i+"_price\" value=\"0\"/></td>");
		str_add.append("<td><input type=\"text\" style=\"width:50px\" id=\""+i+"_amount\"  name=\""+i+"_amount\" value=\"0\"/></td>");
		str_add.append("<td><a href=\"javascript:void(0)\" onclick=\"del_spcs("+i+")\">删除</a></td>");
		str_add.append("</tr>");
		setSessionAttr(LocalConstant.SESSION_MAP, map);
		//System.out.println(str_add.toString());
		renderText(str_add.toString());
	}
	
	/**
	 * 增加商品
	 */
	public void good_del(){
		String px = getPara("px");
		Map map = getSessionAttr(LocalConstant.SESSION_MAP);
		map.remove(px);
		setSessionAttr(LocalConstant.SESSION_MAP, map);
		renderText("ok");
	}
	
	public void ckd_save(){
		boolean flag = Db.tx(new IAtom() {
			boolean save_flag = true;
			@Override
			public boolean run() throws SQLException {
				// TODO Auto-generated method stub
				try {
					//处理上传文件
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
					String time = df.format(new Date());
					int sum = getParaToInt("sum");
					int total = getParaToInt("total");
					Outbill bill = new Outbill();
					bill.set("name", time)
					.set("sum", sum)
					.set("total", total)
					.set("addtime",new Date());
					bill.save();
					Map map = getSessionAttr(LocalConstant.SESSION_MAP);
					int billid = bill.getInt("id");
					for (Object key : map.keySet()) {
					    String value = map.get(key.toString()).toString();
					    int price = getParaToInt(value+"_price");
					    int amount = getParaToInt(value+"_amount");
					    int sizeid = getParaToInt(value+"_sizeid");
					    int goodsid = getParaToInt(value+"_goodsid");
					    Outgoods goods = new Outgoods();
					    goods.set("billid", billid)
					    .set("goodsid", goodsid)
					    .set("sizeid", sizeid)
					    .set("price", price)
					    .set("amount", amount);
					    Goods good = Goods.dao.findById(goodsid);
					    int sa = good.getInt("size"+sizeid)-amount;
					    if(sa<0){
					    	return false;
					    }else{
					    	good.set("size"+sizeid, good.getInt("size"+sizeid)-amount);
					    }
					    good.update();
					    goods.save();
					    //System.out.println(value+"---");
					}
					setSessionAttr(LocalConstant.SESSION_MAP, new HashMap());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					save_flag = false;
					e.printStackTrace();
				}
				return save_flag;
			}
		});
		DwzRender dwz = DwzRender.closeCurrentAndRefresh("ckkc");
		if(flag==true){
			dwz.message("入库成功");
			render(dwz);
		}else{
			render(DwzRender.error("入库失败，请核对数量！"));
		}
	}
	
	
}
