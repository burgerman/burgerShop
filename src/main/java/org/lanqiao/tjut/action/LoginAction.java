package org.lanqiao.tjut.action;
import javax.annotation.Resource;  

import javax.servlet.http.HttpServletRequest;

import org.lanqiao.tjut.bean.TBAdminsBean;
import org.lanqiao.tjut.bean.TBOperatorBean;
import org.lanqiao.tjut.bean.TBUserBean;
import org.lanqiao.tjut.service.AdminLoginService;
import org.lanqiao.tjut.service.OpLoginService;
import org.lanqiao.tjut.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;





@Controller
public class LoginAction{

	
	
	
	
	@Resource
	private OpLoginService operS;	
	@Resource
	private AdminLoginService adminS;
	@Resource
	private UserInfoService userS;
		
	@RequestMapping("/LoginAction")
	public String doLogin(HttpServletRequest request,String txtNick, TBAdminsBean adminB, TBOperatorBean operB, TBUserBean userB) {
		// 调用service层进行管理员登录业务逻辑处理
		// 获取登录用户信息
		
		
		if(txtNick.equals(0)){
		// 跳转到登录成功的页面,页面跳转之后的代码不会再被执行
		// 判定是否登录成功	
	     adminS.getAdminLoginInfo(adminB);
			
	   
		return ("adminLogined");
		} 
		if(txtNick.equals(1)){
		
	     operS.getOperLoginInfo(operB);
		return ("opLogined");
		}
		if(txtNick.equals(2)){
		  userS.queryUserInfoByAccount(userB);
		  return("usrLogined");
		}
		else{
			return ("../../login");
		}

    }
}

