<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<t:userpage userName="${pageContext.request.userPrincipal.name}">
    <jsp:attribute name="headerlinks">
        <li class="active"><a href="#">Home</a></li>
        <spring:url value="createUser" context="admin" var="createUserUrl" />
        <li><a href="${createUserUrl}">Create User</a></li>
        <spring:url value="addCar" context="admin" var="addCarUrl" />
        <li><a href="${addCarUrl}">Add Car</a></li>
        <spring:url value="editLockStatus" context="admin" var="editLockStatusUrl" />
        <li><a href="${editLockStatusUrl}">Edit LockStatus</a></li>
    </jsp:attribute>

    <jsp:body>
    </jsp:body>
</t:userpage>