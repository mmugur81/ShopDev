<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<s:message code="admin.order.list.title" var="pageTitle" />
<%@ include file="../../header.jsp" %>

<s:message code="None" var="lblNone"/>
<s:message code="admin.order.filter.filter" var="lblFilter"/>

<div class="container">
    <h2>${pageTitle}</h2>

    <f:form method="GET" modelAttribute="orderSearchForm" class="form-filter">

        <div class="inline-container">
            <f:label path="user">User</f:label>
            <s:bind path="user">
                <f:select path="user" rows="5" class="form-control">
                    <f:option value="" label="--- ${lblNone} ---" />
                    <f:options items="${users}" itemValue="id" itemLabel="fullNameAndEmail" />
                </f:select>
            </s:bind>
        </div>

        <div class="inline-container">
            <f:label path="status">Status</f:label>
            <s:bind path="status">
                <f:select path="status" rows="5" class="form-control">
                    <f:option value="" label="--- ${lblNone} ---" />
                    <f:options items="${statuses}" />
                </f:select>
            </s:bind>
        </div>

        <div class="inline-container">
            <f:label path="createdBetween.start">Created between</f:label>
            <div>
                <s:bind path="createdBetween.start">
                    <f:input type="text" path="createdBetween.start" class="form-control inline" />
                </s:bind>
                <span>and</span>
                <s:bind path="createdBetween.end">
                    <f:input type="text" path="createdBetween.end" class="form-control inline" />
                </s:bind>
            </div>
        </div>

        <div class="inline-container">
            <button class="btn btn-lg btn-primary form-control" type="submit">
                    ${lblFilter}
            </button>
        </div>

    </f:form>

</div>

<%@ include file="../../footer.jsp" %>
