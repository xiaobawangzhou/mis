<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html>


<!-- Mirrored from www.gzsxt.cn/theme/hplus/table_basic.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:01 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>绿地中央广场综合物业办公系统 - 基础表格</title>
    <meta name="keywords" content="综合办公系统">
    <meta name="description" content="综合办公系统">

    <link rel="shortcut icon" href="favicon.ico"> 
    	<link href="${pageContext.request.contextPath}/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    	<link href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/select/bootstrap-select.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" />

</head>

<body class="gray-bg">
	<div class="wrapper2 wrapper-content2 animated fadeInRight">
    <div class="ibox float-e-margins">
                   <div class="ibox-content">
                        <div class="row">
                        		<div class="col-sm-3 col-sm-offset-2 text-right">
                        			<h3><small>搜索条件:</small></h3>
                        		</div>
                            <div class="col-sm-2">
                                <select class="selectpicker form-control">
                                    <option value="0">选择类型</option>
                                    <option value="1">项目名称</option>
                                    <option value="2">项目经理</option>
                                </select>
                            </div>
                           
                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input type="text" placeholder="请输入关键词" class="input-sm form-control">
                                    <span class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-primary"><i class="fa fa-search"></i>搜索</button>
                                    </span>
                                </div>                                
                            </div>
                            <div class="col-sm-2 text-right">

                            </div>
                        </div>

                        <div class="table-responsive">
                            <!--
                            <table class="table table-striped list-table">
                                <thead>
                                    <tr>
                                        <th>选择</th>
                                        <th>序号</th>
                                        <th>姓名</th>
                                        <th>部门</th>
                                        <th>性别</th>
                                        <th>联系电话</th>
                                        <th>入职时间</th>


                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><input  type="checkbox" checked=""></td>
										<td>1</td>
										<td>李晓明</td>
										<td>开发部</td>
										<td>男</td>
										<td>24</td>
										<td>13288888888</td>
                                    </tr>
                                    
                                </tbody>
                            </table>
                            -->
                            <table id="employeeTable" lay-filter="test"></table>
                            <script type="text/html" id="toolbarDemo">
                                <div class="layui-btn-container">
                                    <button class="layui-btn layui-btn-sm" lay-event="addEmployee">添加</button>
                                    <button class="layui-btn layui-btn-sm" lay-event="editEmployee">修改</button>
                                    <button class="layui-btn layui-btn-sm" lay-event="deleteEmployee">删除</button>
                                </div>
                            </script>
                        </div>
                       

						
                    </div>
                </div>
     </div>       
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
     <script src="${pageContext.request.contextPath}/js/plugins/select/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
   <script>
	$(document).ready(function() {
        showEmployees();
	});

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
	function showEmployees(){
        layui.use('table',function(){
            var table = layui.table;
            table.render({
                elem:"#employeeTable",
                height:500,
                url:"${pageContext.request.contextPath}/emp/getEmployees",
                toolbar: '#toolbarDemo', //开启工具栏，此处显示默认图标
                page:true,
                limits:[2,5,10,15,20],
                cols:[[
                    {checkbox:true,fixed:'left'},
                    {field:'eid',title:'编号',width:80,fixed:'left'},
                    {field:'ename',title:'姓名',width:100,fixed:'left'},
                    {field:'esex',title:'性别',width:100,},
                    {field:'eage',title:'年龄',width:100},
                    {field:'telephone',title:'电话',width:200},
                    {field:'hiredate',title:'入职日期',width:150,
                        templet:function(value){

                            return get_date(value.hiredate);
                        }},
                    {field:'pnum',title:'身份证号',width:200},
                    {field:'dname',title:'部门',width:100}
                ]]
            });
            //头工具栏事件
            table.on('toolbar(test)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                switch(obj.event){
                    case 'addEmployee':
                        window.location="${pageContext.request.contextPath}/emp/save-employee";
                        break;
                    case 'editEmployee':
                        var data = checkStatus.data;
                        if(data.length!=1){
                            swal("只能选择一条记录进行修改");
                            return false;
                        }
                        window.location="${pageContext.request.contextPath}/emp/update-employee?eid="+data[0].eid;
                        break;
                    case 'deleteEmployee':
                        var data = checkStatus.data;
                        if(data.length==0){
                            swal("至少选择一条记录进行删除");
                            return false;
                        }
                        var eids=new Array();
                        for(var i=0;i<data.length;i++){
                            eids.push(data[i].eid);
                        }
                        $.ajax({
                            url:"${pageContext.request.contextPath}/emp/deleteEmployee",
                            type:"post",
                            data:{"eids":eids},
                            dataType:"json",
                            cache:false,
                            success:function(rs){

                                if(rs.status==200){
                                    showEmployees();
                                }else{
                                    swal(rs.msg);
                                }
                            }
                        });
                        break;
                };
            });
        });

    }
    </script>
    
</body>


</html>
    