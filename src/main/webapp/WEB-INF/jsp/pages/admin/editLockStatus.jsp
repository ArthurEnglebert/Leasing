<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<t:userpage>
    <jsp:body>
        <spring:url value="editLockStatus" context="admin" var="editLockStatusUrl" />
        <form action="${editLockStatus}" method="POST" id="editLockStatus" autocomplete="off">
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="alert alert-info" role="alert">${msg}</div>
            </c:if>

            <div class="form-group row">
                <label for="carId" class="col-sm-2 col-form-label">Car</label>
                <div class="col-sm-10">
                    <select name="carId" id="carId" class="custom-select" required="">
                        <c:forEach items="${cars}" var="car">
                            <option value="${car.id}">${car.numberPlate}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group row">
                <label for="lockStatus" class="col-sm-2 col-form-label">Lock</label>
                <div class="col-sm-10">
                    <div class="form-check form-check-inline">
                        <label class="form-check-label">
                            <input class="form-check-input" type="radio" name="lockStatus" id="lockStatus" value="<spring:eval expression="T(com.citobi.leasing.domain.LockStatus).MAINTENANCE.toString()"/>">
                            Maintenance
                        </label>
                    </div>
                    <div class="form-check form-check-inline">
                        <label class="form-check-label">
                            <input class="form-check-input" type="radio" name="lockStatus" id="lockStatus" value="<spring:eval expression="T(com.citobi.leasing.domain.LockStatus).REPAIR.toString()"/>">
                            Repair
                        </label>
                    </div>
                    <div class="form-check form-check-inline">
                        <label class="form-check-label">
                            <input class="form-check-input" type="radio" name="lockStatus" id="lockStatus" value="<spring:eval expression="T(com.citobi.leasing.domain.LockStatus).NONE.toString()"/>" checked>
                            None
                        </label>
                    </div>
                </div>
            </div>

            <div class="form-group row">
                <div class="offset-sm-2 col-sm-10">
                    <button type="submit" class="btn btn-primary">Change lock status</button>
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </jsp:body>
</t:userpage>