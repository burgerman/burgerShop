package org.lanqiao.tjut.dao;

import java.util.List; 

import org.lanqiao.tjut.bean.TBAdminsBean;

public interface ITBAdminBeanDao {
	/**
	 * ��֤��¼�û���Ϣ
	 * 
	 * @param adminB
	 *            ����ʵ��
	 * @return �û���Ϣ
	 */
	public List<TBAdminsBean> getUserLoginInfo(TBAdminsBean adminB);
}
