<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:set var="pageTitle" value="Account home page title"/>
<%@ include file="../header.jsp" %>

<c:choose>
	<c:when test="${pageContext.request.userPrincipal.authenticated}">
    	Welcome ${pageContext.request.userPrincipal.name}
    	<a href="${contextPath}/account/logout">logout</a>
	</c:when>
	<c:otherwise>
	   You could try to <a href="${contextPath}/account/login">login</a>
	</c:otherwise>
</c:choose>

<%@ include file="../footer.jsp" %>