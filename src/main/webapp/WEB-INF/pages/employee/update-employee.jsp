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
                        <h5>员工管理<small>>修改信息</small></h5>
                    </div>
                    <div class="ibox-content">
                        <form id="employeeForm" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/emp/updateEmployee" enctype="multipart/form-data">
                       	<div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-3">
                                    <input type="hidden" name="eid" value="${eid}">
                                    <input name="ename" id="ename" type="text" class="form-control input-sm">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">用户名</label>
                                <div class="col-sm-3">
                                    <input name="username" id="username" type="text" class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">密码</label>
                                <div class="col-sm-3">
                                    <input name="password" id="password" type="text" class="form-control input-sm">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">性别</label>
                                <div class="col-sm-3">
                                      <input type="radio" name="esex" id="opt1" checked value="男">男
                                    <input type="radio" name="esex" id="opt2" value="女">女
                                </div>                     
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">身份证号码</label>
                                <div class="col-sm-3">
                                    <input name="pnum" id="pnum" type="text" class="form-control input-sm">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">联系电话</label>
                                <div class="col-sm-3">
                                    <input name="telephone" id="telephone" type="text" class="form-control input-sm">
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                              <label class="col-sm-2 control-label">部门</label>
                                <div class="col-sm-3">
                                    <select name="dfk" id="dfk">
                                         <option value="0">请选择部门</option>
									</select>
                                </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-offset-1 control-label">入职时间</label>
                                <div class="col-sm-3">
                                    <!--时间控件的id都不能改-->
                                    <input name="hiredate"  id="start" class="laydate-icon form-control layer-date">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                              <div class="form-group">
                                <label class="col-sm-2 control-label">年龄</label>
                                <div class="col-sm-3">
                                    <input name="eage" id="eage" type="text" class="form-control input-sm">
                                </div>
                                <label class="col-sm-2 col-sm-offset-1 control-label">角色</label>
                                <div class="col-sm-3">
                                    <select name="roleid" id="roleid">
										<option value="0">请选择角色</option>

									</select>
                                </div>                     
                            </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">上传头像:</label>
                                <div class="col-sm-9">
                                    <input type="file" name="picImage">
                                </div>

                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-9">
                                    <textarea name="remark" id="remark" class="form-control"></textarea>
                                </div>
                                
                            </div>
                        </div>
                        
                     	<div class="row">
                     		<div class="hr-line-dashed"></div>
                     	</div>
                          
                         <div class="row">
                            <div class="form-group">
                                <div class="col-sm-3 col-sm-offset-3 text-right">
                                    <input type="button" value="提交" onclick="saveEmployee();">
                                </div>
                                <div class="col-sm-3">
                                    <input type="button" value="返回">
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
    <script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/select/bootstrap-select.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/layer/laydate/laydate.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
   <script>
       function get_date(source){
           var date=new Date(source);
           var year=date.getFullYear();
           var month=formatDate(date.getMonth()+1);
           var day=formatDate(date.getDate());
           return year+"-"+month+"-"+day;
       }

       function formatDate(s){
           if(s<10){
               return "0"+s;
           }
           return s;
       }
	$(document).ready(function() {
		// 设置按钮的样式
		$('.selectpicker').selectpicker('setStyle', 'btn-white');
		//初始化日期控件
		laydate({elem: "#start"});

		$.ajax({
            url:"${pageContext.request.contextPath}/emp/getEmployeeByEid",
            type:"post",
            data:{"eid":${eid}},
            dataType:"json",
            cache:false,
            success:function(rs){
                $("#ename").val(rs.ename);
                $("#username").val(rs.username);
                $("#pnum").val(rs.pnum);
                $("#telephone").val(rs.telephone);
                $("#start").val(get_date(rs.hiredate));
                $("#eage").val(rs.eage);
                $("#remark").val(rs.remark);
                if(rs.esex=='男'){
                    $("#opt1").attr("checked",true);
                }else{
                    $("#opt2").attr("checked",true);
                }
                //获取所有部门
                $.ajax({
                    url:"${pageContext.request.contextPath}/dept/getAllDepts",
                    type:"post",
                    dataType:"json",
                    cache:false,
                    success:function(rs1){
                        $.each(rs1,function(index,element){
                            var option="";
                            if(element.deptno==rs.dfk){
                                option="<option selected value='"+element.deptno+"'>"+element.dname+"</option>";
                            }else{
                                option="<option  value='"+element.deptno+"'>"+element.dname+"</option>";
                            }
                            $("#dfk").append(option);
                        })
                    }
                });
                //获取所有角色
                $.ajax({
                    url:"${pageContext.request.contextPath}/role/getCurrentAllRoles",
                    type:"post",
                    dataType:"json",
                    cache:false,
                    success:function(rs2){
                        $.each(rs2,function(index,element){
                            var option="";
                            if(rs.roleid==element.roleid){
                                option="<option selected value='"+element.roleid+"'>"+element.rolename+"</option>";
                            }else{
                                option="<option  value='"+element.roleid+"'>"+element.rolename+"</option>";
                            }
                            $("#roleid").append(option);
                        })
                    }
                });
            }
        });


	});

	function saveEmployee(){
	    $("#employeeForm").ajaxForm();
        $("#employeeForm").ajaxSubmit(function(rs){

            if(rs.status==200){
                 swal(rs.msg);
                 window.location="${pageContext.request.contextPath}/emp/list-employee";
            }else {
                swal(rs.msg);
            }
        });
    }
   </script>
</body>


</html>
    