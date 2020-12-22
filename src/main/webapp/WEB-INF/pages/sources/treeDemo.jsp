<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/plugins/zTreeStyle/zTreeStyle.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/ztree/jquery.ztree.core.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/ztree/jquery.ztree.exedit.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/ztree/jquery.ztree.excheck.js"></script>
</head>
<body>
<script>

    var setting = {
        check:{enable:true},

        async:{
            enable:true,
            url:"${pageContext.request.contextPath}/data.json"
        }
    };
    $(document).ready(function(){


        $.fn.zTree.init($("#treeDemo"),setting);
    });
</script>

<ul id="treeDemo" class="ztree"></ul>
</body>
</html>
