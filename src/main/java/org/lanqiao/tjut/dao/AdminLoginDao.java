package org.lanqiao.tjut.dao;

import java.util.List;  

import org.lanqiao.tjut.bean.TBAdminsBean;


public interface AdminLoginDao {
	
	public List<TBAdminsBean> getAdminLoginInfo(TBAdminsBean adminB);
    
	
}
