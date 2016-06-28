<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%--微信菜单入口
微信需要对菜单url做相应的校验
校验通过才会跳转到我们设定的url
--%>
<%
    //需要重新跳转的url
    String myUrl = "http://" + request.getServerName() + request.getContextPath() + "/myWoodOrder.htm";
    //String myUrl="http://192.168.1.14:8080/lp/ownerAppWxBundling.htm";
    String wxUrl = "https://open.weixin.qq.com/connect/oauth2/authorize";
    Long startTimeState = System.currentTimeMillis();
    //微信需要的参数
    wxUrl += "?appid=" + com.lee.util.Constants.APPID;
    wxUrl += "&redirect_uri=" + java.net.URLEncoder.encode(myUrl, "utf-8");
    wxUrl += "&response_type=code";
    wxUrl += "&scope=snsapi_base";
    wxUrl += "&state=" + startTimeState;
    wxUrl += "#wechat_redirect";

    session.setAttribute("startTimeState", startTimeState);
    System.out.println(wxUrl);
    response.sendRedirect(wxUrl);
%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="utf-8">
    <title>相成科技</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
</head>
<body>
</body>
</html>