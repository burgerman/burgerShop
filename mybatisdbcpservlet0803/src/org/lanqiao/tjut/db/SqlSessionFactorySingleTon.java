package org.lanqiao.tjut.db;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * ����һ������ģʽ��SqlSessionFactory����
 * 
 */
public class SqlSessionFactorySingleTon {
	// ����һ����̬��ʵ������
	public static SqlSessionFactory factory = null;

	// ˽�й��췽��
	private SqlSessionFactorySingleTon() {
	}

	/**
	 * ��ȡdbcp����Դ����
	 * 
	 * @return ����Դ����
	 */
	public static SqlSessionFactory getSqlSessionFactory() {
		// ��һ�γ�ʼ������
		if (factory == null) {
			try {
				// ��ȡ�����ļ�����
				Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
				// ����factoryʵ����������
				factory = new SqlSessionFactoryBuilder().build(reader);
			} catch (Exception e) {
				// �쳣����SqlSessionFactory�쳣
				System.out.println("����SqlSessionFactory�쳣��" + e.getMessage());
			}
		}
		return factory;
	}
}
