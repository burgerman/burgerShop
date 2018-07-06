package org.lanqiao.tjut.db;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

public class DBCPDataSourceFactory implements DataSourceFactory {
	private BasicDataSource datasource = null;

	public DBCPDataSourceFactory() {
		this.datasource = new BasicDataSource();
	}

	@Override
	public DataSource getDataSource() {
		return datasource;
	}

	@Override
	public void setProperties(Properties ps) {
		datasource.setDriverClassName(ps.getProperty("driver"));
		datasource.setUsername(ps.getProperty("username"));
		datasource.setUrl(ps.getProperty("url"));
		datasource.setPassword(ps.getProperty("password"));
		//datasource.setDefaultAutoCommit(ps.getProperty("defaultautocommit", "0").equals("1"));
		datasource.setInitialSize(Integer.parseInt(ps.getProperty("initialSize", "2")));
		datasource.setMaxIdle(Integer.parseInt(ps.getProperty("maxIdle", "0")));
		//datasource.setMaxWaitMillis(Long.parseLong(ps.getProperty("maxwait", "0")));

		/*
		 * maxActive=150 minIdle=2 maxIdle=20 initialSize=3 logAbandoned=true
		 * removeAbandoned=true removeAbandonedTimeout=10 maxWait=1000
		 * timeBetweenEvictionRunsMillis=10000 numTestsPerEvictionRun=10
		 * minEvictableIdleTimeMillis=10000
		 */
	}

}
