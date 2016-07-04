<%@page language="java" contentType="text/html; charset=utf-8" %>

<%@ page import="com.lee.util.GeneralConstants"%>
<div class="header">
    <div class="logo_name">
        <h1 class="system_name">后台管理系统</h1>
    </div>
    <a href="javascript:void(0)">
        <div class="login_user_name">
            <p class="user_name">李志恒&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/manageStyle/img/arrow_down.png" class="arrow" onclick="loginout()"></p>
        </div>
    </a>
</div>
<script src="<%=request.getContextPath()%>/moibleStyle/js/jquery-2.1.4.min.js"></script>
<script>
    function loginout(){
        if(confirm("是否确认退出?")){
            window.location.href= "<%=request.getContextPath()%>/cs_logout.htm";
        }
    }

</script>