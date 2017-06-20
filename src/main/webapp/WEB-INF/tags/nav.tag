<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="authz"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@tag description="Overall Navigation template" pageEncoding="UTF-8"%>

<authz:authorize access="isAuthenticated()">
    <nav class="navbar navbar-toggleable-md navbar-light bg-faded">
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <h1 class="navbar-brand mb-0">Welcome <authz:authentication property="principal.username" /></h1>

        <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
            <ul class="navbar-nav mr-auto mt-2 mt-md-0">
                <authz:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                    <spring:url value="/user/setReservation" var="setReservationUrl" />
                    <c:set var="active" value="${fn:contains(pageContext.request.requestURI, setReservationUrl)}" />
                    <c:if test="${not active}">
                        <spring:url value="/user/" var="userHomeUrl" />
                        <c:set var="active" value="${fn:endsWith(pageContext.request.requestURI, userHomeUrl)}" />
                    </c:if>
                    <li class="nav-item ${active ? "active" : ""}"><a class="nav-link" href="${active ? "#" : setReservationUrl}">Set Reservation</a></li>
                </authz:authorize>

                <authz:authorize access="hasRole('ADMIN')">
                    <spring:url value="/admin/createUser" var="addUserUrl" />
                    <c:set var="active" value="${fn:contains(pageContext.request.requestURI, addUserUrl)}" />
                    <c:if test="${not active}">
                        <spring:url value="/admin/" var="adminHomeUrl" />
                        <c:set var="active" value="${fn:endsWith(pageContext.request.requestURI, adminHomeUrl)}" />
                    </c:if>
                    <li class="nav-item ${active ? "active" : ""}"><a class="nav-link" href="${active ? "#" : addUserUrl}">Add User</a></li>

                    <spring:url value="/admin/addCar" var="addCarUrl" />
                    <c:set var="active" value="${fn:contains(pageContext.request.requestURI, addCarUrl)}" />
                    <li class="nav-item ${active ? "active" : ""}"><a class="nav-link" href="${active ? "#" : addCarUrl}">Add Car</a></li>

                    <spring:url value="/admin/editLockStatus" var="editLockStatusUrl" />
                    <c:set var="active" value="${fn:contains(pageContext.request.requestURI, editLockStatusUrl)}" />
                    <li class="nav-item ${active ? "active" : ""}"><a class="nav-link" href="${active ? "#" : editLockStatusUrl}">Edit LockStatus</a></li>
                </authz:authorize>
            </ul>

            <c:url value="/logout" var="logoutUrl" />
            <form class="form-inline my-2 my-lg-0" action="${logoutUrl}" method="post" id="logoutForm">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <button class="btn btn-outline-danger my-2 my-sm-0" type="submit">Logout</button>
            </form>
        </div>
    </nav>
</authz:authorize>