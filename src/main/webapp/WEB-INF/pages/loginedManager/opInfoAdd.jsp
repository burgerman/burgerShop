<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>bootstrap form</title>
<!-- Bootstrap core CSS -->
<link href="${basePath}bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Optional Bootstrap Theme -->
<link href="data:text/css;charset=utf-8,"
	data-href="${basePath}bootstrap-3.3.7-dist/css/bootstrap-theme.min.css"
	rel="stylesheet" id="bs-theme-stylesheet">

<!-- Bootstrap core JavaScript
		<!-- Placed at the end of the document so the pages load faster -->
<script src="${basePath}jquery-1.12.4/jquery-1.12.4.min.js"></script>
<script src="${basePath}bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
	
</script>
</head>

<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<form class="form-horizontal"
							action="${basePath}OpInfoAddServlet" method="post">
							<div class="form-group"></div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="stuName"
										name="opr_account" placeholder="请输入操作员用户名">
								</div>

								
								</div>
							</div>
							
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="oprPsw"
										name="opr_psw" placeholder="请输入密码">
								</div>

								
							</div>
							
							<div class="form-group">
								<div class="col-sm-offset-4 col-sm-4">
									<button type="submit" class="btn btn-default">保存</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="reset" class="btn btn-default">重置</button>
								</div>
							</div>
						</form>
					</div>
					<div class="col-md-3"></div>
				</div>
			</div>
		</div>
	

</body>

</html>