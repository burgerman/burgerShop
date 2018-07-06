<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>主页面前的跳转页面</title>
</head>
<body>
	<%
		String path = request.getContextPath();
		//获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量 
		// 例如： http://localhost:8090/webservlet0721/
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
		+ request.getServerPort()
				+ path + "/";
		//将 "项目路径basePath" 放入pageContext中，待以后用EL表达式读出。 
		session.setAttribute("basePath", basePath);
		// 跳转到登录页面
		//response.sendRedirect("login.jsp");
		request.getRequestDispatcher("login.jsp").forward(request,response);
	%>
</body>
</html>