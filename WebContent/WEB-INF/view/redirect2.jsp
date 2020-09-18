<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
	String url = (String)request.getAttribute("url");
%>
<!DOCTYPE html>
<html>
<head>
<script>
window.onload = function(){
	location.href='<%=url%>';
};
</script>
</head>
<body>

</body>
</html>