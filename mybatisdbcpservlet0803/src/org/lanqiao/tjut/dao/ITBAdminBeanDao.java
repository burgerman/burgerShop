package org.lanqiao.tjut.dao;

import java.util.List; 

import org.lanqiao.tjut.bean.TBAdminsBean;

public interface ITBAdminBeanDao {
	/**
	 * 验证登录用户信息
	 * 
	 * @param adminB
	 *            参数实体
	 * @return 用户信息
	 */
	public List<TBAdminsBean> getUserLoginInfo(TBAdminsBean adminB);
}
