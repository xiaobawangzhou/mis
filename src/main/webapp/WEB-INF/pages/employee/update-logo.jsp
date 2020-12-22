<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html>

<!-- Mirrored from www.gzsxt.cn/theme/hplus/table_basic.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:01 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>绿地中央广场综合物业办公系统 - 基础表格</title>
    <meta name="keywords" content="绿地中央广场综合物业办公系统">
    <meta name="description" content="绿地中央广场综合物业办公系统">

    <link rel="shortcut icon" href="favicon.ico"> 
    	<link href="${pageContext.request.contextPath}/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/customer.css">
	<link href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        
      <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>个人料资<small>>修改头像</small></h5>
                    </div>
                    <div class="ibox-content">
                        <form id="picForm" method="post" action="${pageContext.request.contextPath}/emp/changePicImage" class="form-horizontal">
                    	   <div class="row">
                    	   		<div class="col-sm-4 col-sm-offset-1 logoshow">
                                 <img id="pic" src="${sessionScope.activeUser.pic}"/>
                            </div>
                    	   </div>
                       <div class="row">
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-1">
                                    <input name="myPic" type="file" class="form-control" >
                                </div>
                            </div>
                        </div>
                        <div class="row">
                     		<div class="hr-line-dashed"></div>
                     	</div>
                          
                         <div class="row">
                            <div class="form-group">
                                <div class="col-sm-3 col-sm-offset-3 text-right">
                                   <input type="button" value="更新头像" onclick="changePic();"/>
                                </div>
                                <div class="col-sm-3">
                                	<a href="list-function.html" class="btn btn-white">返回</a>
                                	</div>
                            </div>
                       </div>
                       </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

 
    
    
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${pageContext.request.contextPath}/js/inputfile.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.form.js"></script>

	
   <script>
	    function changePic(){

            $("#picForm").ajaxForm();
            $("#picForm").ajaxSubmit(function(rs){

                if(rs.status=200){
                    $("#pic").attr("src",rs.data.pic);
                    //$("#pic").reload();
                    alert(rs.msg);
                    window.top.location="${pageContext.request.contextPath}/main/index";

                }else{
                    alert(rs.msg);
                }

            });
        }
   </script>
  
</body>


</html>
    