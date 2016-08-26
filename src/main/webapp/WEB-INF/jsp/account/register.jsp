<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<s:message code="account.register.title" var="pageTitle" />
<%@ include file="../header.jsp" %>

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

<%@ include file="../footer.jsp" %>