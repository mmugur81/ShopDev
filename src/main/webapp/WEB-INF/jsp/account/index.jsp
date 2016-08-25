<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:choose>
	<c:when test="${pageContext.request.userPrincipal.authenticated}">
    	Welcome ${pageContext.request.userPrincipal.name}
    	<a href="${contextPath}/account/logout">logout</a>
	</c:when>
	<c:otherwise>
	   You could try to <a href="${contextPath}/account/login">login</a>
	</c:otherwise>
</c:choose>

</body>
</html>