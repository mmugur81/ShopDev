<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ include file="../../header.jsp" %>

<s:message code="admin.category.name" var="lblName"/>
<s:message code="admin.category.description" var="lblDescription"/>
<s:message code="admin.category.parent_category" var="lblParentCategory"/>
<s:message code="None" var="lblNone"/>

<div class="container">

    <f:form method="POST" modelAttribute="categoryForm" class="form-signin">
        <h2 class="form-signin-heading">${pageTitle}</h2>
        <s:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <f:input type="text" path="name" class="form-control" autofocus="true"
                    placeholder="${lblName}"/>
                <f:errors path="name" />
            </div>
        </s:bind>

        <s:bind path="description">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <f:textarea path="description" rows="5" class="form-control" 
                    placeholder="${lblDescription}" />
                <f:errors path="description" />
            </div>
        </s:bind>

        <div>${lblParentCategory}</div>
        <s:bind path="parentCategory">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <f:select path="parentCategory" rows="5" class="form-control">
                    <f:option value="" label="--- ${lblNone} ---" />
                    <f:options items="${categories}" itemValue="id" itemLabel="name" />
                </f:select>
                <f:errors path="parentCategory" />
            </div>
        </s:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">
            ${lblSubmit}
        </button>
    </f:form>

</div>
<!-- /container -->

<%@ include file="../../footer.jsp" %>