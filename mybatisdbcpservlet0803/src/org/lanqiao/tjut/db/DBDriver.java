package org.lanqiao.tjut.db;

import org.apache.ibatis.session.SqlSession;

public class DBDriver {
	/**
	 * 获取 SqlSession对象
	 * 
	 * @return SqlSession对象
	 */
	public SqlSession getSqlSession() {
		return SqlSessionFactorySingleTon.getSqlSessionFactory().openSession();
	}
}
