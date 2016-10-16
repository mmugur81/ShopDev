<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<s:message code="admin.order.list.title" var="pageTitle" />
<%@ include file="../../header.jsp" %>

<s:message code="None" var="lblNone"/>
<s:message code="Reset" var="lblReset"/>
<s:message code="admin.order.filter.filter" var="lblFilter"/>
<s:message code="admin.order.order_id" var="lblOrderId"/>
<s:message code="admin.order.status" var="lblStatus"/>
<s:message code="admin.order.user" var="lblUser"/>
<s:message code="admin.order.date" var="lblDate"/>
<s:message code="admin.order.created_between" var="lblCreatedBetween"/>
<s:message code="admin.order.total" var="lblTotal"/>
<s:message code="admin.order.items" var="lblItems"/>
<s:message code="admin.order.details" var="lblDetails"/>

<fmt:formatDate var="createdStart" value="${orderSearchForm.createdBetween.start}" pattern="MM/dd/yyyy"/>
<fmt:formatDate var="createdEnd" value="${orderSearchForm.createdBetween.end}" pattern="MM/dd/yyyy"/>

<div class="container">
    <h2>${pageTitle}</h2>

    <f:form id="filterFrm" method="GET" modelAttribute="orderSearchForm" class="form-filter">

        <div class="inline-container">
            <f:label path="user">${lblUser}</f:label>
            <s:bind path="user">
                <f:select path="user" rows="5" class="form-control">
                    <f:option value="" label="--- ${lblNone} ---" />
                    <f:options items="${users}" itemValue="id" itemLabel="fullNameAndEmail" />
                </f:select>
            </s:bind>
        </div>

        <div class="inline-container">
            <f:label path="status">${lblStatus}</f:label>
            <s:bind path="status">
                <f:select path="status" rows="5" class="form-control">
                    <f:option value="" label="--- ${lblNone} ---" />
                    <f:options items="${statuses}" />
                </f:select>
            </s:bind>
        </div>

        <div class="inline-container">
            <f:label path="createdBetween.start">${lblCreatedBetween}</f:label>
            <div>
                <s:bind path="createdBetween.start">
                    <f:input type="text" path="createdBetween.start" value="${createdStart}"
                              class="form-control inline datepicker"/>
                </s:bind>
                <span>and</span>
                <s:bind path="createdBetween.end">
                    <f:input type="text" path="createdBetween.end" value="${createdEnd}"
                             class="form-control inline datepicker" />
                </s:bind>
            </div>
        </div>

        <div class="inline-container">
            <button class="btn btn-primary form-control" type="submit">
                    ${lblFilter}
            </button>
        </div>

    </f:form>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>${lblOrderId}</th>
            <th>${lblDate}</th>
            <th>${lblStatus}</th>
            <th>${lblUser}</th>
            <th>${lblTotal}</th>
            <th>${lblItems}</th>
            <th>${lblDetails}</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>${order.id}</td>
                <td><fmt:formatDate value="${order.created}" pattern="MM/dd/yyyy HH:mm" /></td>
                <td>${order.status}</td>
                <td>${order.user.fullNameAndEmail}</td>
                <td>${order.total.price} ${order.total.currency}</td>
                <td>${order.orderItems.size()}</td>
                <td><a href="${contextPath}/admin/order/view/${order.id}" class="link-edit">
                    <span class="glyphicon glyphicon-edit"></span> ${lblDetails}</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<%@ include file="../../footer.jsp" %>
