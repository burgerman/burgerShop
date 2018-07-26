package org.lanqiao.tjut.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.lanqiao.tjut.bean.TBUserBean;
import org.lanqiao.tjut.dao.UserInfoDao;
import org.springframework.stereotype.Service;

@Service("userS")
public class UserInfoServiceImpl {
	
	@Resource
	private UserInfoDao userD;
	
    public List<TBUserBean> queryUserInfoByAccount(TBUserBean userB) {
    	
    	List<TBUserBean> lstUser = null;
    	//调用dao层的mybatis进行数据查询操作
    	lstUser = userD.selectUserInfoByAccount(userB);
    	return lstUser;
    }
}
