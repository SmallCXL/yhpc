<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="js/bootstrap.js"></script>
<script src="js/bootoast.js" charset="UTF-8"></script>

<script>
    $(function () {
        var _phoneNumber = $('#phoneNumber');
        var _validateCode = $('#validateCode');
        var _faultMessage = $('#faultMessage');
        var phone_reg = /^[1][3456789][0-9]{9}$/;
        var code_reg = /^[0-9]{6}$/;
        var _form = $('#login-form');
        var _getCode = $('#getCode');

        function validateForm() {
            if (_phoneNumber.val()==="" || _validateCode.val()==="") {
                _faultMessage.html("请输入完整的验证信息");
                return false;
            }
            if (!phone_reg.test(_phoneNumber.val())) {
                _faultMessage.html("手机号码格式有误，请检查");
                return false;
            }
            if(!code_reg.test(_validateCode.val())) {
                _faultMessage.html("验证码格式有误，请检查");
                return false;
            }
            _faultMessage.html("");
            return true;
        }
        //重新发送验证码的倒计时
        function startClock() {
            _getCode.attr("disabled",true);
            var i = 0;
            var countDown = setInterval(function () {
                i++;
                _getCode.html((120-i) + "秒后获取");
                if (i===120) {
                    _getCode.removeAttr("disabled");
                    _getCode.html("获取验证码");
                    clearInterval(countDown);
                }
            },1000);
        }
        //toast提示
        function toast(message, type) {
            bootoast({
                message: message,
                type: type,
                position:'right-bottom',
                timeout:3
            });
        }
        
        //获取验证码
        _getCode.click(function () {
            if (!phone_reg.test(_phoneNumber.val())){
                _faultMessage.html("手机号码格式有误，请检查");
                return false;
            }
            $.ajax({
                url:'./getCode',
                type:"GET",
                dataType:"json",
                data:{
                    "phoneNumber":_phoneNumber.val(),
                    "type":"forget-password"
                },
                success:function(responseData) {
                    if(responseData.code === 200){
                        startClock();
                        toast("已发送，请查看手机","success");
                    }else {
                        toast(responseData.message,"danger")
                    }
                },
                error:function (message) {
                    toast("请求超时，请重新登录", "danger");
                    console.log(message);
                }
            });
            return false;
        });

        _form.submit(function () {
            return validateForm();
        })

    })
</script>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <!-- Bootstrap -->
    <link href="css/bs/bootstrap.css" rel="stylesheet">
    <link href="css/bootoast.css" rel="stylesheet">
    <link href="css/custom-style.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <!--<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>-->
    <!--<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>-->
    <!--[endif]-->
    <title>阳核拼车 - 忘记密码</title>
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
        <p>忘记密码</p>
    </div>
    <div class="row">
        <div class="col-xs-10 col-xs-offset-1 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 table-form">
            <form action="./login" method="post" id="login-form">
                <div class="form-group">
                    <label for="phoneNumber">手机号码</label>
                    <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="PhoneNumber" value="${try_number}">
                </div>
                <div class="form-group">
                    <label for="validateCode">验证码</label>
                    <input type="text" class="form-control" id="validateCode" name="validateCode" placeholder="Password">
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <button id="getCode" class="btn btn-block-dark">获取验证码</button>
                    </div>
                    <div class="col-xs-6">
                        <button id="vLogin" type="submit" class="btn btn-block-dark">验证登陆</button>
                    </div>
                </div>
                <div class="col-xs-12 tip-link">
                    <a class="orange-text" href="./login">密码登陆</a>
                </div>
            </form>
        </div>
        <div class="col-xs-12">
            <h5 id="faultMessage" class="fault-message">${forget_password_login_error_message}</h5>
        </div>
    </div>
</div>
</body>
</html>
