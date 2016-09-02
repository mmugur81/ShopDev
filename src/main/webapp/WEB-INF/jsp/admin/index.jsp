<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:set var="pageTitle" value="Admin index"/>
<%@ include file="../header.jsp" %>

<div class="col-lg-4 center-block">
	<div class="panel panel-primary">
	    <div class="panel-heading">
	        <h3 class="panel-title">Admin</h3>
	    </div>
	    <div class="panel-body">
	    <ul>
	        <li><a href="${contextPath}/admin/category/">Categories</a></li>
	        <li><a href="${contextPath}/admin/product/">Products</a></li>
	    </ul>
	    </div>
	</div>
</div>

<%@ include file="../footer.jsp" %>