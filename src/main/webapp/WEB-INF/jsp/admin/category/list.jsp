<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<s:message code="admin.category.list.title" var="pageTitle" />
<%@ include file="../../header.jsp" %>

<s:message code="admin.category.name" var="lblName"/>
<s:message code="admin.category.description" var="lblDescription"/>
<s:message code="admin.category.parent_category" var="lblParentCategory"/>
<s:message code="admin.category.list.add_new" var="lblAddNew"/>
<s:message code="admin.category.list.confirm_delete" var="msgConfirmDelete"/>

<div class="container">
  <h2>${pageTitle}</h2>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>${lblName}</th>
        <th>${lblDescription}</th>
        <th>${lblParentCategory}</th>
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
      <span class="glyphicon glyphicon-plus"></span> ${lblAddNew}
  </div>
</div>

<form id="frm-del" method="POST">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<%@ include file="../../footer.jsp" %>

<script lang="javascript">
function promptDelLink(catId) {
	var baseUrl = "${contextPath}/admin/category/del/";
	var msg = "${msgConfirmDelete}";
    if (confirm(msg) == true) {
    	$("#frm-del").attr("action", baseUrl + catId);
    	$('#frm-del').submit();
    }
}
</script>