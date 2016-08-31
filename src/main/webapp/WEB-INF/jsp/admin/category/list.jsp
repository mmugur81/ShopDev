<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:set var="pageTitle" value="Admin index"/>
<%@ include file="../../header.jsp" %>

<div class="container">
  <h2>Categories</h2>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Parent category</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${categories}" var="category">
      <tr>
        <td>
          <a href="${contextPath}/admin/category/edit/${category.id}" class="link-edit">
            <span class="glyphicon glyphicon-edit"></span> ${category.name}
          </a>
          <a href="#" onclick="promptDelLink(${category.id})" class="link-del" style="float: right">
            <span class="glyphicon glyphicon-remove"></span>
          </a>
        </td>
        <td>${category.description}</td>
        <td>${category.getParentCategoryName()}</td>
      </tr>
	</c:forEach>
    </tbody>
  </table>
  
  <div>
    <a href="${contextPath}/admin/category/edit/" class="link-add">
      <span class="glyphicon glyphicon-plus"></span> Add new category
  </div>
</div>

<form id="frm-del" method="POST">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<%@ include file="../../footer.jsp" %>

<script lang="javascript">
function promptDelLink(catId) {
	var baseUrl = "${contextPath}/admin/category/del/";
	var msg = "Are you sure you want to delete?";
    if (confirm(msg) == true) {
    	$("#frm-del").attr("action", baseUrl + catId);
    	$('#frm-del').submit();
    }
}
</script>