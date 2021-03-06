<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:set var="pageTitle" value="Admin index"/>
<%@ include file="../header.jsp" %>

<div class="col-lg-4 center-block">
	<div class="panel panel-primary">
	    <div class="panel-heading">
	        <h3 class="panel-title">Admin</h3>
	    </div>
	    <div class="panel-body">
	    <ul class="line-2">
	        <li><a href="${contextPath}/admin/category/">Categories</a></li>
	        <li><a href="${contextPath}/admin/product/">Products</a></li>
			<sec:authorize access="hasAuthority('ORDER_ADMIN')">
				<li><a href="${contextPath}/admin/order/">Orders</a></li>
			</sec:authorize>
		</ul>
	    </div>
	</div>
</div>

<%@ include file="../footer.jsp" %>