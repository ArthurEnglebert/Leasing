<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<t:userpage userName="${pageContext.request.userPrincipal.name}">
    <jsp:attribute name="headerlinks">
        <spring:url value="" context="admin" var="adminHomeUrl" />
        <li><a href="${adminHomeUrl}">Home</a></li>
        <spring:url value="addUser" context="admin" var="addUserUrl" />
        <li><a href="${addUserUrl}">Add User</a></li>
        <li class="active"><a href="#">Add Car</a></li>
        <spring:url value="editLockStatus" context="admin" var="editLockStatusUrl" />
        <li><a href="${editLockStatusUrl}">Edit LockStatus</a></li>
    </jsp:attribute>

    <jsp:body>
        <div id="create-user">
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>
        </div>
        <spring:url value="/addCar" context="/admin" var="addCarUrl" />
        <form action="${addCarUrl}" method="POST" id="addCar" autocomplete="off">
            <table>
                <tr>
                    <td colspan='2'>
                        <input name="submit" type="submit" value="submit" />
                    </td>
                </tr>
            </table>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </jsp:body>
</t:userpage>