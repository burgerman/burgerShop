package org.lanqiao.tjut.bean;

import java.util.Date;

public class TBUserBean {
	
	
	private String user_id;
	private String user_name;
	private String user_sex;
	private Date user_birthday;
	
	private String user_address;
	private String user_account;

	private Integer user_company;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}

	public Date getUser_birthday() {
		return user_birthday;
	}

	public void setUser_birthday(Date user_birthday) {
		this.user_birthday = user_birthday;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public Integer getUser_company() {
		return user_company;
	}

	public void setUser_company(Integer user_company) {
		this.user_company = user_company;
	}

	@Override
	public String toString() {
		return "TBUserBean [user_id=" + user_id + ", user_name=" + user_name + ", user_sex=" + user_sex
				+ ", user_birthday=" + user_birthday + ", user_address=" + user_address + ", user_account="
				+ user_account + ", user_company=" + user_company + "]";
	}
	
	
	
	

}
