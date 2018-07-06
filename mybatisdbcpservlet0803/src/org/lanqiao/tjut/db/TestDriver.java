package org.lanqiao.tjut.db;

import java.util.List;  

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.lanqiao.tjut.bean.TBAdminsBean;
import org.lanqiao.tjut.dao.ITBAdminBeanDao;

public class TestDriver {	
	@Test
	public void testDao() {
		// ��֯���ݲ�ѯ����sqlsession����
		SqlSession sqlS = DBFactory.getDBDriverInstance().getSqlSession();
		// ��ȡdao�����
		ITBAdminBeanDao adminD = sqlS.getMapper(ITBAdminBeanDao.class);
		TBAdminsBean adminB = new TBAdminsBean();
		adminB.setAdmins_name("admin");
		adminB.setAdmins_psw("admin");
		// ����db��ִ��sql���
		List<TBAdminsBean> lstAdmins = adminD.getUserLoginInfo(adminB);
		System.out.println("��ʾ��ѯ�����");
		// ������ѯ�����
		for (TBAdminsBean stu : lstAdmins) {
			System.out.println(stu.toString());
		}
	}
}
