<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<t:userpage>
    <jsp:body>
        <spring:url value="/addCar" context="/admin" var="addCarUrl" />
        <form action="${addCarUrl}" method="POST" id="addCar" autocomplete="off">
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="alert alert-info" role="alert">${msg}</div>
            </c:if>

            <div class="form-group row">
                <label for="plate" class="col-sm-2 col-form-label">Plate</label>
                <div class="col-sm-10">
                    <input class="form-control" type="text" name="plate" id="plate" required="" placeholder="1-AAA-111" />
                </div>
            </div>

            <div class="form-group row">
                <label for="model_id" class="col-sm-2 col-form-label">Model</label>
                <div class="col-sm-10">
                    <select name="model_id" id="model_id" class="custom-select" required="">
                        <c:forEach items="${models}" var="model">
                            <option value="${model.id}">${model.brand.name} - ${model.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group row">
                <div class="offset-sm-2 col-sm-10">
                    <button type="submit" class="btn btn-primary">Add new car</button>
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </jsp:body>
</t:userpage>