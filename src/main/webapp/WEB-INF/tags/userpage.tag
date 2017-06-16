<%@tag description="User Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="userName" required="true"%>
<%@attribute name="headerlinks" fragment="true" %>

<t:genericpage>

    <jsp:attribute name="specificCss">
        <c:url value="/css/userpage.css" var="specificCss" />
        <link rel="stylesheet" type="text/css" href="${specificCss}" />
    </jsp:attribute>

    <jsp:attribute name="specificJs">
        <c:url value="/js/userpage.js" var="specificJs" />
        <script type="text/javascript" src="${specificJs}"></script>
    </jsp:attribute>

    <jsp:attribute name="header">
      <h2>Welcome ${userName}</h2>
    </jsp:attribute>

    <jsp:attribute name="headerlinks">
        <jsp:invoke fragment="headerlinks" />
        <li><a href="javascript:logoutFormSubmit()"> Logout</a></li>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <p id="copyright">Copyright 1927, Future Bits When There Be Bits Inc.</p>
    </jsp:attribute>

    <jsp:body>
        <c:url value="/logout" var="logoutUrl" />
        <form action="${logoutUrl}" method="post" id="logoutForm">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
        <h2>Message: ${message}</h2>
        <jsp:doBody/>
    </jsp:body>
</t:genericpage>