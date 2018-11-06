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
<script src="js/bootstrap.js" charset="UTF-8"></script>
<script src="js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

<script>
    $(function () {
        var _addition_label = $('#addition-label');
        var region_reg = /^[\u4e00-\u9fa5_a-zA-Z0-9,:.?!()\[\]【】，。、？：\-！（）\s]{1,20}$/;
        var addition_reg = /^[\u4e00-\u9fa5_a-zA-Z0-9,:.?!()\[\]【】，。、？：\-！（）\s]{1,100}$/;
        var _departure = $('#departure');
        var _arrival = $('#arrival');
        var _addition = $('#addition');
        var _travel_time = $('#travel_time');
        var _faultMessage = $('#faultMessage');
        var _form = $('#publish-form');
        var type_lfd = $('#lf_driver');
        var type_lfp = $('#lf_passenger');
        var logout_link = $('#logout_link');
        var _submit = $('#submit');

        logout_link.click(function () {
            return confirm("确认注销？");
        });
        $('[data-toggle="offcanvas"]').click(function () {
            $('.row-offcanvas').toggleClass('active');
        });


        function validateForm() {
            if(${publish_frequency > 4}){
                _faultMessage.html("今日发布次数已达上限");
                return false;
            }
            if (_departure.val().trim()===""||_arrival.val().trim()===""||
                _addition.val().trim()===""||_travel_time.val().trim()===""){
                _faultMessage.html("请输入完整的拼车信息");
                return false;
            }
            if (_addition.val().length>100){
                _faultMessage.html("备注信息过长，请修改");
                return false;
            }
            if (_departure.val().length>20){
                _faultMessage.html("出发信息过长，请修改");
                return false;
            }
            if (_arrival.val().length>20){
                _faultMessage.html("到达信息过长，请修改");
                return false;
            }
            if(!region_reg.test(_departure.val()) ||
               !region_reg.test(_arrival.val().trim()) ||
               !addition_reg.test(_addition.val().trim())){
                _faultMessage.html("表单中包含非法字符，请检查");
                return false;
            }
            _faultMessage.html("&nbsp");
            return true;
        }

        _submit.click(function () {
            if (validateForm()) {
                $.ajax({
                    url:'./publishSubmit',
                    type:"POST",
                    dataType:"json",
                    data:{"departure":_departure.val(),
                        "arrival":_arrival.val(),
                        "addition":_addition.val(),
                        "travel_time":_travel_time.val(),
                        "type": $("input[name='type']:checked").val()},
                    success:function (responseData){
                        if(responseData.code === 200){
                            _faultMessage.html("&nbsp");
                            alert("发布信息成功");
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

        _addition.keyup(function(){
            var l = $(this).val().length;
            _addition_label.html("备注信息（"+"限 "+l+"/100字符）");
            if(l > 100){
                $(this).addClass("error");
                _addition_label.addClass("red-text");
            }else{
                $(this).removeClass("error");
                _addition_label.removeClass("red-text");
            }
        });

        type_lfd.click(function () {
            _addition.attr("placeholder","为了您的信息安全，请不要在此处暴露联系方式，可填写上车地点，乘车人数等信息");
        });
        type_lfp.click(function () {
            _addition.attr("placeholder","为了您的信息安全，请不要在此处暴露联系方式，可填写途经地点，剩余车位等信息");
        });

        function getToday() {
            var date = new Date();
            var m = date.getMonth()+1;
            return date.getFullYear()+"-"+m+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes();
        }

        $(".form_datetime").datetimepicker({
            language:  'zh-CN',
            format: "yyyy-mm-dd hh:ii",
            autoclose: 1,
            todayBtn: 1,
            startDate: getToday(),
            todayHighlight:true,
            minuteStep: 20
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
    <link href="css/custom-style.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <%--<!--[if lt IE 9]>--%>
    <%--<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>--%>
    <%--<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>--%>
    <title>阳核拼车 - 发布拼车信息</title>
</head>
<body style="background-color: #fcfcfc">
<%@include file="navbar.jsp"%>

<div class="header-canvas">
    <div class="jumbotron text-center">
        <h1>阳核拼车</h1>
        <p>发布拼车信息</p>
    </div>
</div>
<div class="container" >
    <div class="row row-offcanvas row-offcanvas-left">
        <div class="col-xs-6 col-sm-4 col-md-3 sidebar-offcanvas">
            <div class="list-group text-center">
                <a href="./userinfo" class="list-group-item">查看个人信息</a>
                <a href="./editinfo" class="list-group-item">修改个人信息</a>
                <a href="#" class="list-group-item active">发布拼车信息</a>
            </div>
        </div>
        <p class="pull-left visible-xs">
            <button class="btn btn-orange-sm btn-xs" data-toggle="offcanvas">菜单</button>
        </p>
        <div class="col-xs-12 col-sm-8 col-md-9 outter-container">

            <div class="inner-container">
                <div class="table-form">
                    <form id="publish-form">
                        <div class="form-group">
                            <h4 class="table-form-title">请填写拼车信息</h4>
                        </div>
                        <div class="form-group">
                            <label class="radio-inline col-xs-6">
                                <input type="radio" name="type" id="lf_passenger" value="1" checked="checked"> 我想找乘客
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="type" id="lf_driver" value="2"> 我想找司机
                            </label>
                        </div>

                        <div class="form-group" id="departure-item">
                            <label for="departure" class="control-label">出发地</label>
                            <input type="text" class="form-control" id="departure" name="departure" placeholder="必填">

                        </div>
                        <div class="form-group" id="arrival-item">
                            <label for="arrival" class="control-label">目的地</label>

                            <input type="text" class="form-control" id="arrival" name="arrival" placeholder="必填">
                        </div>

                        <div class="form-group">
                            <label for="dtp_input1" class="control-label">出行时间</label>
                            <div class="input-group date form_datetime col-md-5" data-link-field="dtp_input1">
                                <input class="form-control" size="16" type="text" id="travel_time" name="travel_time" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            <input type="hidden" id="dtp_input1" value=""/><br/>
                        </div>

                        <div class="form-group">
                            <label for="addition" class="control-label" id="addition-label">备注信息（限 0/100字符）</label>
                            <textarea class="form-control" rows="3" id="addition" name="addition" placeholder="为了您的信息安全，请不要在此处暴露您的联系方式，可填写途经地点，剩余车位等信息"></textarea>
                        </div>

                        <div class="form-group">
                            <p class="grey-text table-form-title">每天只能发布5条信息，您已发布<span class="orange-text">${publish_frequency}</span>条</p>
                            <button id="submit" class="btn btn-large-orange">发布</button>
                        </div>
                    </form>
                    <div id="faultMessage" class="fault-message" style="margin-bottom: 15px;text-align: left">&nbsp</div>
                </div>
            </div>

        </div>

    </div>
</div>

<%@include file="footer.jsp"%>
</body>
</html>
