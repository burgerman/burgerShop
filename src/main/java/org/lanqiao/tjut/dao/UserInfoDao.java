package org.lanqiao.tjut.dao;

import java.util.List;

import org.lanqiao.tjut.bean.TBUserBean;

public interface UserInfoDao {
	
	public List<TBUserBean> selectUserInfoByAccount(TBUserBean userB);
	
	
    public List<TBUserBean> updateUserInfoByAccount(TBUserBean userB);
}
