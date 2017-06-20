<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
    <jsp:body>
        <form class="form-signin" action="<c:url value='/login' />" method='POST' autocomplete="off">
            <h2 class="form-signin-heading">Please sign in</h2>

            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="alert alert-info" role="alert">${msg}</div>
            </c:if>

            <label for="username" class="sr-only">Username</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="Username" required="" autofocus="">

            <label for="password" class="sr-only">Password</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password" required="">

            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </form>
    </jsp:body>
</t:genericpage>
