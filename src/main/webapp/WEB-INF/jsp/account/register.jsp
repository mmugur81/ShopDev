<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title><s:message code="account.register.title"/></title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>

<s:message code="account.register.firstName" var="firstName"/>
<s:message code="account.register.lastName" var="lastName"/>
<s:message code="account.register.email" var="email"/>
<s:message code="account.register.password" var="password"/>
<s:message code="account.register.confirmPassword" var="passwordConfirm"/>

<div class="container">

    <f:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading"><s:message code="account.register.title"/></h2>
        <s:bind path="firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <f:input type="text" path="firstName" class="form-control" autofocus="true"
                    placeholder="${firstName}"/>
                <f:errors path="firstName" />
            </div>
        </s:bind>

        <s:bind path="lastName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <f:input type="text" path="lastName" class="form-control" 
                    placeholder="${lastName}" />
                <f:errors path="lastName" />
            </div>
        </s:bind>

        <s:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <f:input type="text" path="email" class="form-control" 
                    placeholder="${email}" />
                <f:errors path="email" />
            </div>
        </s:bind>

        <s:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <f:input type="password" path="password" class="form-control" 
                    placeholder="${password}" />
                <f:errors path="password" />
            </div>
        </s:bind>

        <s:bind path="passwordConfirm">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <f:input type="password" path="passwordConfirm" class="form-control"
                    placeholder="${passwordConfirm}" />
                <f:errors path="passwordConfirm" />
                <f:errors element="div" cssClass="has-error" />
            </div>
        </s:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">
            <s:message code="account.register.submit"/>
        </button>
    </f:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
