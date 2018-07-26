package org.lanqiao.tjut.bean;

import java.util.Date;

public class FeeManagerBean {
	
	private String user_account;
	private String user_name;
	private String user_charge;
	private String user_pay;
	private String user_change;
	private Date user_time;
	private String opr_account;
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_charge() {
		return user_charge;
	}
	public void setUser_charge(String user_charge) {
		this.user_charge = user_charge;
	}
	public String getUser_pay() {
		return user_pay;
	}
	public void setUser_pay(String user_pay) {
		this.user_pay = user_pay;
	}
	public String getUser_change() {
		return user_change;
	}
	public void setUser_change(String user_change) {
		this.user_change = user_change;
	}
	public Date getUser_time() {
		return user_time;
	}
	public void setUser_time(Date user_time) {
		this.user_time = user_time;
	}
	public String getOpr_account() {
		return opr_account;
	}
	public void setOpr_account(String opr_account) {
		this.opr_account = opr_account;
	}
	@Override
	public String toString() {
		return "FeeManagerBean [user_account=" + user_account + ", user_name=" + user_name + ", user_charge="
				+ user_charge + ", user_pay=" + user_pay + ", user_change=" + user_change + ", user_time=" + user_time
				+ ", opr_account=" + opr_account + "]";
	}
	
	
	
	

}
