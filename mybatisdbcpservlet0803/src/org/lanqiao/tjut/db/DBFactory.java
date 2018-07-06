package org.lanqiao.tjut.db;

public class DBFactory {

	/**
	 * 简单工程方法：获取DBDriver的实例对象
	 * 
	 * @return DBDriver实例
	 */
	public static DBDriver getDBDriverInstance() {
		return new DBDriver();
	}
}
