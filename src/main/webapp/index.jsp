<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="pageTitle" value="welcome" scope="session"/>
<jsp:include page="/web/title/header.jsp"/>

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">欢迎来到我的代码库</h1>
        </div>
    </div>
</div>
<!-- /#page-wrapper -->

<jsp:include page="/web/common/footer.jsp"/>
<jsp:include page="/web/common/footerjs.jsp"/>
<%--page js--%>
