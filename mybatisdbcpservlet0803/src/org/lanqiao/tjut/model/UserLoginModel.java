package org.lanqiao.tjut.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.lanqiao.tjut.bean.TBAdminsBean;
import org.lanqiao.tjut.dao.ITBAdminBeanDao;
import org.lanqiao.tjut.db.DBFactory;

/*
	 * Model�㣬����ҵ���߼�����
	 * @auther Administrator
	 */
  public class UserLoginModel{
     
	  /*
	   * ��¼��֤��Ϣ��ѯ
	   * @param adminB
	   *       ��¼��Ϣ
	   * @return ���ز�ѯ�����      
	   */
	  
 public List<TBAdminsBean> getUserLoginInfo(TBAdminsBean adminB){
	 //��֯���ݲ�ѯ����sqlsession����
	 SqlSession  sqlS = DBFactory.getDBDriverInstance().getSqlSession();
	 
	 //��ȡdao�����
	 ITBAdminBeanDao adminD = sqlS.getMapper(ITBAdminBeanDao.class);

      //����db��ִ��sql���
	  List<TBAdminsBean> lstAdmins = adminD.getUserLoginInfo(adminB);
     
      //���ص�¼��ѯ�����Ϣ��
	  return lstAdmins;
 
 }
	  
	  
	  
  }





	

