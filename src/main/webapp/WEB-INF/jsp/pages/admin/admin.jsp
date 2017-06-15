<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<t:userpage userName="${pageContext.request.userPrincipal.name}">
    <jsp:attribute name="headerlinks">
        <li class="active"><a href="#">Home</a></li>
    </jsp:attribute>

    <jsp:body>
        <spring:url value="/createUser" context="/admin" var="createUserUrl" />
        <form action="${createUserUrl}" method="get" id="createUserUrl">
            <input type="text" name="username" />
            <input type="password" name="password" />
            <input type="checkbox" name="isAdmin" />
            <input type="submit" value="Envoyer" />
        </form>
    </jsp:body>
</t:userpage>