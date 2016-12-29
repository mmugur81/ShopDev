<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<s:message code="main.title" var="pageTitle" />
<%@ include file="header.jsp" %>

<s:message code="shop.filter.search" var="lblSearch"/>
<s:message code="shop.filter.category" var="lblCategory"/>

<div class="container" align="center">
    <h3><s:message code="shop.title"/></h3>

    <form id="filterFrm" class="container form-filter" style="text-align: left;">
        <div class="inline-container">
            <label for="category">${lblCategory}</label>
            <select id="category" name="category" class="form-control">

            </select>
        </div>

        <div class="inline-container">
            <button class="btn btn-primary form-control" type="button" onclick="doShopSearch()">
                ${lblSearch}
            </button>
        </div>
    </form>

    <div id="prd" class="product-view col-md-3" style="display: none">
        <strong id="prd-title">Titlu</strong>
        <img id="prd-img" src="" width="100%" height="80%">
        <div>
            <label>Price</label>
            <span id="prd-price" style="float: right">0.00 EUR</span>
        </div>
    </div>

    <div id="prdContainer" class="container form-filter"></div>

</div>

<%@ include file="footer.jsp" %>
<script src="${contextPath}/resources/js/shop.js"></script>

<script>

    jQuery(document).ready( function(){
        shopFilter.updateCategoriesList('category');
    } );

    function doShopSearch() {
        var categoryId = jQuery('#category').val();
        var searchParams = {};

        if (categoryId > 0) {
            searchParams.categoryId = categoryId;
        }

        shop.searchProducts(jQuery('#prdContainer'), jQuery('#prd'), searchParams);
    }

</script>