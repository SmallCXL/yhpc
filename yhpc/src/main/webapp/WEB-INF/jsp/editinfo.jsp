<%--
  Created by IntelliJ IDEA.
  User: chenxiaolong
  Date: 2018/9/27
  Time: 下午9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script src="js/bootoast.js" charset="UTF-8"></script>
<script src="js/bootstrap.js" charset="UTF-8"></script>

<script>
    $(function () {
        var _faultMessage = $('#faultMessage');

        var _password = $('#password');
        var _confirm_password = $('#confirmPassword');
        var _username = $('#userName');
        var _getCode = $('#getCode');
        var _inputCode = $('#inputCode');
        var _submit = $('#submit');
        var logout_link = $('#logout_link');

        var password_reg = /^\w{6,20}$/;
        var user_reg = /^[\u4e00-\u9fa5_a-zA-Z0-9]{2,10}$/;
        var code_reg = /^[0-9]{6}$/;

        logout_link.click(function () {
            return confirm("确认注销？");
        });
        $('[data-toggle="offcanvas"]').click(function () {
            $('.row-offcanvas').toggleClass('active');
        });

        //验证表单数据
        function validateForm() {
            if (${is_info_edited == 1}) {
                _faultMessage.html("本月修改次数已达上限");
                return false;
            }
            if (_password.val()===""||
                _confirm_password.val()==="" ||
                _inputCode.val()==="" ||
                _username.val()===""){
                _faultMessage.html("请输入完整的注册信息");
                return false;
            }
            if (!password_reg.test(_password.val())){
                _faultMessage.html("密码必须是6-20位的字母或数字");
                return false;
            }
            if (_password.val() !==  _confirm_password.val()){
                _faultMessage.html("两次密码不相同");
                return false;
            }

            if (!code_reg.test(_inputCode.val())){
                _faultMessage.html("验证码格式有误");
                return false;
            }
            if (!user_reg.test(_username.val())){
                _faultMessage.html("用户名长度过长或含非法字符，请检查");
                return false;
            }

            _faultMessage.html("&nbsp");
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
            if (${is_info_edited == 1}) {
                _faultMessage.html("本月修改次数已达上限");
                return false;
            }
            $.ajax({
                url:'./getCode',
                type:"GET",
                dataType:"json",
                data:{
                    "phoneNumber":${phoneNumber},
                    "type":"edit-info"
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

        _submit.click(function () {
            if (validateForm()) {
                $.ajax({
                    url:'./editSubmit',
                    type:"POST",
                    dataType:"json",
                    data:{"userName":_username.val(),
                        "password":_password.val(),
                        "inputCode":_inputCode.val()},
                    success:function (responseData){
                        if(responseData.code === 200){
                            _faultMessage.html("&nbsp");
                            alert("修改信息成功");
                            window.location.href="./userinfo";
                        }else {
                            _faultMessage.html(responseData.message);
                        }
                    },
                    error:function (message) {
                        _faultMessage.html(message);
                        console.log(message);
                    }
                });
            }
            return false;
        });
    });
</script>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <!-- Bootstrap -->
    <link href="css/bs/bootstrap.css" rel="stylesheet">
    <link href="css/bs/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="css/bootoast.css" rel="stylesheet">
    <link href="css/custom-style.css" rel="stylesheet">


    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <%--<!--[if lt IE 9]>--%>
    <%--<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>--%>
    <%--<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>--%>
    <title>阳核拼车 - 修改个人信息</title>
</head>
<body style="background-color: #fcfcfc">
<%@include file="navbar.jsp"%>

<div class="header-canvas">
    <div class="jumbotron text-center">
        <h1>阳核拼车</h1>
        <p>修改个人信息</p>
    </div>
</div>
<div class="container" >
    <div class="row row-offcanvas row-offcanvas-left" id="test">
        <div class="col-xs-6 col-sm-4 col-md-3 sidebar-offcanvas">
            <div class="list-group text-center">
                <a href="./userinfo" class="list-group-item">查看个人信息</a>
                <a href="#" class="list-group-item active">修改个人信息</a>
                <a href="./publish" class="list-group-item">发布拼车信息</a>
            </div>
        </div>
        <p class="pull-left visible-xs">
            <button class="btn btn-orange-sm btn-xs" data-toggle="offcanvas">菜单</button>
        </p>
        <div class="col-xs-12 col-sm-6 outter-container">

            <div class="inner-container">
                <div class="table-form">
                    <form id="userinfo-form">
                        <div class="row">
                            <div class="col-sm-12">
                                <h4 class="table-form-title">请填写个人信息</h4>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group" id="user-item">
                                    <label for="userName" class="control-label">用户名</label>
                                    <input type="text" class="form-control" id="userName" name="userName" placeholder="必填">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label for="password" class="control-label">新密码</label>
                                    <input type="password" class="form-control" id="password" name="password" placeholder="必填">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label for="confirmPassword" class="control-label">确认新密码</label>
                                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="必填">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label for="inputCode" class="control-label">手机验证码（接收号码：${phoneNumber}）</label>
                                    <input type="password" class="form-control" id="inputCode" name="inputCode" placeholder="必填">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-12">
                                <c:set var="bool" value="${is_info_edited == 1?'已':'未'}"/>
                                <p class="grey-text table-form-title">每月只能修改一次，您<span class="orange-text">${bool}</span>修改</p>
                            </div>
                        </div>

                        <div class="row">

                            <div class="col-xs-6">
                                <button id="getCode" class="btn btn-send-code btn-block">取验证码</button>
                            </div>
                            <div class="col-xs-6">
                                <button id="submit" class="btn btn-orange btn-block">提交修改</button>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group" >
                                    <div id="faultMessage" class="fault-message" style="margin-bottom: 15px;text-align: left">&nbsp</div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

        </div>

    </div>
</div>

<%@include file="footer.jsp"%>

</body>
</html>
