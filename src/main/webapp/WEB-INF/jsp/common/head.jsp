<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//相对路径    /seckill
	String path = request.getContextPath();
	//绝对路径    http://localhost:8080/seckill/
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>