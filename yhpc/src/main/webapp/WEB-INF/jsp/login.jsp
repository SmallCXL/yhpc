<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="js/bootstrap.js"></script>
<script>
    $(function () {
        var _phoneNumber = $('#phoneNumber');
        var _password = $('#password');
        var _form = $('#login-form');
        var _faultMessage = $('#faultMessage');
        var phone_reg = /^[1][3456789][0-9]{9}$/;
        var password_reg = /^\w{6,20}$/;

        function validateForm() {
            if (_phoneNumber.val()==="" || _password.val()==="") {
                _faultMessage.html("请输入完整的登陆信息");
                return false;
            }
            if (!phone_reg.test(_phoneNumber.val())) {
                _faultMessage.html("手机号码格式有误，请检查");
                return false;
            }
            if(!password_reg.test(_password.val())) {
                _faultMessage.html("密码格式有误，请检查");
                return false;
            }
            _faultMessage.html("");
            return true;
        }

        _form.submit(function () {
            return validateForm();
        })

    })
</script>
<%--
  Created by IntelliJ IDEA.
  User: chenxiaolong
  Date: 2018/9/22
  Time: 上午10:07
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <!-- Bootstrap -->
    <link href="css/bs/bootstrap.css" rel="stylesheet">
    <link href="css/custom-style.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <!--<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>-->
    <!--<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>-->
    <!--[endif]-->
    <title>阳核拼车 - 登陆</title>
</head>
<body>
<nav class="navbar navbar-fixed-top navbar-black" id="header">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#nav-bar" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand"><span class="glyphicon glyphicon-send" aria-hidden="true"></span> YHPC</a>
        </div>
        <div class="collapse navbar-collapse" id="nav-bar">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="./home" class="navbar-link"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> 首页</a></li>
                <shiro:hasAnyRoles name="user,admin">
                    <li><a href="./userinfo" class="navbar-link"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 个人中心</a></li>
                    <li><a href="./logout" class="navbar-link" id="logout_link"><span class="glyphicon glyphicon-off" aria-hidden="true"></span> 注销</a></li>
                </shiro:hasAnyRoles>
                <shiro:guest>
                    <li><a href="./login" class="navbar-link"><span class="glyphicon glyphicon-flag" aria-hidden="true"></span> 登陆</a></li>
                    <li><a href="./register" class="navbar-link"><span class="glyphicon glyphicon-registration-mark" aria-hidden="true"></span> 注册</a></li>
                </shiro:guest>
            </ul>
        </div>
    </div>
</nav>
    <div class="container">
        <div class="jumbotron text-center">
            <h1>阳核拼车</h1>
            <p>登录</p>
        </div>
        <div class="row">
            <div class="col-xs-10 col-xs-offset-1 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 table-form">
                <form action="./login" method="post" id="login-form">
                    <div class="form-group">
                        <label for="phoneNumber">手机号码</label>
                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="PhoneNumber" value="${try_number}">
                    </div>
                    <div class="form-group">
                        <label for="password">密码</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                    </div>
                    <div class="col-xs-12">
                        <button type="submit" class="btn btn-block-dark">登陆</button>
                    </div>
                    <div class="col-xs-12 tip-link">
                        <div class="row">
                            <div class="col-xs-6">
                                <a class="orange-text" href="./forgetPassword">忘记密码</a>
                            </div>
                            <div class="col-xs-6">
                                <a class="orange-text" href="./register">我要注册</a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-xs-12" >
                <h5 id="faultMessage" class="fault-message">${login_error_message}</h5>
            </div>
        </div>
    </div>

</body>
</html>
