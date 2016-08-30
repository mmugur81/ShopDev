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
        <td><a href="${contextPath}/admin/category/${category.id}"><span class="glyphicon glyphicon-edit"></span> ${category.name}</a></td>
        <td>${category.description}</td>
        <td>${category.getParentCategoryName()}</td>
      </tr>
	</c:forEach>
    </tbody>
  </table>
</div>

<%@ include file="../../footer.jsp" %>