<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script src="js/bootoast.js" charset="UTF-8"></script>
<script>
    $(function () {
        var _getCode = $('#getCode');
        var _faultMessage = $('#faultMessage');

        var _password = $('#password');
        var _confirm_password = $('#confirmPassword');
        var _user = $('#user');
        var _phoneNumber = $('#phoneNumber');
        var _inputCode = $('#inputCode');
        var _sex = $('#sex');
        var _submit=$('#form-submit');

        var phone_reg = /^[1][3456789][0-9]{9}$/;
        var password_reg = /^\w{6,20}$/;
        var user_reg = /^[\u4e00-\u9fa5_a-zA-Z0-9]{2,10}$/;
        var code_reg = /^[0-9]{6}$/;

        var userStatus = $('#userStatus');
        var phoneNumberStatus = $('#phoneNumberStatus');
        var passwordStatus = $('#passwordStatus');
        var confirmPasswordStatus = $('#confirmPasswordStatus');
        var inputCodeStatus = $('#inputCodeStatus');

        var user_item = $('#user-item');
        var phoneNumber_item = $('#phoneNumber-item');
        var password_item = $('#password-item');
        var confirmPassword_item = $('#confirmPassword-item');
        var inputCode_item = $('#inputCode-item');

        var success_border = "form-group has-success has-feedback";
        var success_icon = "glyphicon glyphicon-ok form-control-feedback";
        var error_border = "form-group has-error has-feedback";
        var error_icon = "glyphicon glyphicon-remove form-control-feedback";

        $('[data-toggle="tooltip"]').tooltip();

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
                    "type":"register"
                },
                success:function(responseData) {
                    if(responseData.code === 200){
                        startClock();
                        toast("已发送，请查看手机", "success");
                    }else {
                        toast(responseData.message, "danger");
                    }
                },
                error:function (message) {
                    toast("请求超时，请重新登录", "danger");
                    console.log(message);
                }
            });
            return false;//阻止表单的自动提交
        });
        //验证表单数据
        function validateForm() {
            if (_password.val()===""||
                _confirm_password.val()==="" ||
                _user.val()===""||
                _phoneNumber.val()===""||
                _inputCode.val()===""){
                _faultMessage.html("请输入完整的注册信息");
                return false;
            }
            if (_password.val() !==  _confirm_password.val()){
                _faultMessage.html("两次密码不相同");
                return false;
            }
            if (!code_reg.test(_inputCode.val())){
                _faultMessage.html("请输入6位验证码数字");
                return false;
            }
            if (!password_reg.test(_password.val())){
                _faultMessage.html("密码必须是6-20位的字母或数字");
                return false;
            }
            if (!user_reg.test(_user.val())){
                _faultMessage.html("用户名必须是10字符以内中文、英文或数字");
                return false;
            }
            if (!phone_reg.test(_phoneNumber.val())){
                _faultMessage.html("手机号码格式有误，请检查");
                return false;
            }
            _faultMessage.html("");
            return true;
        }

        //实现提交post数据并自动跳转
        function postToLogin (url,phoneNumber,password)
        {
            var form = $("<form method='post'></form>");
            form.attr({"action":url});
            var input1 = $("<input type='hidden'>");
            input1.attr({"name":"phoneNumber"});
            input1.val(phoneNumber);
            form.append(input1);
            var input2 = $("<input type='hidden'>");
            input2.attr({"name":"password"});
            input2.val(password);
            form.append(input2);
            form.submit();
        }
        //提交注册表单
        _submit.click(function () {
            if (validateForm()){
                $.ajax({
                    url:'./registerSubmit',
                    type:"POST",
                    dataType:"json",
                    data:{"phoneNumber":_phoneNumber.val(),
                        "userName":_user.val(),
                        "password":_password.val(),
                        "sex":_sex.val(),
                        "inputCode":_inputCode.val()},
                    success:function (responseData) {
                        if (responseData.code === 200) {
                            alert("注册成功!");
                            postToLogin("./login",_phoneNumber.val(),_password.val());
                        }else {
                            $('#faultMessage').html(responseData.message);
                        }
                    },
                    error:function (message) {
                        $('#faultMessage').html(message);
                        console.log(message);
                    }
                });
            }
            return false;//用bootstrap之后，要添加这个语句，否则会重新定向一次原有的页面
        });

        //人性化提醒，包括表单错误提醒，和自动提示工具
        _user.change(function(){
            if (!user_reg.test(_user.val())){
                user_item.attr("class",error_border);
                userStatus.attr("class",error_icon);
                user_item.tooltip('show');
                setTimeout(function () {user_item.tooltip('hide');},3000);
            }
            else {
                user_item.attr("class",success_border);
                userStatus.attr("class",success_icon);
            }
        });
        _phoneNumber.change(function(){
            if (!phone_reg.test(_phoneNumber.val())){
                phoneNumber_item.attr("class",error_border);
                phoneNumberStatus.attr("class",error_icon);
                phoneNumber_item.tooltip('show');
                setTimeout(function () {phoneNumber_item.tooltip('hide');},3000);
            }
            else {
                phoneNumber_item.attr("class",success_border);
                phoneNumberStatus.attr("class",success_icon);
            }
        });
        _password.change(function(){
            if (!password_reg.test(_password.val())){
                password_item.attr("class",error_border);
                passwordStatus.attr("class",error_icon);
                password_item.tooltip('show');
                setTimeout(function () {password_item.tooltip('hide');},3000);
            }
            else {
                password_item.attr("class",success_border);
                passwordStatus.attr("class",success_icon);
            }
        });
        _confirm_password.change(function(){
            if (_password.val()!==_confirm_password.val()){
                confirmPassword_item.attr("class",error_border);
                confirmPasswordStatus.attr("class",error_icon);
                confirmPassword_item.tooltip('show');
                setTimeout(function () {confirmPassword_item.tooltip('hide');},3000);
            }
            else {
                confirmPassword_item.attr("class",success_border);
                confirmPasswordStatus.attr("class",success_icon);
            }
        });
        _inputCode.change(function(){
            if (!code_reg.test(_inputCode.val())){
                inputCode_item.attr("class",error_border);
                inputCodeStatus.attr("class",error_icon);
                inputCode_item.tooltip('show');
                setTimeout(function () {inputCode_item.tooltip('hide');},3000);
            }
            else {
                inputCode_item.attr("class",success_border);
                inputCodeStatus.attr("class",success_icon);
            }
        });
    });
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
    <%--<!--[if lt IE 9]>--%>
    <%--<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>--%>
    <%--<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>--%>
    <title>阳核拼车 - 注册</title>
    <%--end if--%>
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
            <p>注册</p>
        </div>
        <div class="row">
            <div class="col-xs-10 col-xs-offset-1 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 table-form">
                <form class="form-horizontal" id="register-form">
                    <div class="form-group" id="user-item" data-delay="100" data-toggle="tooltip" data-placement="top" title="10个字符以内昵称" data-trigger="manual">
                        <label for="user" class="col-sm-4 control-label">用户名</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="user" name="user" placeholder="10个字符以内，不能含空格等">
                            <span id="userStatus" aria-hidden='true'></span>
                        </div>
                    </div>
                    <div class="form-group" id="sex-item">
                        <label for="sex" class="col-sm-4 control-label">性别</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="sex" name="sex">
                                <option value="0" disabled="disabled">请选择</option>>
                                <option value="1">男士</option>
                                <option value="2">女士</option>
                            </select>
                        </div>
                        <span id="sexStatus" aria-hidden='true'></span>
                    </div>
                    <div class="form-group" id="phoneNumber-item" data-delay="100" data-toggle="tooltip" data-placement="top" title="请输入正确的手机号" data-trigger="manual">
                        <label for="phoneNumber" class="col-sm-4 control-label">手机号</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="phoneNumber">
                            <span id="phoneNumberStatus" aria-hidden='true'></span>
                        </div>
                    </div>
                    <div class="form-group" id="password-item" data-delay="100" data-toggle="tooltip" data-placement="top" title="密码必须是6-20位的字母或数字" data-trigger="manual">
                        <label for="password" class="col-sm-4 control-label">密码</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" id="password" name="password" placeholder="password">
                            <span id="passwordStatus" aria-hidden='true'></span>
                        </div>
                    </div>
                    <div class="form-group" id="confirmPassword-item" data-delay="100" data-toggle="tooltip" data-placement="top" title="两次密码不相同" data-trigger="manual">
                        <label for="confirmPassword" class="col-sm-4 control-label">确认密码</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="password">
                            <span id="confirmPasswordStatus" aria-hidden='true'></span>
                        </div>
                    </div>

                    <div class="form-group" id="inputCode-item" data-delay="100" data-toggle="tooltip" data-placement="top" title="验证码格式有误" data-trigger="manual">
                        <div class="col-sm-4 col-xs-4">
                            <button id="getCode" class="btn btn-send-code">获取验证码</button>
                        </div>
                        <div class="col-sm-8 col-xs-8">
                            <input type="text" class="form-control" id="inputCode" name="inputCode" placeholder="请输入6位数字验证码">
                            <span id="inputCodeStatus" aria-hidden='true'></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12 col-sm-12">
                            <button id="form-submit" class="btn btn-block-dark">注册</button>
                        </div>
                    </div>
                    <div class="col-xs-12 tip-link">
                        <a class="orange-text" href="./login">已有账号？直接登录</a>
                    </div>
                </form>
            </div>
            <div class="col-xs-12">
                <div class="fault-message" id="faultMessage"></div>
            </div>
        </div>
    </div>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="js/bootstrap.js"></script>
</body>
</html>
