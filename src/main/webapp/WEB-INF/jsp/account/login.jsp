<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<s:message code="account.login.title" var="pageTitle" />
<%@ include file="../header.jsp" %>

<div class="container">

    <form method="POST" action="${contextPath}/account/login" class="form-signin">
        <h2 class="form-heading"><s:message code="account.login.label"/></h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="<s:message code="account.login.username"/>" autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="<s:message code="account.login.password"/>"/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button class="btn btn-lg btn-primary btn-block" type="submit"><s:message code="account.login.label"/></button>
            <h4 class="text-center"><a href="${contextPath}/account/register"><s:message code="account.login.create"/></a></h4>
        </div>

    </form>

</div>

<%@ include file="../footer.jsp" %>