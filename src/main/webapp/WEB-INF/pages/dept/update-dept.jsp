<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>办公系统 - 基础表格</title>
    <meta name="keywords" content="办公系统">
    <meta name="description" content="办公系统">

    <link rel="shortcut icon" href="favicon.ico"> 
    	<link href="${pageContext.request.contextPath}/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/select/bootstrap-select.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        
      <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>员工管理<small>>添加信息</small></h5>
                    </div>
                    <div class="ibox-content">

                       	<div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">部门名称</label>
                                <div class="col-sm-3">
                                    <input type="hidden" name="deptno" value="${deptno}">
                                    <input name="dname" type="text" class="form-control input-sm" id="dname">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">部门位置</label>
                                <div class="col-sm-3">
                                    <input name="local" type="text" class="form-control input-sm" id="local">
                                </div>
                            </div>
                        </div>
                        
                        
                     	<div class="row">
                     		<div class="hr-line-dashed"></div>
                     	</div>
                          
                         <div class="row">
                            <div class="form-group">
                                <div class="col-sm-3 col-sm-offset-3 text-right">
                                    <button id="deptBtn">保存内容</button>
                                </div>
                                <div class="col-sm-3">
                                	<a href="list-dept.jsp" class="btn btn-white"><i class="fa fa-reply"></i> 返回</a>
                                	</div>
                            </div>
                       </div>

                    </div>
                </div>
            </div>
        </div>
    </div>

 
    
    
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/select/bootstrap-select.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
   <script>
	$(document).ready(function() {

	    $.ajax({
            url:"${pageContext.request.contextPath}/dept/getDeptByDeptno",
            type:"post",
            data:{"deptno":${deptno}},
            dataType:"json",
            cache:false,
            success:function(rs){
               $("#dname").val(rs.dname);
               $("#local").val(rs.local);
            }

        });

	    $("#deptBtn").click(function(){
	        $.ajax({
                url:"${pageContext.request.contextPath}/dept/updateDept",
                type:"post",
                data:{"deptno":${deptno},"dname":$("#dname").val(),"local":$("#local").val()},
                dataType:"json",
                cache:false,
                success:function(rs){
                    if(rs.status==200){
                         window.location="${pageContext.request.contextPath}/dept/list-dept";
                    }else{
                        swal(rs.msg);
                    }

                }
            });
        });
		
	});
   </script>

</body>


</html>