<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>后台管理系统</title>
<link href="<%=request.getContextPath()%>/manageStyle/img/css/css.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="wrap">
    <div class="login_area">
         <div class="logo_login"></div>
         <div class="login_form_area mt40">
              <div class="login_form_group">
                   <p class="login_name">用户名:</p>
                   <p class="login_form"><input name="loginName" id="loginName" type="text" class="formstyle" value="" /></p>
              </div>
              <div class="login_form_group mt15">
                   <p class="login_name">密 &nbsp;&nbsp;码:</p>
                   <p class="login_form"><input name="password" id="password" type="password" class="formstyle" value="" /></p>
              </div>
              <div class="button_area mt20">
                   <a><button name="Submit" class="button log left" onclick="login()">登录</button></a>
                   <button class="button log right">取消</button>
              </div>
         </div>
    </div>
</div>
<img src="<%=request.getContextPath()%>/manageStyle/img/login_bg.jpg" id="background" alt="" />
<script src="<%=request.getContextPath()%>/moibleStyle/js/jquery-2.1.4.min.js"></script>
<script>
     document.onkeydown = function(evt){ var evt = window.event?window.event:evt; if (evt.keyCode==13) { login(); } };
     $("#loginName").focus();
     function login() {
          var loginName = $("#loginName").val();
          var password = $("#password").val();
          if(loginName == null || loginName == ""){
               alert("请输入用户名");
               return false;
          }
          if(password == null || password == ""){
               alert("请输入密码");
               return false;
          }

          $.post("<c:url value="/csLogin.htm"/>", {loginName:loginName,password:password}, function (reponse) {
               if(reponse.outResult == 1){
                    if(reponse.userType == '31'){
                         location.href = "<c:url value="/csuser/list.htm?setIndex=1"/>";
                    }else{
                         location.href = "<c:url value="/orderMember/orderList_member1.htm?status=10&bigType=10"/>";
                    }
               }else{
                    alert(reponse.outResultReason);
               }
          })
     }

</script>
</body>
</html>
