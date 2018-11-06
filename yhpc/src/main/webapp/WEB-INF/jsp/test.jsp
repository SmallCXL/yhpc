<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
    <title>jquery特效之城市三级联动插件 - 更多特效请到www.dawnfly.cn</title>
    <meta name="keywords" content="jQuery城市三级联动,代码特效,前端框架">
    <meta name="description" content="前端框架代码特效JS城市三级联动">
    <style type="text/css">
        html,body{height:auto;}
        #province select{margin-left:10px; width:100px}
    </style>
</head>
<body>
<%--<style>--%>
    <%--ul#bcty365_nav{padding-left:50px; margin-bottom:10px; border-bottom:2px solid #ccc; overflow:hidden; _zoom:1;}--%>
    <%--ul#bcty365_nav li{float:left; display:inline; margin:10px;}--%>
    <%--ul#bcty365_nav li a{display:block;color:#000000; font-size:16px;}--%>
    <%--ul#bcty365_nav li a,#wimoban_p,#wimoban_p a{ font-family:"微软雅黑";}--%>
    <%--ul#bcty365_nav li a:hover,#wimoban_p a:hover{color:red;}--%>
<%--</style>--%>
<%--<ul id="bcty365_nav">--%>
    <%--<li><a target="_blank" title="首页" href="http://www.dawnfly.cn">首页</a></li>--%>
    <%--<li><a target="_blank"  title="Web前端" href="http://www.dawnfly.cn/125.html">Web前端</a></li>--%>
    <%--<li><a target="_blank" title="SEO优化" href="http://www.dawnfly.cn/128.html">SEO优化</a></li>--%>
    <%--<li><a target="_blank" title="CMS建站" href="http://www.dawnfly.cn/129.html">CMS建站</a></li>--%>
    <%--<li><a target="_blank" title="热爱编程" href="http://www.dawnfly.cn/135.html">热爱编程</a></li>--%>
    <%--<li><a target="_blank" title="资源共享" href="http://www.dawnfly.cn/136.html">资源共享</a></li>--%>
    <%--<li><a target="_blank" title="开发资讯" href="http://www.dawnfly.cn/142.html">开发资讯</a></li>--%>
    <%--<li><a target="_blank" title="经验分享" href="http://www.dawnfly.cn/143.html">经验分享</a></li>--%>
    <%--<li><a target="_blank" title="精彩案例" href="http://www.dawnfly.cn/144.html">精彩案例</a></li>--%>
    <%--<li><a target="_blank" title="创业漫漫谈" href="http://www.dawnfly.cn/145.html">创业漫漫谈</a></li></ul>--%>
<div id="province"></div>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.provincesCity.js"></script>
<script type="text/javascript" src="js/provincesData.js"></script>
<script type="text/javascript">
    /*调用插件*/
    $(function(){
        $("#province").ProvinceCity();
    });
</script>
</body>
</html>