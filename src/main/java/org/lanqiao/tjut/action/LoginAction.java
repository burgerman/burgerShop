package org.lanqiao.tjut.action;
import javax.annotation.Resource;  

import javax.servlet.http.HttpServletRequest;

import org.lanqiao.tjut.bean.TBAdminsBean;
import org.lanqiao.tjut.bean.TBOperatorBean;
import org.lanqiao.tjut.service.AdminLoginService;
import org.lanqiao.tjut.service.OpLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;





@Controller
public class LoginAction{

	
	
	
	
	@Resource
	private OpLoginService operS;	
	@Resource
	private AdminLoginService adminS;
		
	@RequestMapping("/LoginAction")
	public String doLogin(HttpServletRequest request,String txtNick, TBAdminsBean adminB, TBOperatorBean operB) {
		// ����service����й���Ա��¼ҵ���߼�����
		// ��ȡ��¼�û���Ϣ
		
		
		if(txtNick.equals(0)){
		// ��ת����¼�ɹ���ҳ��,ҳ����ת֮��Ĵ��벻���ٱ�ִ��
		// �ж��Ƿ��¼�ɹ�	
	     adminS.getAdminLoginInfo(adminB);
			
	   
		return ("adminLogined");
		} 
		else if(txtNick.equals(1)){
		
	     operS.getOperLoginInfo(operB);
		return ("opLogined");
		}
		else{
			return ("../../login");
		}

    }
}

