<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<t:userpage>
    <jsp:body>
        <spring:url value="/createUser" context="/admin" var="createUserUrl" />
        <form action="${createUserUrl}" method="POST" id="createUserUrl" autocomplete="off">
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="alert alert-info" role="alert">${msg}</div>
            </c:if>

            <div class="form-group row">
                <label for="username" class="col-sm-2 col-form-label">Username</label>
                <div class="col-sm-10">
                    <input class="form-control" type="text" name="username" id="username" placeholder="JohnSmith" required="" autofocus="" />
                </div>
            </div>
            <div class="form-group row">
                <label for="password" class="col-sm-2 col-form-label">Password</label>
                <div class="col-sm-10">
                    <input class="form-control" type="password" name="password" id="password" placeholder="************" required="" />
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-10 offset-sm-2">
                    <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox" name="isAdmin" id="isAdmin"> can access administration panel
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="offset-sm-2 col-sm-10">
                    <button type="submit" class="btn btn-primary">Create User</button>
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </jsp:body>
</t:userpage>