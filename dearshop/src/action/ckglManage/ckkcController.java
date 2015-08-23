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
public class ckkcController extends Controller{
	
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
		sql.append(" and zt = 0");
		setAttr("listPage", Db.paginate(getParaToInt("pageNum", LocalConstant.PAGE_NUM), getParaToInt("numPerPage", LocalConstant.NUM_PER_PAGE), "select * ", sql.toString()));
		List<Record> sizelist = Db.find("select * from size order by id");
		setAttr("sizelist",sizelist);
		render("/business/ckglManage/ckkc/ckkc.jsp");
	}
	
	public void goods_xq(){
		String id = getPara("id");
		String type = getPara("type");
		String code = getPara("code");
		List<Record> sizelist = Db.find("select * from size order by id");
		setAttr("sizelist",sizelist);
		Record good = new Record();
		if(type!=null){
			good = Db.findFirst("select a.* ,b.name sname from goods a left join season b on a.season_id = b.id where a.code =" + code);
		}else{
			good = Db.findFirst("select a.* ,b.name sname from goods a left join season b on a.season_id = b.id where a.id =" + id);
		}
		setAttr("good", good);
		if(type!=null){
			render("/business/ckglManage/ckkc/goods_xq1.jsp");
		}else{
			render("/business/ckglManage/ckkc/goods_xq.jsp");
		}
	}
	
	/**
	 * 创建商品
	 */
	public void goods_add(){
		List<Record> seasonlist = Db.find("select * from season order by id");
		setAttr("seasonlist", seasonlist);
		render("/business/ckglManage/ckkc/goods_edit.jsp");
	}
	
	
	public void goods_edit(){
		String id = getPara("id");
		Goods good = Goods.dao.findById(id);
		List<Record> seasonlist = Db.find("select * from season order by id");
		setAttr("good", good);
		setAttr("seasonlist", seasonlist);
		render("/business/ckglManage/ckkc/goods_edit.jsp");
	}
	
	public void goods_xj(){
		String id = getPara("id","");
		String[] ids = getParaValues("ids");
		if(ids==null){
			Goods good = Goods.dao.findById(id);
			good.set("zt", 1)
			.set("size1", 0)
			.set("size2", 0)
			.set("size3", 0)
			.set("size4", 0)
			.set("size5", 0)
			.set("size6", 0);
			good.update();
		}else{
			for(String u_id:ids){
				Goods good = Goods.dao.findById(u_id);
				good.set("zt", 1)
				.set("size1", 0)
				.set("size2", 0)
				.set("size3", 0)
				.set("size4", 0)
				.set("size5", 0)
				.set("size6", 0);
				good.update();
			}
		}
		render(DwzRender.success());
	}
	
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
					String id = getPara("id","");
					Goods good = new Goods();
					if(id.length()==0){
						good.set("code", code)
						.set("showname", showname)
						.set("picpath", picpath)
						.set("price", price)
						.set("remark", remark)
						.set("sex", sex)
						.set("season_id",season_id)
						.set("addtime", new Date());
						good.save();
					}else{
						good = Goods.dao.findById(id);
						good.set("showname", showname)
						.set("price", price)
						.set("remark", remark)
						.set("sex", sex)
						.set("season_id",season_id)
						.set("addtime", new Date());
						if(picpath.length()>0){
							good.set("picpath", picpath);
						}
						good.update();
					}
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
			dwz.message("操作成功");
			render(dwz);
		}else{
			dwz.message("操作失败").statusCode("300");
			render(dwz);
		}
	}
	
}
