<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="path" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${param.url}" />
<c:set var="pathurl" value="${path}/${url}" />
<c:set var="formIdex" value="${param.formIdex}" />
<% 
if(null!=request.getParameter("resultViewName") && !request.getParameter("resultViewName").equals("resultView")){
	pageContext.setAttribute("resultView", request.getAttribute(request.getParameter("resultViewName")),PageContext.PAGE_SCOPE);
}
%>
 <div class="next1">
 
<table style="width:100%; height:20px; padding-bottom:1px; margin-left:1px; align:right">
<tr style="align:right">
	<td nowrap="nowrap">
		<%--第${resultView eq null ? "1" : resultView.currentPage}页/共${resultView eq null ? "0" : resultView.pageCount}页&nbsp;--%>
		<c:choose>
			<c:when test="${resultView.havePrePage eq true}">
				<a href="javascript:click_first();">&lt;&lt;首页</a>&nbsp;
				<a href="javascript:click_previous();">&lt;上一页</a>
			</c:when>
			<c:otherwise>
				&lt;&lt;首页&nbsp;&lt;上一页&nbsp;
			</c:otherwise>
		</c:choose>
		&nbsp;||&nbsp;
		<c:choose>
			<c:when test="${resultView.havaNextPage eq true}">
				<a href="javascript:click_next();">下一页&gt;</a>&nbsp;
				<a href="javascript:click_last();">尾页&gt;&gt;</a>
			</c:when>
			<c:otherwise>
				&nbsp;下一页&gt;&nbsp;尾页&gt;&gt;
			</c:otherwise>
		</c:choose>
		&nbsp;转到<SELECT name="indexChange" id="indexChange"onchange="getCurrentPage(this.value);">
			<c:forEach var="index" begin="1" end="${resultView.pageCount}" step="1">
			<option value="${index}" ${resultView.currentPage eq index ? "selected" : ""}>第${index}页</option>
			</c:forEach>
		</SELECT>
		&nbsp;每页<select name="pageSize" id="pageSize" onchange="setEveryPage();">
			<c:forEach var="pageIndex" begin="20" end="100" step="20">
			<option value="${pageIndex}" ${resultView.pageSize eq pageIndex ? "selected" : ""}>${pageIndex}条</option>
			</c:forEach>
		</select>/共${resultView.totalCount}条
	</td>
</tr>
</table>

<input id="toPage" name="toPage" type="hidden" value="1"/>
</div>
<SCRIPT>

	var form_index="${formIdex}";
	if (form_index=="")form_index=0;
	var str_url="${url}";
	function click_first(){
		if (str_url!="")document.forms[form_index].action="${pathurl}";
		document.forms[form_index].submit();
	}
	function click_previous(){
		var toPage = document.getElementById("toPage");
		toPage.value=parseInt("${resultView.currentPage}")-1;
		if (str_url!="")document.forms[form_index].action="${pathurl}";
		document.forms[form_index].submit();
	}
	function click_next(){
		var toPage = document.getElementById("toPage");
		toPage.value=parseInt("${resultView.currentPage}")+1;
		if (str_url!="")document.forms[form_index].action="${pathurl}";
		document.forms[form_index].submit();
	}
	function click_last(){
		var toPage = document.getElementById("toPage");
		toPage.value="${resultView.pageCount}";
		if (str_url!="")document.forms[form_index].action="${pathurl}";
		document.forms[form_index].submit();
	}
	
	function getCurrentPage(index){
		var toPage = document.getElementById("toPage");
		toPage.value= index;
		if (str_url!="")document.forms[form_index].action="${pathurl}";
		document.forms[form_index].submit();
	}

	function setEveryPage(){
		var toPage = document.getElementById("toPage");
		toPage.value= "${resultView.currentPage}";
		if (str_url!="")document.forms[form_index].action="${pathurl}";
		document.forms[form_index].submit();
	}
</SCRIPT>

