<%--
  Created by IntelliJ IDEA.
  User: chenxiaolong
  Date: 2018/7/23
  Time: 下午9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>未授权</title>
</head>
<body>
<div>
    权限不足，具体原因：${ex.message}
    <br>
    <a href="#" onClick="history.back()">返回</a>
</div>
</body>
</html>
