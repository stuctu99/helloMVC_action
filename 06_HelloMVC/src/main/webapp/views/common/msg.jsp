<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%
	String msg=(String)request.getAttribute("msg");
	String loc=(String)request.getAttribute("loc");
	String script=(String)request.getAttribute("script");
%>   --%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시스템메세지</title>
</head>
<body>
	<script>
		<%-- alert("<%=msg%>"); --%>
		alert("${msg}");
		<%-- <%=script!=null?script:""%> --%>
		${script!=null?script:""}
		<%-- location.replace("<%=request.getContextPath()%><%=loc%>"); --%>
		location.replace("${pageContext.request.contextPath}${loc}");
	</script>
</body>
</html>