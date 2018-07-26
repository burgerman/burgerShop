package org.lanqiao.tjut.service;

import java.util.List;

import org.lanqiao.tjut.bean.TBUserBean;

public interface UserInfoService {
	
	public List<TBUserBean> queryUserInfoByAccount(TBUserBean userB);

}
