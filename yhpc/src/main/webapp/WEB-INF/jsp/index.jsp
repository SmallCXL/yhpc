<%--
  Created by IntelliJ IDEA.
  User: chenxiaolong
  Date: 2018/9/14
  Time: 下午7:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="js/bootstrap.js"></script>
<script>
    $(function () {
        var _form =$('#search-form');
        var _search_driver = $('#search-driver');
        var _search_passenger = $('#search-passenger');
        var logout_link = $('#logout_link');
        var _arrival = $('#arrival');
        var _departure = $('#departure');

        logout_link.click(function () {
            return confirm("确认注销？");
        });
        _search_driver.click(function () {
            $.ajax({
                url:"./search",
                type:"POST",
                dataType:"json",
                data:{
                    "type":"1",
                    "departure":_departure.val(),
                    "arrival":_arrival.val()
                },
                success: function(){
                    window.location.href="./index";
                },
                error:function(){
                    alert("请求超时，请稍后重试");
                }
            });
            return false;//表格中的按钮，click事件，如果不加return false，则会自动发一个get的请求
        });
        _search_passenger.click(function () {
            $.ajax({
                url:"./search",
                type:"POST",
                dataType:"json",
                data:{
                    "type":"2",
                    "departure":_departure.val(),
                    "arrival":_arrival.val()
                },
                success: function(){
                    window.location.href="./index";
                },
                error:function(){
                    alert("请求超时，请稍后重试");
                }
            });
            return false;
        });
    });
</script>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>阳核拼车</title>

    <!-- Bootstrap -->
    <link href="css/bs/bootstrap.css" rel="stylesheet">
    <link href="css/custom-style.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <!--<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>-->
    <!--<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>-->
    <!--[endif]-->
</head>
<body style="background-color: #fcfcfc">
<%@include file="navbar.jsp"%>
    <div class="header-canvas">
        <div class="container" style="height: max-content">
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-sm-offset-2">
                    <div class="jumbotron text-center">
                        <h1>阳核拼车</h1>
                        <shiro:guest>
                            <p>拼车出行，共享便捷</p>
                        </shiro:guest>
                        <shiro:hasAnyRoles name="user,admin">
                            <p>你好，${username}</p>
                        </shiro:hasAnyRoles>
                        <form>
                            <div class="row">
                                <div class="col-xs-6 col-sm-6">
                                    <input name="departure" type="text" class="form-control" value="${departure}" id="departure" placeholder="出发地">
                                </div>
                                <div class="col-xs-6 col-sm-6">
                                    <input name="arrival" type="text" class="form-control" value="${arrival}" id="arrival" placeholder="目的地">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-6">
                                    <button class="btn btn-block btn-search-drv " id="search-driver">我找司机</button>
                                </div>
                                <div class="col-xs-6">
                                    <button class="btn btn-block btn-search-psg " id="search-passenger">我找乘客</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

     <div class="wrapper">
         <c:if test="${!empty travel_info_list}">
             <div class="row">
                    <c:forEach var="info" items="${travel_info_list}" varStatus="st">
                        <div class="col-xs-12 col-sm-6" >
                            <c:set var="info_type_class" value="${info.type_==2?'item-orange':'item-green'}"/>
                            <div class="card-item ${info_type_class}" data-toggle="modal" data-target="#${info.id}">
                                <h5 class="text-left dark-text info-title">${info.departure_} <span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span> ${info.arrival_}</h5>
                                <div class="row">
                                    <p class="grey-text col-xs-5">${fn:substring(info.travel_time_,5,16)}</p>
                                    <p class="grey-text col-xs-7 text-right">更新于：${fn:substring(info.publish_time_,5,10)}</p>
                                </div>
                                <p class="text-left grey-text addition">${info.addition_}</p>
                            </div>
                        </div>

                        <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" id="${info.id}">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-content text-left">
                                        <div class="modal-header">
                                            <c:set var="user_type" value="${info.type_==2?'乘客':'车主'}"/>
                                            <h4 class="dark-text">${user_type}信息</h4>
                                        </div>
                                        <shiro:hasAnyRoles name="admin,user">
                                            <div class="modal-body">
                                                <p class="grey-text"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${user_type}：${info.user.name_} ${info.user.sex_==1?'先生':'女士'}</p>
                                                <p class="grey-text"><span class="glyphicon glyphicon-phone-alt" aria-hidden="true"></span> 电话：${info.user.phone_}</p>
                                            </div>
                                            <div class="modal-header">
                                                <h4 class="text-left dark-text">${info.departure_} <span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span> ${info.arrival_}</h4>
                                            </div>
                                            <div class="modal-body">
                                                <p class="grey-text"><span class="glyphicon glyphicon-time" aria-hidden="true"></span> 出行：${fn:substring(info.travel_time_,5,16)}</p>
                                                <p class="grey-text"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 发布：${fn:substring(info.publish_time_,5,16)}</p>
                                                <p class="text-left grey-text"><span class="glyphicon glyphicon-comment" aria-hidden="true"></span> 备注：${info.addition_}</p>
                                            </div>
                                        </shiro:hasAnyRoles>
                                        <shiro:guest>
                                            <div class="modal-body">
                                                <p class="grey-text">请<a href="./login" class="orange-text">登录</a>后查看${user_type}信息</p>
                                            </div>
                                        </shiro:guest>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
             </div>
             <div class="row">
                 <div class="text-center">
                     <nav aria-label="Page navigation">
                         <ul class="pagination">
                             <li><a href="./search?page=1" class="dark-text">第一页</a></li>
                             <c:set var="prePage" value="${currentPage==1?'disabled':''}"/>
                             <li class="${prePage}">
                                 <a href="./search?page=${currentPage-1>1?currentPage-1:1}" aria-label="Previous" class="dark-text">
                                     <span aria-hidden="true">&laquo;</span>
                                 </a>
                             </li>
                             <c:forEach begin="1" end="${totalPages}" varStatus="st">
                                 <c:set var="active" value="${st.index==currentPage?'active':''}"/>
                                 <li class="${active}"><a href="./search?page=${st.index}" class="dark-text">${st.index}</a></li>
                             </c:forEach>

                             <c:set var="nextPage" value="${currentPage==totalPages?'disabled':''}"/>
                             <li class=${nextPage}>
                                 <a href="./search?page=${currentPage+1<totalPages?currentPage+1:totalPages}" class="dark-text">
                                     <span aria-hidden="true">&raquo;</span>
                                 </a>
                             </li>
                             <li class="waves-effect"><a href="./search?page=${totalPages}" class="dark-text">最后页</a></li>
                         </ul>
                     </nav>
                 </div>
             </div>
         </c:if>
         <c:if test="${empty travel_info_list}">
             <div class="row" style="margin-top: 15px">
                 <h3 class="orange-text text-center shadow-text">暂无出行信息</h3>
             </div>
             <div class="row" style="margin-top: 15px">
                 <a href="./home"><h5 class="dark-text text-center">返回首页</h5></a>
             </div>
         </c:if>
      </div>
<%@include file="footer.jsp"%>
</body>
</html>