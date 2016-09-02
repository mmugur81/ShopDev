<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<s:message code="admin.product.list.title" var="pageTitle" />
<%@ include file="../../header.jsp" %>

<s:message code="admin.product.name" var="lblName"/>
<s:message code="admin.product.category" var="lblCategory"/>
<s:message code="admin.product.description" var="lblDescription"/>
<s:message code="admin.product.currency" var="lblCurrency"/>
<s:message code="admin.product.price" var="lblPrice"/>
<s:message code="admin.product.list.add_new" var="lblAddNew"/>
<s:message code="admin.product.list.confirm_delete" var="msgConfirmDelete"/>

<div class="container">
  <h2>${pageTitle}</h2>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>${lblName}</th>
        <th>${lblCategory}</th>
        <th>${lblPrice}</th>
        <th>${lblCurrency}</th>
        <th>${lblDescription}</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="product">
      <tr>
        <td>
          <a href="${contextPath}/admin/product/edit/${product.id}" class="link-edit">
            <span class="glyphicon glyphicon-edit"></span> ${product.name}
          </a>
          <a href="#" onclick="promptDelLink(${product.id})" class="link-del" style="float: right">
            <span class="glyphicon glyphicon-remove"></span>
          </a>
        </td>
        <td>${product.category.name}</td>
        <td>${product.price.price}</td>
        <td>${product.price.currency}</td>
        <td>${product.description}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  
  <div>
    <a href="${contextPath}/admin/product/edit/" class="link-add">
      <span class="glyphicon glyphicon-plus"></span> ${lblAddNew}
  </div>
</div>

<form id="frm-del" method="POST">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<%@ include file="../../footer.jsp" %>

<script lang="javascript">
function promptDelLink(catId) {
    var baseUrl = "${contextPath}/admin/product/del/";
    var msg = "${msgConfirmDelete}";
    if (confirm(msg) == true) {
        $("#frm-del").attr("action", baseUrl + catId);
        $('#frm-del').submit();
    }
}
</script>