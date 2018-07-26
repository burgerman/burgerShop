package org.lanqiao.tjut.bean;

public class TBOperatorBean {
	
	private String opr_account;
	private String opr_psw;
	public String getOpr_account() {
		return opr_account;
	}
	public void setOpr_account(String opr_account) {
		this.opr_account = opr_account;
	}
	public String getOpr_psw() {
		return opr_psw;
	}
	public void setOpr_psw(String opr_psw) {
		this.opr_psw = opr_psw;
	}
	@Override
	public String toString() {
		return "TBOperatorBean [opr_account=" + opr_account + ", opr_psw=" + opr_psw + "]";
	}
	
	 
}