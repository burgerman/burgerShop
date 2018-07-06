package org.lanqiao.tjut.db;

import java.util.List;  

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.lanqiao.tjut.bean.TBAdminsBean;
import org.lanqiao.tjut.dao.ITBAdminBeanDao;

public class TestDriver {	
	@Test
	public void testDao() {
		// 组织数据查询操作sqlsession对象
		SqlSession sqlS = DBFactory.getDBDriverInstance().getSqlSession();
		// 获取dao层对象
		ITBAdminBeanDao adminD = sqlS.getMapper(ITBAdminBeanDao.class);
		TBAdminsBean adminB = new TBAdminsBean();
		adminB.setAdmins_name("admin");
		adminB.setAdmins_psw("admin");
		// 调用db层执行sql语句
		List<TBAdminsBean> lstAdmins = adminD.getUserLoginInfo(adminB);
		System.out.println("显示查询结果：");
		// 遍历查询结果集
		for (TBAdminsBean stu : lstAdmins) {
			System.out.println(stu.toString());
		}
	}
}
