<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page language="java" contentType="text/html; charset=utf-8" %>
<%@page import="com.lee.util.GeneralConstants" %>
<%@page import="com.lee.model.User" %>
<%
    //判断是否已经登录，若未登录，回到登录界面
    if (session.getAttribute(GeneralConstants.CS_USER_INFOR) == null) {
        response.sendRedirect(request.getContextPath());
    }
    User user_left = (User) request.getSession().getAttribute(GeneralConstants.CS_USER_INFOR);
    String userType = "";
    if (user_left != null) {
        userType = user_left.getUserType();
    }
%>
<div id="col1" class="navi left">
    <% if ("31".equals(userType)) {%>
    <!--navi group-->
    <div class="navi_title">
        <p
                <c:choose>
                    <c:when test="${fn:endsWith(pageContext.request.servletPath, 'normalPerosonList.jsp')}">
                        class="system_d"
                    </c:when>
                    <c:otherwise>
                        class="user_ico"
                    </c:otherwise>
                </c:choose>><a href="<c:url value="/csuser/list.htm?setIndex=1"/>">用户管理</a></p>

        <p class="navi_line"></p>
    </div>
    <!--End of navi group-->
    <!--navi group-->

    <div class="navi_title">
        <p
                <c:choose>
                    <c:when test="${fn:endsWith(pageContext.request.servletPath, 'orderList_leader.jsp')}">
                        class="system_d"
                    </c:when>
                    <c:otherwise>
                        class="edu"
                    </c:otherwise>
                </c:choose>><a href="<c:url value="/orderLeader/orderList_leader.htm"/> ">订单指派</a></p>

        <p class="navi_line"></p>
    </div>
    <div class="navi_title">
        <p
                <c:choose>
                    <c:when test="${fn:endsWith(pageContext.request.servletPath, 'orderList_member.jsp')}">
                        class="system_d"
                    </c:when>
                    <c:otherwise>
                        class="edu"
                    </c:otherwise>
                </c:choose>><a href="<c:url value="/orderMember/orderList_member1.htm"/> ">订单管理</a></p>

        <p class="navi_line"></p>
    </div>
    <%
        }
        if ("32".equals(userType)) {
    %>

    <div class="navi_title">
        <p
                <c:choose>
                    <c:when test="${fn:endsWith(pageContext.request.servletPath, 'orderList_member1.jsp') and bigType eq '10'}">
                        class="system_d"
                    </c:when>
                    <c:otherwise>
                        class="edu"
                    </c:otherwise>
                </c:choose>><a href="<c:url value="/orderMember/orderList_member1.htm?status=10&bigType=10"/> ">地板管理</a>
        </p>

        <p class="navi_line"></p>
    </div>

    <div class="navi_title">
        <p
                <c:choose>
                    <c:when test="${fn:endsWith(pageContext.request.servletPath, 'orderList_jiaju.jsp') and bigType eq '20'}">
                        class="system_d"
                    </c:when>
                    <c:otherwise>
                        class="edu"
                    </c:otherwise>
                </c:choose>><a href="<c:url value="/orderJiajuMgr/list.htm?status=10&bigType=20"/> ">家具管理</a></p>

        <p class="navi_line"></p>
    </div>

    <div class="navi_title">
        <p
                <c:choose>
                    <c:when test="${fn:endsWith(pageContext.request.servletPath, 'orderList_other.jsp') and bigType eq '30'}">
                        class="system_d"
                    </c:when>
                    <c:otherwise>
                        class="edu"
                    </c:otherwise>
                </c:choose>><a href="<c:url value="/orderOtherMgr/list.htm?status=10&bigType=30"/> ">其他管理</a>
        </p>

        <p class="navi_line"></p>
    </div>

    <div class="navi_title">
        <p
                <c:choose>
                    <c:when test="${fn:endsWith(pageContext.request.servletPath, 'wasteStuff_list.jsp')}">
                        class="system_d"
                    </c:when>
                    <c:otherwise>
                        class="edu"
                    </c:otherwise>
                </c:choose>><a href="<c:url value="/wasteStuff/list.htm"/> ">废品杂事管理</a></p>

        <p class="navi_line"></p>
    </div>

    <div class="navi_title">
        <p
                <c:choose>
                    <c:when test="${fn:endsWith(pageContext.request.servletPath, 'wasteRecycle_list.jsp')}">
                        class="system_d"
                    </c:when>
                    <c:otherwise>
                        class="edu"
                    </c:otherwise>
                </c:choose>><a href="<c:url value="/wasteRecycle/list.htm"/> ">废品回收管理</a></p>

        <p class="navi_line"></p>
    </div>

    <%
        }
    %>
    <!--End of navi group-->
    <!--navi group-->
    <div class="navi_title">
        <p
                <c:choose>
                    <c:when test="${fn:endsWith(pageContext.request.servletPath, 'hongbao.jsp')}">
                        class="system_d"
                    </c:when>
                    <c:otherwise>
                        class="settings"
                    </c:otherwise>
                </c:choose>><a href="<c:url value="/bonus/index.htm"/> ">红包管理</a></p>

        <p class="navi_line"></p>
    </div>
    <!--End of navi group-->
    <!--navi group-->

    <% if ("31".equals(userType)) {%>
    <div class="navi_title">
        <p
                <c:choose>
                    <c:when test="${fn:endsWith(pageContext.request.servletPath, 'system_user.jsp')}">
                        class="system_d"
                    </c:when>
                    <c:otherwise>
                        class="edu"
                    </c:otherwise>
                </c:choose>><a href="<c:url value="/sysU/index.htm"/>">系统用户管理</a></p>

        <p class="navi_line"></p>
    </div>
    <!--End of navi group-->
    <!--navi group-->
    <div class="navi_title">
        <p
                <c:choose>
                    <c:when test="${fn:endsWith(pageContext.request.servletPath, 'manger2.jsp')}">
                        class="system_d"
                    </c:when>
                    <c:otherwise>
                        class="pc"
                    </c:otherwise>
                </c:choose>><a href="<c:url value="/sysC/manager.htm"/> ">系统配置</a></p>

        <p class="navi_line"></p>
    </div>
    <div class="navi_title">
        <p
                <c:choose>
                    <c:when test="${fn:endsWith(pageContext.request.servletPath, 'consultList.jsp')}">
                        class="system_d"
                    </c:when>
                    <c:otherwise>
                        class="pc"
                    </c:otherwise>
                </c:choose>><a href="<c:url value="/report/consultList.htm"/> ">接单顾问报表</a></p>

        <p class="navi_line"></p>
    </div>
    <%
        }
    %>
    <!--End of navi group-->
</div>
<script>

</script>