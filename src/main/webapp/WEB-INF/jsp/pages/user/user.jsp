<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<t:userpage userName="${pageContext.request.userPrincipal.name}">
    <jsp:attribute name="headerlinks">
        <li class="active"><a href="#">Home</a></li>
        <spring:url value="setReservation" context="user" var="setReservationUrl" />
        <li><a href="${setReservationUrl}">Set Reservation</a></li>
    </jsp:attribute>
</t:userpage>