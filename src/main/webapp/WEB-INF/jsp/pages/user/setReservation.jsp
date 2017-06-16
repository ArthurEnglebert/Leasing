<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<t:userpage userName="${pageContext.request.userPrincipal.name}">
    <jsp:attribute name="headerlinks">
        <spring:url value="" context="user" var="homeUrl" />
        <li><a href="${homeUrl}">Home</a></li>
        <li class="active"><a href="#">Set Reservation</a></li>
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

        <spring:url value="setReservation" context="user" var="setReservationUrl" />
        <form action="${setReservationUrl}" method="POST" id="setReservation" autocomplete="off">
            <table>
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
                    <td><label for="start">Start of Reservation:</label></td>
                    <td><input type="date" name="start" id="start" min="${startTime}"/></td>
                </tr>
                <tr>
                    <td><label for="end">End of Reservation:</label></td>
                    <td><input type="date" name="end" id="end" min="${endTime}"/></td>
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
