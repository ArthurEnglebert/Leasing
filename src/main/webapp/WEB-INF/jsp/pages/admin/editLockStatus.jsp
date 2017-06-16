<%@ page import="com.citobi.leasing.domain.LockStatus" %>
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
        <spring:url value="addCar" context="admin" var="addCarUrl" />
        <li><a href="${addCarUrl}">Add Car</a></li>
        <li class="active"><a href="#">Edit LockStatus</a></li>
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
        <spring:url value="editLockStatus" context="admin" var="editLockStatusUrl" />
        <form action="${editLockStatus}" method="POST" id="editLockStatus" autocomplete="off">
            <table>
                <tr>
                    <td><label for="lockStatus">LockStatus:</label></td>
                    <td><input type="radio" name="lockStatus" id="lockStatus" value="<spring:eval expression="T(com.citobi.leasing.domain.LockStatus).MAINTENANCE.toString()"/>"> Maintenance</td>
                    <td><input type="radio" name="lockStatus" id="lockStatus" value="<spring:eval expression="T(com.citobi.leasing.domain.LockStatus).REPAIR.toString()"/>"> Repair</td>
                    <td><input type="radio" name="lockStatus" id="lockStatus" value="<spring:eval expression="T(com.citobi.leasing.domain.LockStatus).NONE.toString()"/>" checked> None</td>
                </tr>
                <tr>
                    <td><label for="carId">Car:</label></td>
                    <td>
                        <select name="carId" id="carId">
                            <c:forEach items="${cars}" var="car">
                                <option value="${car.id}">${car.id}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
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
