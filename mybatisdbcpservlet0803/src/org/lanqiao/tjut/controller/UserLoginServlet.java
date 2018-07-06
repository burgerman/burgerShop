package org.lanqiao.tjut.controller;

import java.util.List;    
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.tjut.bean.TBAdminsBean;

import org.lanqiao.tjut.model.ModelFactory;
import org.lanqiao.tjut.myUtils.MyUtils;

import com.sun.org.apache.xpath.internal.operations.Equals;


@WebServlet("/UserLoginServlet")


public class UserLoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1l;
	
	/*
	 * 初始化方法
	 */
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		//进行客户端请求处理
		System.out.println("这是采用web.xml配置的servlet对客户端request请求处理");
		//使用request对象的getParameter方法可获取客户端使用http的request请求提交给服务器的form表单的数据内容
		//必须使用控件的name属性获取
		//获取姓名
	
	    request.setCharacterEncoding("UTF-8");
		
		String strName = request.getParameter("txtUserName");
		
		//获取密码
		String strPsw = request.getParameter("txtUserPsw");
		
		System.out.println("获取的客户端发送的用户名：" +strName);
		System.out.println("获取的客户端发送的密码:" +strPsw);
			
		String method = request.getParameter("txtNick");
		//使用实体类进行传参
		if(method.equals("老师")){
		TBAdminsBean adminB = MyUtils.getNewInstance(TBAdminsBean.class);
		
		//将前台页面传入值，赋给实体变量
		adminB.setAdmins_name(strName);
		adminB.setAdmins_psw(strPsw);
      		
        //调用model层进行登录验证的业务逻辑处理
		//获取登录用户信息
		
		List<TBAdminsBean> lstAdmins = ModelFactory.getUserLoginModelInstance().getUserLoginInfo(adminB);
		
		//跳转到登录成功的页面，页面跳转后的代码不会再被执行
		//判定是否登录成功
		
		if(lstAdmins != null && lstAdmins.size()>0){
			
			//登录成功
			//将用户登录成功信息缓存到session对象中
			request.getSession().setAttribute("loginedAdminB", lstAdmins.get(0));
			//使用转发进行跳转
			
			request.getRequestDispatcher("WEB-INF/logined/logined.jsp").forward(request, response);
		    //使用重定向进行跳转
			//response.sendRedirect("WEB-INF/logined/logined.jsp");
			
		
		
		}
		
			
			
			
		
		}else{
			
		
		//登录失败
		response.sendRedirect("login.jsp");
	}
		}
    
	
     
}
