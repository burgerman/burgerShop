package org.lanqiao.tjut.bean;

public class TBAdminsBean {
	
	
	
	
	private String admins_name;
	private String admins_psw;
	
	
	
	public String getAdmins_name() {
		return admins_name;
	}
	public void setAdmins_name(String admins_name) {
		this.admins_name = admins_name;
	}
	public String getAdmins_psw() {
		return admins_psw;
	}
	public void setAdmins_psw(String admins_psw) {
		this.admins_psw = admins_psw;
	}
	
	
	public String toString(){
		
		return "TBAdminsBean [admins_name="+ admins_name +",admins_psw="+ admins_psw +"]";
	}


}
