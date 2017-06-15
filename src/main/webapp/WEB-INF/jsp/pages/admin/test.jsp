<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<t:userpage userName="${pageContext.request.userPrincipal.name}">
    <p>
        First Name: ${pageContext.request.userPrincipal.name} <br/>
        Last Name: lolz <br/>
        Phone: 0474/284.636 <br/>
    </p>
</t:userpage>