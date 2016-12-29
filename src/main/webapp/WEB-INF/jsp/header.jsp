<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<s:message code="main.home" var="lblHome" />
<s:message code="main.shop" var="lblShop" />
<s:message code="main.about" var="lblAbout" />
<s:message code="main.contact" var="lblContact" />
<s:message code="main.admin" var="lblAdmin" />
<s:message code="main.sign_up" var="lblSignUp" />
<s:message code="main.login" var="lblLogin" />
<s:message code="main.logout" var="lblLogout" />
<s:message code="main.toggle_navigation" var="lblToggleNavigation" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${pageTitle}</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.custom.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<!-- Static navbar -->
<nav class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">${lblToggleNavigation}</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">${app.name}</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li><a href="${contextPath}/">${lblHome}</a></li>
        <li><a href="${contextPath}/shop">${lblShop}</a></li>
        <li><a href="${contextPath}/about">${lblAbout}</a></li>
        <li><a href="${contextPath}/contact">${lblContact}</a></li>
        <sec:authorize access="hasAuthority('ADMIN')">
            <li><a href="${contextPath}/admin/">${lblAdmin}</a></li>
        </sec:authorize>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <c:choose>
		  <c:when test="${pageContext.request.userPrincipal.authenticated}">
		    <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${pageContext.request.userPrincipal.name}</a></li>
            <li><a href="${contextPath}/account/logout"><span class="glyphicon glyphicon-log-out"></span> ${lblLogout}</a></li>
		  </c:when>
		  <c:otherwise>
	        <li><a href="${contextPath}/account/register"><span class="glyphicon glyphicon-user"></span> ${lblSignUp}</a></li>
	        <li><a href="${contextPath}/account/login"><span class="glyphicon glyphicon-log-in"></span> ${lblLogin}</a></li>
		  </c:otherwise>
		</c:choose>
      </ul>
    </div><!--/.nav-collapse -->
  </div>
</nav>
