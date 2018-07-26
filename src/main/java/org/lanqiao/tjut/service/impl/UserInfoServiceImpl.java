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
    	//����dao���mybatis�������ݲ�ѯ����
    	lstUser = userD.selectUserInfoByAccount(userB);
    	return lstUser;
    }
}
