<%@page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style>
.body{ background:#f5f7f8}
</style>
</head>

<body class="body">
		<%
			Exception ex = (Exception)request.getAttribute("ex");
		%>
<div class="c404 w">
    <div <%--style="display:none;"--%>>
		<%
			out.println(ex.getMessage());
		    ex.printStackTrace(new java.io.PrintWriter(out));
			ex.printStackTrace();
		%>
		</div>
</div>

</body>
</html>
