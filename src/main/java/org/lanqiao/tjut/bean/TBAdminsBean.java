package org.lanqiao.tjut.bean;

public class TBAdminsBean {
	
	
	
	
	private String admin_account;
	private String admin_psw;
	
	
	
	public String getAdmin_account() {
		return admin_account;
	}
	public void setAdmin_account(String admin_account) {
		this.admin_account = admin_account;
	}
	public String getAdmin_psw() {
		return admin_psw;
	}
	public void setAdmin_psw(String admin_psw) {
		this.admin_psw = admin_psw;
	}
	
	
	public String toString(){
		
		return "TBAdminsBean [admin_account="+ admin_account +",admin_psw="+ admin_psw +"]";
	}


}
