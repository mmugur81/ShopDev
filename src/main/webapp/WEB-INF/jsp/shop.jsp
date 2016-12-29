<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<s:message code="main.title" var="pageTitle" />
<%@ include file="header.jsp" %>

<s:message code="admin.order.filter.filter" var="lblFilter"/>
<s:message code="admin.product.category" var="lblCategory"/>

<div class="container" align="center">
    <h3>Welcome to the Shop</h3>

    <form id="filterFrm" class="form-filter" style="text-align: left;">
        <div class="inline-container">
            <label for="category">${lblCategory}</label>
            <select id="category" name="category" class="form-control">

            </select>
        </div>

        <div class="inline-container">
            <button class="btn btn-primary form-control" type="button">
                ${lblFilter}
            </button>
        </div>

    </form>


</div>

<%@ include file="footer.jsp" %>
<script src="${contextPath}/resources/js/shop.js"></script>

<script>

    jQuery(document).ready( function(){
        shopFilter.updateCategoriesList('category');
    } );

</script>