package org.lanqiao.tjut.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.lanqiao.tjut.bean.TBAdminsBean;
import org.lanqiao.tjut.dao.ITBAdminBeanDao;
import org.lanqiao.tjut.db.DBFactory;

/*
	 * Model层，进行业务逻辑处理
	 * @auther Administrator
	 */
  public class UserLoginModel{
     
	  /*
	   * 登录验证信息查询
	   * @param adminB
	   *       登录信息
	   * @return 返回查询结果集      
	   */
	  
 public List<TBAdminsBean> getUserLoginInfo(TBAdminsBean adminB){
	 //组织数据查询操作sqlsession对象
	 SqlSession  sqlS = DBFactory.getDBDriverInstance().getSqlSession();
	 
	 //获取dao层对象
	 ITBAdminBeanDao adminD = sqlS.getMapper(ITBAdminBeanDao.class);

      //调用db层执行sql语句
	  List<TBAdminsBean> lstAdmins = adminD.getUserLoginInfo(adminB);
     
      //返回登录查询结果信息集
	  return lstAdmins;
 
 }
	  
	  
	  
  }





	

