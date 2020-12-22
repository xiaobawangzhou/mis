<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <!DOCTYPE html>
<html>


<!-- Mirrored from www.gzsxt.cn/theme/hplus/table_basic.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:01 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>办公系统 - 基础表格</title>
    <meta name="keywords" content="办公系统">
    <meta name="description" content="办公系统">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${pageContext.request.contextPath}/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/select/bootstrap-select.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/plugins/zTreeStyle/zTreeStyle.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" />

</head>

<body class="gray-bg">
	<div class="wrapper2 wrapper-content2 animated fadeInRight">
	       <div class="row">
	    		<div class="col-sm-5">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>添加角色</h5>
                    </div>
                    <form id="roleForm">
                    <div class="ibox-content">
                            <div class="form-group">
                                <label>角色名称：</label>

                                    <input type="text" name="rolename" id="rolename">

                            </div>
                            <div class="form-group">
                                <label>角色描述：</label>


                                    <input type="text" name="roledis" id="roledis">

                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">角色权限：</label>

                                <div class="col-sm-8">
                                     <ul id="treeDemo" class="ztree"></ul>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否启用：</label>
                                <div class="col-sm-8">
                                    <div class="switch">
			                            <div class="onoffswitch">
			                                 <input type="radio" name="status" value="1" checked>启用
                                             <input type="radio" name="status" value="0">禁用
			                            </div>
			                        </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-8">
                                    <button id="saveBtn">保存</button>
                                    <button><i class="fa fa-undo"></i> 重置</button>
                                </div>
                            </div>

                    </div>
                    </form>
                </div>
            </div>
	    		<div class="col-sm-7">
                  <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>角色列表 <small>点击修改信息将显示在左边表单</small></h5>
                    </div>
                    <div class="ibox-content">                   		

                        <div class="row">
                            <div class="table-responsive">
                            <table id="roleTable" lay-filter="test"></table>
                            <script type="text/html" id="barDemo">
                                <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                            </script>
                        </div>

                        </div>
                    </div>
                </div>
            </div>
            
	    	</div>
	
	</div>       
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
     <script src="${pageContext.request.contextPath}/js/plugins/select/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/ztree/jquery.ztree.core.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/ztree/jquery.ztree.exedit.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/ztree/jquery.ztree.excheck.js"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>

   <script>
       var setting = {
           check: {
               enable: true
           },
           async: {
               enable: true,
               url: "${pageContext.request.contextPath}/resources/getRootSourcesByPid",
               autoParam: ["id", "name"]
           }
       };
       //当页面加载完成后初始化资源树
       $(function(){
           $.fn.zTree.init($("#treeDemo"), setting);
           showRoles();
           //给保存按钮添加点击事件
           $("#saveBtn").click(function(){
               //获取所选中的资源的id
               //获取所选中的菜单树资源
               // 获取我们的树
               var treeObj = $.fn.zTree.getZTreeObj("treeDemo");

               var nodes = treeObj.getCheckedNodes(true);
               if(0==nodes.length){
                   swal("请给当前角色添加资源!");
                  return false;
               }
               // 获取被选中的所有节点id
               var ids="";
               for(var i=0;i<nodes.length;i++){
                   ids=ids+nodes[i].id+",";
               }
               ids=ids.substring(0,ids.length-1);



               //发送请求保存角色及角色资源
               $.ajax({
                   url:"${pageContext.request.contextPath}/role/saveRole",
                   type:"post",
                   data:{"rolename":$("#rolename").val(),"roledis":$("#roledis").val(),"status": $("input[name=status]:checked").val(),"ids":ids},
                   dataType:"json",
                   cache:false,
                   success:function(rs){
                       if(rs.status==200){
                           alert(rs.msg);
                           $("#roleForm")[0].reset();
                       }else if(rs.status==400){
                           swal(rs.msg);
                       }
                   }
               });

           });
       });

       //显示列表
       function showRoles(){

           layui.use('table',function(){
               var table = layui.table;
               table.render({
                   elem:"#roleTable",
                   height:400,
                   url:"${pageContext.request.contextPath}/role/getAllRoles",
                   toolbar: 'default', //开启工具栏，此处显示默认图标
                   page:true,
                   limits:[2,5,10,15,20],
                   cols:[[
                       {checkbox:true,fixed:'left'},
                       {field:'roleid',title:'编号',width:80,fixed:'left'},
                       {field:'rolename',title:'角色名称',width:150,fixed:'left'},
                       {field:'roledis',title:'角色描述',width:200},
                       {field:'status',title:'状态',width:80,templet:function(value){
                           if(value.status==1){
                               return "启用";
                           }else if(value.status==0){
                               return "禁用";
                           }
                           }},
                       {title:'操作',toolbar:'#barDemo',width:150,align:'center'}
                   ]]

               });

               //监听行工具事件
               table.on('tool(test)', function(obj){
                   //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                   var data = obj.data //获得当前行数据
                       ,layEvent = obj.event; //获得 lay-event 对应的值
                   if(layEvent === 'del'){
                       layer.confirm('真的删除行么', function(index){


                           //向服务端发送删除指令
                           $.ajax({
                               url:"${pageContext.request.contextPath}/role/deleteRoleById",
                               type:"post",
                               data:{"roleid":data.roleid},
                               dataType:"json",
                               cache:false,
                               success:function(rs){

                                   if(rs.status==200){
                                       obj.del(); //删除对应行（tr）的DOM结构
                                       layer.close(index);
                                       layer.msg(rs.msg);
                                   }else if(rs.status==400){
                                       layer.msg(rs.msg);
                                   }

                               }
                           });
                       });
                   } else if(layEvent === 'edit'){
                       window.location="${pageContext.request.contextPath}/role/update-role?roleid="+data.roleid;
                   }
               });
           });

       }

    </script>
    
</body>


</html>
    
