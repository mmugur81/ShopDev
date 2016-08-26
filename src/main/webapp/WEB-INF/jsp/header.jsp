<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<!-- Static navbar -->
<nav class="navbar navbar-inverse navbar-custom">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">${app.name}</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li><a href="${contextPath}/">Home</a></li>
        <li><a href="${contextPath}/about">About</a></li>
        <li><a href="${contextPath}/contact">Contact</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <c:choose>
		  <c:when test="${pageContext.request.userPrincipal.authenticated}">
		    <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${pageContext.request.userPrincipal.name}</a></li>
            <li><a href="${contextPath}/account/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
		  </c:when>
		  <c:otherwise>
	        <li><a href="${contextPath}/account/register"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
	        <li><a href="${contextPath}/account/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
		  </c:otherwise>
		</c:choose>
      </ul>
    </div><!--/.nav-collapse -->
  </div>
</nav>
