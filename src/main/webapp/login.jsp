<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>bootstrap form</title>
		<!-- Bootstrap core CSS -->
		<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
		<!-- Optional Bootstrap Theme -->
		<link href="data:text/css;charset=utf-8," data-href="bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet" id="bs-theme-stylesheet">

		<!-- Bootstrap core JavaScript
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="jquery-1.12.4/jquery-1.12.4.min.js"></script>
		<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    
    
    <TITLE> New Document </TITLE>
  <META NAME="Generator" CONTENT="EditPlus">
  <META NAME="Author" CONTENT="">
  <META NAME="Keywords" CONTENT="">
  <META NAME="Description" CONTENT="">
  <script>
        function _change(obj){
            if(obj.value == 'other')
                document.getElementById("othertxt").disabled=false;
            else 
                document.getElementById("othertxt").disabled=true;
            if(obj.value == 'b')
                document.getElementById("ms").disabled=false;
            else
                document.getElementById("ms").disabled=true;
        }
  </script>
    	
	
	
	</head>

	<body>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-3">
						</div>
						<div class="col-md-6">
							<form class="form-horizontal" action="LoginAction" method="post">
								<div class="form-group">									
								</div>
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
									<div class="col-sm-4">
										<input type="text" id="txtUserName" name="txtUserName" class="form-control" placeholder="请输入用户名">
									</div>

									<label for="inputEmail3" class="col-sm-2 control-label">密码</label>
									<div class="col-sm-4">
										<input type="password" id="txtUserPsw" name="txtUserPsw" class="form-control" placeholder="请输入密码">
									</div>
								</div>	
								
								
								
								   <span style="white-space:pre">  </span>  
                                   <img onclick="$(this).attr('src', 'code.do?ran=' + Math.random())" src="code.do"  title="点击刷新验证码"  class="vcodeImg">  
                                   <input  id="vcode" name="vcode"> 
								
							 
							<input type="radio" name="txtNick" id="txtNick1" onclick="sbm2;" value="0"/> 管理员
                           <input type="radio" name="txtNick" id="txtNick2" onclick="sbm1;" value="1"/> 操作员
							
								
									
								
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-default">登录</button>
									</div>
								</div>。                                   
							</form>
						</div>
						<div class="col-md-3">
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
		
		
    <!-- <input type="radio" name="ra" id="ra" onclick="_change(this);" value="other"/> 其他 <input type="text" name="othertxt" id="othertxt" disabled/> -->
    
		
	</body>
</html>