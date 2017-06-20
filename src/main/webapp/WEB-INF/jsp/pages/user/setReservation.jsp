<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<t:userpage>
    <jsp:body>
        <table class="table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Brand</th>
                    <th>Model</th>
                    <th>Plate</th>
                    <th>Start</th>
                    <th>End</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${reservationsToCome}" var="reservation" varStatus="i">
                    <tr>
                        <th scope="row">${i.count}</th>
                        <td>${reservation.car.model.brand.name}</td>
                        <td>${reservation.car.model.name}</td>
                        <td>${reservation.car.numberPlate}</td>
                        <td><fmt:formatDate value="${reservation.start}" pattern="yyyy-MM-dd" /></td>
                        <td><fmt:formatDate value="${reservation.end}" pattern="yyyy-MM-dd" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <spring:url value="setReservation" context="user" var="setReservationUrl" />
        <form action="${setReservationUrl}" method="POST" id="setReservation" autocomplete="off">
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="alert alert-info" role="alert">${msg}</div>
            </c:if>
            <div class="form-group row">
                <label for="modelId" class="col-sm-2 col-form-label">Model</label>
                <div class="col-sm-10">
                    <select name="modelId" id="modelId" class="custom-select">
                        <c:forEach items="${modelsAvailable}" var="model">
                            <option value="${model.id}">${model.brand.name} - ${model.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label for="start" class="col-sm-2 col-form-label">Start of Reservation</label>
                <div class="col-sm-10">
                    <input class="form-control" type="date" name="start" id="start" min="${startTime}" required="" />
                </div>
            </div>
            <div class="form-group row">
                <label for="end" class="col-sm-2 col-form-label">End of Reservation</label>
                <div class="col-sm-10">
                    <input class="form-control" type="date" name="end" id="end" min="${endTime}" required="" />
                </div>
            </div>
            <div class="form-group row">
                <div class="offset-sm-2 col-sm-10">
                    <button type="submit" class="btn btn-primary">Reserve</button>
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </jsp:body>
</t:userpage>
