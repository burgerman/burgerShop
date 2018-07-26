package org.lanqiao.tjut.dao;

import java.util.List;

import org.lanqiao.tjut.bean.TBOperatorBean;

public interface OpLoginDao {
	
	public List<TBOperatorBean> getOperLoginInfo(TBOperatorBean operB);

}
