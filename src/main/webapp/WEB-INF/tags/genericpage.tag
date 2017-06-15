<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<%@attribute name="specificCss" fragment="true" %>
<%@attribute name="specificJs" fragment="true" %>

<%@attribute name="headerlinks" fragment="true" %>
<html>
<head>
    <title>${title}</title>

    <jsp:include page="/WEB-INF/jsp/css.jsp" />
    <jsp:invoke fragment="specificCss" />

    <jsp:include page="/WEB-INF/jsp/script.jsp" />
    <jsp:invoke fragment="specificJs" />
</head>
<body>
    <div id="body">
        <t:nav>
            <jsp:attribute name="header">
                <jsp:invoke fragment="header"/>
            </jsp:attribute>
            <jsp:attribute name="headerlinks">
                <jsp:invoke fragment="headerlinks"/>
            </jsp:attribute>
        </t:nav>

        <div class="container">

            <div class="starter-template">
                <jsp:doBody/>
            </div>

        </div>
    </div>
    <div id="pagefooter">
        <jsp:invoke fragment="footer"/>
    </div>
</body>
</html>
