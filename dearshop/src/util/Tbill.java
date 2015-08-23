package util;

public class Tbill {
	private String month;
	private String ckd;
	private String rkd;
	private String thd;
	private String cs_sum;
	private String rk_sum;
	private String cs_total;
	private String rk_total;
	private String yl;
	
	public Tbill() {

	}
	
	public Tbill(String month, String ckd,String rkd,String thd,String cs_sum,String rk_sum,String cs_total,String rk_total,String yl) {
		this.month = month;
		this.ckd = ckd;
		this.rkd = rkd;
		this.thd = thd;
		this.cs_sum = cs_sum;
		this.rk_sum = rk_sum;
		this.cs_total = cs_total;
		this.rk_total = rk_total;
		this.yl = yl;
		
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getCkd() {
		return ckd;
	}

	public void setCkd(String ckd) {
		this.ckd = ckd;
	}

	public String getRkd() {
		return rkd;
	}

	public void setRkd(String rkd) {
		this.rkd = rkd;
	}

	public String getThd() {
		return thd;
	}

	public void setThd(String thd) {
		this.thd = thd;
	}

	public String getCs_sum() {
		return cs_sum;
	}

	public void setCs_sum(String cs_sum) {
		this.cs_sum = cs_sum;
	}

	public String getRk_sum() {
		return rk_sum;
	}

	public void setRk_sum(String rk_sum) {
		this.rk_sum = rk_sum;
	}

	public String getCs_total() {
		return cs_total;
	}

	public void setCs_total(String cs_total) {
		this.cs_total = cs_total;
	}

	public String getRk_total() {
		return rk_total;
	}

	public void setRk_total(String rk_total) {
		this.rk_total = rk_total;
	}

	public String getYl() {
		return yl;
	}

	public void setYl(String yl) {
		this.yl = yl;
	}
	
}
