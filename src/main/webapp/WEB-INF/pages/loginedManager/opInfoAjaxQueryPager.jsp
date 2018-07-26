<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>bootstrap form</title>

<!-- Bootstrap core CSS -->

<link href="${basePath}bootstrap-3.3.7-dist/css/bootstrap.min.css"
   rel="stylesheet">

<!-- Optional Bootstrap Theme -->   
<link href="data:text/css;charset=utf-8,"
      data-href="${basePath}bootstrap-3.3.7-dist/css/bootstrap-theme.min.css"
      rel="stylesheet" id="bs-theme-stylesheet" >
<!-- Bootstrap core JavaScript -->
     <!-- Placed at the end of the document so the pages load faster -->
<script src = "${basePath}jquery-1.12.4/jquery-1.12.4.min.js"></script>
<script src = "${basePath}bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>     

<!-- 分页插件的引用 -->
<link
    href="${basePath}pagination-2.0.7/css/pagination.css" rel="stylesheet" />
<script
    src="${basePath}pagination-2.0.7/js/pagination.min.js"></script>


<!-- 加载bootstrap模态框的js和css  -->
<link href="${basePath}bootstrap-3.3.7-dist/css/bootstrapEx.css" rel="stylesheet" />
<script
   src="${basePath}bootstrap-3.3.7-dist/js/bootstrapEx.js"></script>



<!-- 加载的jquery脚本 -->

<script type="text/javascript">

       //页面加载完成时即执行查询操作
       $(function(){
    	   doAjaxQuery();
       });
   //dom加载完成时就执行该方法
 


   //--------------bootstrap模态框初始化----------------
   //dom初始化完时，加载模态框
   function showModel(iframeUrl){
	   
	   //创建一个模态框
	   var ModelA = InitModelA(iframeUrl);
	   //显示模态框
	   ModelA.show();
	   //model关闭完成时的事件处理(只需定义该函数就会调用)
	   modelA_Hiden = function(){
		   
		   //console.info("here.....:");
		   //调用ajax进行数据修改后的更新
		   doAjaxQuery();
	   }
   }
   
     //模态框初始化
     function InitModelA(iframeUrl){
    	 //模态框加载父标签及模态框生成的div
    	 $(document.body).append('<div id="ModelA"></div>');
    	 //创建一个模态框对象
    	 ModelA = new Model();
    	 //模态框标题设置为空
    	 ModelA.title ="";
    	 //模态框将在的位置
    	 ModelA.renderto = "#modelA";
    	 
    	 //是否绘制完成后显示模态框
    	 ModelA.InitShow = true;
    	 //模态框的按钮数组
    	 ModelA.btns = [{
    		 //关闭按钮
    		 id : "closebtn",
    		 text : "修改"
    		 
    	 }];
    	 //创建tab对象
    	 tab1 = new Tab();
    	 ModelA.onfirstInited = function(thismodel){
    		 tab1.tabs = [{
    			
    			 //加载的tab控件的id
    			 id : "tabs1",
    			 title : "用户信息修改",
    			 active : true,
    			 //tab标签中是否加载iframe框架
    			 isiframe : true,
    			 url : iframeUrl
    			 
    		 }];
    		 
    		 //tab加载位置
    		 tab1.renderto = thismodel.body;
    		 //初始化
    		 tab1.Init();
    		 //给id为bttn1的按钮添加一个click事件
    		 $("#closebtn").click(function(){
    			 
    			 //调用iframe加载的页面中的form表单的submit提交事件
    			 
    			 /*
    			  * 使用jquery选择器选择 模态框中的id为tabs1加载的iframe对象
    			  *　然后再从iframe对象的组成的元素中使用find方法找到id为　fronMod的from表单对象
    			  *　调用找到from表单对象的submit事件，发送request请求到服务器(修改操作)
    			  *　等价于点击了保存按钮
    			 
    			  */
    			 $('#tabs1 > iframe').contents().find("#formMod").submit();
    		 })
    	 }
    	 //返回模态框对象
    	 return ModelA;
     }

     function  doAjaxQuery(){
    	 $.ajax({
    		 type : "POST", //请求的类型get/post
    	     url: "${basePath}OpInfoQueryPagerServlet",//服务器url
    	     dataType : "json",//服务器返回的数据类型
    	     data :$("#myForm").serialize(),
    	     success : function(data){  //成功之后的回调函数
    	       //调用分页插件的分页行为方法
    	       console.info("data:");
    	      console.info(data);
    	      //当查询数据不为空的时候进行分页处理
    	      if (data != null && data.length > 0){
    	    	  //分页处理
    	    	  doPaging(data);
    	      } else{
    	    	  //查询结果集为空时，情况显示的table内容
    	    	  $('#tbShow').html("");
    	    	  
    	      }
    	      } 
    	      
    	     });
    	   }
     
     
   
     
     
     //调用分页控件
     function doPaging(pageDatas){
    	 
    	 //调用分页插件的分页处理方法
    	 $("#pager").pagination({
    		
    		 dataSource : pageDatas, //分页数据源
    		 pageSize : 3,  //每页有几条记录
    		 showGoInput : true, //是否显示输入页面处理
    		 showGoButton : true,
    		 callback : function(data, pagination){ //点击分页插件页数时的回调方法
    			 //调用分页数据的解析处理方法
    			 console.info("2");
    			 parseJsonData(data);
    		 }
    		 
    	 })
     }
     
     
     
    //json解析处理函数
    
    function parseJsonData(data){
    	//解析的结果串
    	var strResult= "";
    	//使用jquery解析json对象，因为json是一个list数据结构，是一个数组结构，所有使用each函数进行解析
    	//如果json返回的是一个对象，则可以先将该对象转换为数组结构再遍历，转换方式:"["+data+"]"
    	
    	$.each(data,function(index,eachVal) { 
    		
    		//每个eachVal就是list中的一个bean对象
    		
    		strResult += "<tr>";
    		
    		strResult += "<th scope='row'>";
    		strResult += index+1;
    		strResult += "</th>";
    		
    		strResult += "<td>";
    		strResult += eachVal.opr_account;
    		strResult += "</th>";
    		
    		strResult += "<td>";
    		strResult += eachVal.opr_psw;
    		strResult += "</th>";
    	
    		
    	
    		
    		
    	
    	
    	  

		//将解析的结果内容，添加到显示的控件中
		$("#tbShow").html(strResult);

	}
</script>  

</head>
<body>

   <div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<form class="form-horizontal" id="myForm">
							
							<div class="form-group"></div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">帐号</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="oprName"
										name="stuName" placeholder="请输入操作员帐号">
								</div>
								</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="oprPsw"
										name="stuTel" placeholder="请输入密码">
								</div>

								
							</div>
							<div class="form-group">
							<div class="col-sm-offset-4 col-sm-4">
							    <button type= "button" class="btn btn-default" onclick = "doAjaxQuery()" >查询</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="reset" class="btn btn-default">重置</button>
							</div>
						</div>
						</form>
					</div>
					<div class="col-md-2"></div>
				</div>
			</div>
		</div>
	</div>
    <div class="bs-example" data-example-id = "hoverable-table">
       <table class="table table-hover">
         <thead>
            <tr>
            
               <th>序号</th>
               <th>帐号</th>
               <th>密码</th>
               <th>修改</th>
               <th>删除</th>
              </tr>
           </thead>   
               <tbody id ="tbShow">
               
               </tbody>
         
         </table>
         <div id="pager">
         
         <div class="m-pagination"></div>
         </div>
         
       </div>
    
</body>
</html>