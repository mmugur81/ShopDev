<%--TODO translate string variables--%>
<%@ page import="com.mmugur81.controller.AdminOrderController" %>
<%@ page import="com.mmugur81.model.Order" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<s:message code="admin.order.view.title" var="pageTitle" />
<%@ include file="../../header.jsp" %>

<s:message code="Yes" var="lblYes" />
<s:message code="No" var="lblNo" />
<s:message code="admin.order.details" var="lblDetails" />
<s:message code="admin.order.items" var="lblItems" />
<s:message code="admin.product.name" var="lblName"/>
<s:message code="admin.product.category" var="lblCategory"/>
<s:message code="admin.product.price" var="lblPrice"/>

<c:set var="colLeft" scope="page" value="col-lg-4"/>
<c:set var="colRight" scope="page" value="col-lg-8 col-value"/>

<c:set var="StatusPending" value="<%=Order.Status.Pending%>"/>
<c:set var="StatusConfirmed" value="<%=Order.Status.Confirmed%>"/>

<fmt:formatDate var="payDate" value="${order.payDate}" pattern="MM/dd/yyyy"/>

<div class="container">
    <h2>${pageTitle}</h2>

    <div class="row">
        <div class="col-lg-6 form-view-wrapper">
            <div class="form-view col-lg-12">
                <div class="col-lg-12"><div class="label label-primary form-legend">
                    ${lblDetails}
                </div></div>

                <div class="${colLeft}">Order id</div>
                <div class="${colRight}">${order.id}</div>

                <div class="${colLeft}">User</div>
                <div class="${colRight}">${order.user.fullNameAndEmail}</div>

                <div class="${colLeft}">Date</div>
                <div class="${colRight}"><fmt:formatDate value="${order.created}" pattern="MM/dd/yyyy"/></div>

                <div class="${colLeft}">Status</div>
                <div class="${colRight}">${order.status}</div>

                <div class="${colLeft}">Payed (date)</div>
                <div class="${colRight}">${order.payed ? lblYes : lblNo} (${payDate})</div>

                <div class="${colLeft}">Total</div>
                <div class="${colRight}"><strong>
                    <fmt:formatNumber type="number" value="${order.total.price}" />
                    ${order.total.currency}
                </strong></div>

                <div class="col-lg-12">Notes</div>
                <div class="col-lg-12 col-value"><em>${order.notes}</em></div>
            </div>
        </div>

        <div class="col-lg-6 form-view-wrapper">
            <div class="form-view col-lg-12">
                <div class="col-lg-12"><div class="label label-primary form-legend">
                    ${lblItems}
                </div></div>

                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th> # </th>
                        <th>${lblName}</th>
                        <th>${lblCategory}</th>
                        <th style="text-align: right">${lblPrice}</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${order.orderItems}" var="item">
                        <tr>
                            <td>${item.itemNumber}</td>
                            <td>${item.product.name}</td>
                            <td>${item.product.category.name}</td>
                            <td align="right"><fmt:formatNumber type="number" value="${item.price.price}" /></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-6 form-view-wrapper">
            <div class="form-view col-lg-12">
                <div class="col-lg-12"><div class="label label-primary form-legend">
                    ${"Actions"}
                </div></div>

                <f:form id="orderFrm" method="POST" class="">
                    <c:choose>
                        <c:when test="${order.status == StatusPending}">
                            <button class="btn btn-success mr20" value="<%=AdminOrderController.Action.Confirm%>"
                                    name="orderAction" type="submit">
                                    ${"Confirm order!"}
                            </button>
                        </c:when>
                        <c:when test="${order.status == StatusConfirmed}">
                            <button class="btn btn-danger mr20" value="<%=AdminOrderController.Action.Cancel%>"
                                    name="orderAction" type="submit">
                                    ${"Cancel order!"}
                            </button>
                        </c:when>
                    </c:choose>
                    <c:if test="${order.payed == false}">
                        <button class="btn btn-info mr20" value="<%=AdminOrderController.Action.MarkPayed%>"
                                name="orderAction" type="submit">
                                ${"Mark as payed!"}
                        </button>
                    </c:if>

                </f:form>
        </div>
    </div>

</div>

<%@ include file="../../footer.jsp" %>
