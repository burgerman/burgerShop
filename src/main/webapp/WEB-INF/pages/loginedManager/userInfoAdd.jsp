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
							action="${basePath}UserInfoAddServlet" method="post">
							<div class="form-group"></div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">姓名</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="userName"
										name="user_name" placeholder="请输入学生姓名">
								</div>

								<label for="inputEmail3" class="col-sm-2 control-label">性别</label>
								<div class="col-sm-4">
									<label class="radio-inline"> <input type="radio"
										id="usersex2" name="user_sex" value="女"> 女
									</label> <label class="radio-inline"> <input type="radio"
										id="usersex2" name="user_sex" value="男"> 男
									</label>
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">出生日期</label>
								<div class="col-sm-4">
									<!-- <input type="text" id="stuBirthday" name="stuBirthday"
										placeholder="请选择出生日期" data-date-format="yyyy-mm-dd hh:ii:ss"> -->
									<input type="date" class="form-control" id="userBirthday"
										name="user_birthday" placeholder="请选择出生日期">
								</div>

								<label for="inputPassword3" class="col-sm-2 control-label">地址</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="userAddress"
										name="user_address" placeholder="请输入地址">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">身份证</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="userId"
										name="user_id" placeholder="请输入ID">
								</div>

								<label for="inputPassword3" class="col-sm-2 control-label">单位</label>
								<div class="col-sm-4">
									<select class="form-control" id="userCompany"
										name="user_company">
										<option></option>
										<option value="1">国企</option>
										<option value="2">外企</option>
										<option value="3">私企</option>
										<option value="4">其它</option>
									</select>
								</div>
							</div>
							<label for="inputEmail3" class="col-sm-2 control-label">账户名</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="userAccount"
										name="user_account" placeholder="请输入用户账户名">
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
	</div>

</body>

</html>