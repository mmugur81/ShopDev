<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ include file="../../header.jsp" %>

<s:message code="admin.product.name" var="lblName"/>
<s:message code="admin.product.category" var="lblCategory"/>
<s:message code="admin.product.description" var="lblDescription"/>
<s:message code="admin.product.currency" var="lblCurrency"/>
<s:message code="admin.product.price" var="lblPrice"/>
<s:message code="None" var="lblNone"/>
<s:message code="Back" var="lblBack"/>

<div class="container">

    <f:form method="POST" modelAttribute="productForm" class="form-signin">
        <h2 class="form-signin-heading">${pageTitle}</h2>
        
        <s:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <f:input type="text" path="name" class="form-control" autofocus="true"
                    placeholder="${lblName}"/>
                <f:errors path="name" />
            </div>
        </s:bind>

        <div>${lblCategory}</div>
        <s:bind path="category">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <f:select path="category" rows="5" class="form-control">
                    <f:option value="" label="--- ${lblNone} ---" />
                    <f:options items="${categories}" itemValue="id" itemLabel="name" />
                </f:select>
                <f:errors path="category" />
            </div>
        </s:bind>
        
        <div>${lblPrice}</div>
        <s:bind path="price.price">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <f:input type="text" path="price.price" class="form-control" autofocus="true" />
                <f:errors path="price.price" />
            </div>
        </s:bind>
        
        <div>${lblCurrency}</div>
        <s:bind path="price.currency">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <f:select path="price.currency" rows="5" class="form-control">
                    <f:options items="${currencies}" />
                </f:select>
                <f:errors path="price.currency" />
            </div>
        </s:bind>
        
        <s:bind path="description">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <f:textarea path="description" rows="5" class="form-control" 
                    placeholder="${lblDescription}" />
                <f:errors path="description" />
            </div>
        </s:bind>

        <button class="btn btn-lg float-left" type="button" onClick="window.location='${contextPath}/admin/product/'">
            ${lblBack}
        </button>
        <button class="btn btn-lg btn-primary float-right" type="submit">
            ${lblSubmit}
        </button>
    </f:form>

</div>
<!-- /container -->

<%@ include file="../../footer.jsp" %>