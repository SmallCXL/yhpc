<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
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