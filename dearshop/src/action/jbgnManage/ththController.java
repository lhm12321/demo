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
import model.Thbill;
import model.Thgoods;

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
public class ththController extends Controller{
	
	/**
	 * 商品入库方法：
	 */
	public void index(){
		render("/business/jbgnManage/thth/thth.jsp");
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
	public void th_add(){
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
		str_add.append("<tr id=\""+i+"_thtr\">");
		str_add.append("<td><input type=\"hidden\" name=\""+i+"_thid\" value=\""+good.getInt("id")+"\" />");
		str_add.append("<img src=\""+good.get("picpath")+"\" style=\"margin:3px;margin-left:20px;width:30px;height:40px;float:left\" />");
		str_add.append("<div style=\"float:left;margin:3px;margin-left:20px;\"><span style=\"font-weight:bold;\">"+code+"</span><br><br><span>"+good.get("showname")+"</span></div></td>");
		str_add.append("<td>"+good.getInt("price")+"</td>");
		str_add.append("<td><select name=\""+i+"_thsizeid\" style=\"width:120px;\">");
		for(Record size:sizelist){
			str_add.append("<option value=\""+size.getInt("id")+"\">"+size.getStr("name")+"</option>");
		}
		str_add.append("</select></td>");
		str_add.append("<td><input type=\"text\" style=\"width:50px\" id=\""+i+"_thprice\" name=\""+i+"_thprice\" value=\"0\"/></td>");
		str_add.append("<td><input type=\"text\" style=\"width:50px\" id=\""+i+"_thamount\" name=\""+i+"_thamount\" value=\"0\"/></td>");
		str_add.append("<td><a href=\"javascript:void(0)\" onclick=\"del_th("+i+")\">删除</a></td>");
		str_add.append("</tr>");
		setSessionAttr(LocalConstant.SESSION_MAP, map);
		//System.out.println(str_add.toString());
		renderText(str_add.toString());
	}
	
	/**
	 * 增加商品
	 */
	public void th_del(){
		String px = getPara("px");
		Map map = getSessionAttr(LocalConstant.SESSION_MAP);
		map.remove(px);
		setSessionAttr(LocalConstant.SESSION_MAP, map);
		renderText("ok");
	}
	
	/**
	 * 增加商品
	 */
	public void hq_add(){
		String code = getPara("code");
		String i = getPara("i");
		//System.out.println(code+"-----"+i);
		StringBuffer str_add = new StringBuffer();
		Map map = getSessionAttr(LocalConstant.SESSION_MAP1);
		if(i.equals("1")){
			map=new HashMap();
		}
		map.put(i, i);
		Goods good = Goods.dao.findFirst("select * from Goods where code="+code);
		List<Record> sizelist= Db.find("select * from size order by id");
		str_add.append("<tr id=\""+i+"_hqtr\">");
		str_add.append("<td><input type=\"hidden\" name=\""+i+"_hqid\" value=\""+good.getInt("id")+"\" />");
		str_add.append("<img src=\""+good.get("picpath")+"\" style=\"margin:3px;margin-left:20px;width:30px;height:40px;float:left\" />");
		str_add.append("<div style=\"float:left;margin:3px;margin-left:20px;\"><span style=\"font-weight:bold;\">"+code+"</span><br><br><span>"+good.get("showname")+"</span></div></td>");
		str_add.append("<td>"+good.getInt("price")+"</td>");
		str_add.append("<td><select name=\""+i+"_hqsizeid\" style=\"width:120px;\">");
		for(Record size:sizelist){
			str_add.append("<option value=\""+size.getInt("id")+"\">"+size.getStr("name")+"    (剩余"+good.getInt("size"+size.getInt("id"))+"件)</option>");
		}
		str_add.append("</select></td>");
		str_add.append("<td><input type=\"text\" style=\"width:50px\" id=\""+i+"_hqprice\" name=\""+i+"_hqprice\" value=\"0\"/></td>");
		str_add.append("<td><input type=\"text\" style=\"width:50px\" id=\""+i+"_hqamount\" name=\""+i+"_hqamount\" value=\"0\"/></td>");
		str_add.append("<td><a href=\"javascript:void(0)\" onclick=\"del_hq("+i+")\">删除</a></td>");
		str_add.append("</tr>");
		setSessionAttr(LocalConstant.SESSION_MAP1, map);
		//System.out.println(str_add.toString());
		renderText(str_add.toString());
	}
	
	
	public void hq_del(){
		String px = getPara("px");
		Map map = getSessionAttr(LocalConstant.SESSION_MAP1);
		map.remove(px);
		setSessionAttr(LocalConstant.SESSION_MAP1, map);
		renderText("ok");
	}
	
	
	public void hqd_save(){
		boolean flag = Db.tx(new IAtom() {
			boolean save_flag = true;
			@Override
			public boolean run() throws SQLException {
				// TODO Auto-generated method stub
				try {
					//处理上传文件
					String name = getPara("name");
					int total = getParaToInt("total");
					Thbill bill = new Thbill();
					bill.set("name", name)
					.set("total", total)
					.set("addtime",new Date());
					bill.save();
					Map map = getSessionAttr(LocalConstant.SESSION_MAP);
					Map map1 = getSessionAttr(LocalConstant.SESSION_MAP1);
					int billid = bill.getInt("id");
					for (Object key : map.keySet()) {
					    String value = map.get(key.toString()).toString();
					    int price = getParaToInt(value+"_thprice");
					    int amount = getParaToInt(value+"_thamount");
					    int sizeid = getParaToInt(value+"_thsizeid");
					    int goodsid = getParaToInt(value+"_thid");
					    Thgoods goods = new Thgoods();
					    goods.set("billid", billid)
					    .set("goodsid", goodsid)
					    .set("sizeid", sizeid)
					    .set("price", price)
					    .set("lx", 0)
					    .set("amount", amount);
					    Goods good = Goods.dao.findById(goodsid);
				    	good.set("size"+sizeid, good.getInt("size"+sizeid)+amount);
					    good.update();
					    goods.save();
					    //System.out.println(value+"---");
					}
					for (Object key : map1.keySet()) {
					    String value = map1.get(key.toString()).toString();
					    int price = getParaToInt(value+"_hqprice");
					    int amount = getParaToInt(value+"_hqamount");
					    int sizeid = getParaToInt(value+"_hqsizeid");
					    int goodsid = getParaToInt(value+"_hqid");
					    Thgoods goods = new Thgoods();
					    goods.set("billid", billid)
					    .set("goodsid", goodsid)
					    .set("sizeid", sizeid)
					    .set("price", price)
					    .set("lx", 1)
					    .set("amount", amount);
					    Goods good = Goods.dao.findById(goodsid);
					    int sa = good.getInt("size"+sizeid)+amount;
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
					setSessionAttr(LocalConstant.SESSION_MAP1, new HashMap());
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
