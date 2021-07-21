<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>正在加载...</title>
</head>
<body>
<form action="${p2p_return_url}" method="post">
<c:forEach items="${paramMap}" var="mymap">
<input type="hidden" id="${mymap.key}" name="${mymap.key}" value="${mymap.value}"/>
</c:forEach>
</form>
<script>document.forms[0].submit();</script>
</body>
</html>