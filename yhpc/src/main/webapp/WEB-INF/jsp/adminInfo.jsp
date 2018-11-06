<%--
  Created by IntelliJ IDEA.
  User: chenxiaolong
  Date: 2018/10/4
  Time: 下午4:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script src="js/bootstrap.js" charset="UTF-8" type="text/javascript"></script>
<script>
    $(function () {
        var logout_link = $('#logout_link');

        logout_link.click(function () {
            return confirm("确认注销？");
        });
        $('[data-toggle="offcanvas"]').click(function () {
            $('.row-offcanvas').toggleClass('active');
        });
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
    <link href="css/bs/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="css/custom-style.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <%--<!--[if lt IE 9]>--%>
    <%--<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>--%>
    <%--<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>--%>
    <title>阳核拼车 - 管理页面</title>
</head>
<body style="background-color: #fcfcfc">
<%@include file="navbar.jsp"%>

<div class="header-canvas">
    <div class="jumbotron text-center">
        <h1>阳核拼车</h1>
        <p>管理页面</p>
    </div>
</div>

<div class="container" >
    <div class="row row-offcanvas row-offcanvas-left">
        <div class="col-xs-6 col-sm-4 col-md-3 sidebar-offcanvas">
            <div class="list-group text-center">
                <a href="#" class="list-group-item active">用户状态一览</a>
                <a href="./userSupervise" class="list-group-item">拼车信息一览</a>
            </div>
        </div>
        <p class="pull-left visible-xs">
            <button class="btn btn-orange-sm btn-xs" data-toggle="offcanvas">菜单</button>
        </p>
        <div class="col-xs-12 col-sm-8 col-md-9 outter-container">

            <div class="inner-container">
                <div class="table-form">
                    <form id="publish-form" action="./userSuperviseSubmit" method="post">
                        <div class="form-group">
                            <h4 class="table-form-title">用户状态一览</h4>
                        </div>
                        <table id="sss" class="table table-hover">
                            <thead>
                            <tr>
                                <th class="col-xs-4">用户</th>
                                <th class="col-xs-6">状态</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr id="1" data-toggle="modal" data-target="#data${travel.id}">

                                <td>陈小龙cxl</td>
                                <td>
                                    <label class="radio-inline">
                                        <input type="radio" name="t1" id="lf_passenger" value="1" checked="checked">锁定
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="t1" id="lf_driver" value="2">正常
                                    </label>
                                </td>
                            </tr>
                            <tr id="2" data-toggle="modal" data-target="#data${travel.id}">

                                <td>陈小龙cxl</td>
                                <td>
                                    <label class="radio-inline">
                                        <input type="radio" name="t2" id="lf_passenger" value="1" checked="checked">锁定
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="t2" id="lf_driver" value="2">正常
                                    </label>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%-- 装载模态数据 --%>
<c:if test="${!empty userinfo.travelList}">
    <c:forEach var="travel_content" items="${userinfo.travelList}">
        <div class="modal fade" id="data${travel_content.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="text-left dark-text">${travel_content.departure_} <span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span> ${travel_content.arrival_}</h4>
                    </div>
                    <div class="modal-body">
                        <c:set var="type_content" value="${travel_content.type_ == 1?'找乘客':'找司机'}"/>
                        <p class="grey-text"><span class="glyphicon glyphicon-tag" aria-hidden="true"></span> 类型：${type_content}</p>
                        <p class="grey-text"><span class="glyphicon glyphicon-time" aria-hidden="true"></span> 出行：${fn:substring(travel_content.travel_time_,5,16)}</p>
                        <p class="grey-text"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 发布：${fn:substring(travel_content.publish_time_,5,16)}</p>
                        <p class="text-left grey-text"><span class="glyphicon glyphicon-comment" aria-hidden="true"></span> 备注：${travel_content.addition_}</p>
                    </div>
                    <div class="modal-footer">
                        <button data-dismiss="modal" type="button" class="btn btn-danger btn-xs" id="delete${travel_content.id}"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 删除本条</button>
                        <script>
                            $(function () {
                                $('#delete${travel_content.id}').click(function () {
                                    $.ajax({
                                        url:'./delete',
                                        type:'post',
                                        dataType:'json',
                                        data:{"id":"${travel_content.id}"},
                                        success:function(responseData) {
                                            if (responseData.code === 200) {
                                                $('#tr${travel_content.id}').hide();
                                            }
                                            else {
                                                alert(responseData.message);
                                            }
                                        },
                                        error: function () {
                                            alert("请求失败，请稍后再操作");
                                        }
                                    });
                                });
                            })
                        </script>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</c:if>

<%@include file="footer.jsp"%>
</body>
</html>
