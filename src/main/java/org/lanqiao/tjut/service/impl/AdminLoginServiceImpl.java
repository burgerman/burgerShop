package org.lanqiao.tjut.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.lanqiao.tjut.bean.TBAdminsBean;
import org.lanqiao.tjut.dao.AdminLoginDao;
import org.lanqiao.tjut.service.AdminLoginService;
import org.springframework.stereotype.Service;


@Service("adminS")
public class AdminLoginServiceImpl implements AdminLoginService{

	@Resource
	private AdminLoginDao adminD;
	
	@Override
	public List<TBAdminsBean> getAdminLoginInfo(TBAdminsBean adminB) {
        
		List<TBAdminsBean> lstAdmins = null;
		    lstAdmins = adminD.getAdminLoginInfo(adminB);
		
		return lstAdmins;
	}
	
}
