package action.ckglManage;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import util.ImageResizer;
import model.Goods;
import model.Inbill;
import model.Ingoods;

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
public class sprkController extends Controller{
	
	/**
	 * 商品入库方法：
	 */
	public void index(){
		render("/business/ckglManage/sprk/sprk.jsp");
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
	 * 商品入库创建商品
	 */
	public void goods_add(){
		String code = getPara("code");
		List<Record> seasonlist = Db.find("select * from season order by id");
		setAttr("code", code);
		setAttr("seasonlist", seasonlist);
		render("/business/ckglManage/sprk/goods_add.jsp");
	}
	
	/**
	 * 商品入库创建商品
	 */
	public void goods_save(){
		boolean flag = Db.tx(new IAtom() {
			boolean save_flag = true;
			@Override
			public boolean run() throws SQLException {
				// TODO Auto-generated method stub
				try {
					//处理上传文件
					List<UploadFile> list_uf = getFiles(); //保存文件
					String picpath="";
					if(list_uf!=null){
						String time = System.currentTimeMillis()+"";
						for(UploadFile uf:list_uf){
							ImageResizer.resizeImage(uf.getFile(),PathKit.getWebRootPath()+"/upload/"+time+"_"+uf.getFileName(),240,320);
							picpath = "upload/"+time+"_"+uf.getFileName();
						}
					}
					//处理上传文件
					String code = getPara("code");
					String showname = getPara("showname");
					int price = getParaToInt("price");
					String sex = getPara("sex");
					int season_id = getParaToInt("season_id");
					String remark = getPara("remark");
					Goods good = new Goods();
					good.set("code", code)
					.set("showname", showname)
					.set("picpath", picpath)
					.set("price", price)
					.set("remark", remark)
					.set("sex", sex)
					.set("season_id",season_id)
					.set("addtime", new Date());
					good.save();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					save_flag = false;
					e.printStackTrace();
				}
				return save_flag;
			}
		});
		if(flag==true){
			renderText("操作成功");
		}else{
			renderText("操作失败");
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
		str_add.append("<td><select name=\""+i+"_sizeid\" style=\"width:50px;\">");
		for(Record size:sizelist){
			str_add.append("<option value=\""+size.getInt("id")+"\">"+size.getStr("name")+"</option>");
		}
		str_add.append("</select></td>");
		str_add.append("<td><input type=\"text\" style=\"width:50px\" id=\""+i+"_price\" name=\""+i+"_price\" value=\"0\"/></td>");
		str_add.append("<td><input type=\"text\" style=\"width:50px\" id=\""+i+"_amount\" name=\""+i+"_amount\" value=\"0\"/></td>");
		str_add.append("<td><a href=\"javascript:void(0)\" onclick=\"del_sprk("+i+")\">删除</a></td>");
		str_add.append("</tr>");
		setSessionAttr(LocalConstant.SESSION_MAP, map);
		//System.out.println(str_add.toString());
		renderText(str_add.toString());
	}
	
	public void goods_sj(){
		String code = getPara("code");
		if(code!=null){
			Goods good = Goods.dao.findFirst("select * from goods where code = '"+code+"'");
			good.set("zt", 0);
			good.update();
		}
		render(DwzRender.success());
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
	
	public void rkd_save(){
		boolean flag = Db.tx(new IAtom() {
			boolean save_flag = true;
			@Override
			public boolean run() throws SQLException {
				// TODO Auto-generated method stub
				try {
					//处理上传文件
					String name = getPara("rkd_name");
					int sum = getParaToInt("sum");
					int total = getParaToInt("total");
					Inbill bill = new Inbill();
					bill.set("name", name)
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
					    Ingoods goods = new Ingoods();
					    goods.set("billid", billid)
					    .set("goodsid", goodsid)
					    .set("sizeid", sizeid)
					    .set("price", price)
					    .set("amount", amount);
					    Goods good = Goods.dao.findById(goodsid);
					    good.set("size"+sizeid, good.getInt("size"+sizeid)+amount);
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
			dwz.setMessage("入库成功");
			render(dwz);
		}else{
			dwz.setStatusCode("300");
			dwz.setMessage("入库失败");
			render(dwz);
		}
	}
	
	
}
