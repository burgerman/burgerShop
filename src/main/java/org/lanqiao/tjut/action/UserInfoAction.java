package org.lanqiao.tjut.action;

import java.util.List;

import javax.annotation.Resource;

import org.lanqiao.tjut.bean.TBUserBean;

import org.lanqiao.tjut.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserInfoAction {
	
	@Resource
	private UserInfoService stuS;
	
@ResponseBody	

@RequestMapping("/StudentInfoAjaxQueryAction")	
public List<TBUserBean> selectUserInfoByAccount(TBUserBean userB) {
	
	//调用service进行数据库查询操作
	List<TBUserBean> lstUser = stuS.queryUserInfoByAccount(userB);
	return lstUser;
}


}
