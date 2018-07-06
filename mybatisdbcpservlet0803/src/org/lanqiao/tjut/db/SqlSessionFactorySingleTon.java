package org.lanqiao.tjut.db;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 创建一个单例模式的SqlSessionFactory对象
 * 
 */
public class SqlSessionFactorySingleTon {
	// 创建一个静态的实例变量
	public static SqlSessionFactory factory = null;

	// 私有构造方法
	private SqlSessionFactorySingleTon() {
	}

	/**
	 * 获取dbcp数据源对象
	 * 
	 * @return 数据源对象
	 */
	public static SqlSessionFactory getSqlSessionFactory() {
		// 第一次初始化处理
		if (factory == null) {
			try {
				// 读取配置文件内容
				Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
				// 创建factory实例变量对象
				factory = new SqlSessionFactoryBuilder().build(reader);
			} catch (Exception e) {
				// 异常处理SqlSessionFactory异常
				System.out.println("生成SqlSessionFactory异常：" + e.getMessage());
			}
		}
		return factory;
	}
}
