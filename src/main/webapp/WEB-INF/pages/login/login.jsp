<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zxx">

<head>
	<title>Home</title>
	<!-- Meta tag Keywords -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8" />
	<meta name="keywords" content=""
	/>
	<script>
		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
	</script>
	<!-- Meta tag Keywords -->
	<!-- css files -->
	
	<link href="${pageContext.request.contextPath}/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginstyle.css" type="text/css" media="all" />
	<link href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
</head>

<body>
	<!-- bg effect -->
	<div id="bg">
		<canvas></canvas>
		<canvas></canvas>
		<canvas></canvas>
	</div>
	
	<!-- content -->
	<div class="sub-main-w3">
		<form>
			<h2>欢迎登录项目管理系统
				<i class="fa fa-level-down"></i>
			</h2>
			 <div class="row">
                <div class="form-group">
                    <label class="col-sm-4 control-label text-right">用户名<i class="fa fa-user"></i></label>
                    <div class="col-sm-6">
                        <input name="username" id="username" type="text" class="form-control input-sm">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label class="col-sm-4 control-label text-right">密码<i class="fa fa-unlock-alt"></i></label>
                    <div class="col-sm-6">
                        <input name="password" id="password" type="password" class="form-control input-sm">
                    </div>
                </div>
            </div>
		
			 <input type="button" value="登陆" onclick="login();"/>
			
		</form>
	</div>

	<div class="footer">
		<p>Copyright &copy; 2020.项目理管系统 All rights reserved.</p>
	</div>


	
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/canva_moving_effect.js"></script>
   <script>
       function login(){
           $.ajax({
               url:"${pageContext.request.contextPath}/checkLogin",
               type:"post",
               data:{"username":$("#username").val(),"password":$("#password").val()},
               dataType:"json",
               cache:false,
               success:function(rs){
                    if(rs.status==200){
                        window.location="${pageContext.request.contextPath}/main/index";
                    }else {
                       alert(rs.msg);
                    }
               }
           });
       }
   </script>
</body>

</html>