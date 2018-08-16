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
		// ����service����й���Ա��¼ҵ���߼�����
		// ��ȡ��¼�û���Ϣ
		
		
		if(txtNick.equals(0)){
		// ��ת����¼�ɹ���ҳ��,ҳ����ת֮��Ĵ��벻���ٱ�ִ��
		// �ж��Ƿ��¼�ɹ�	
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

