<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>${title}</title>

        <jsp:include page="/WEB-INF/jsp/css.jsp" />
        <jsp:include page="/WEB-INF/jsp/script.jsp" />
    </head>
    <body>
        <div class="container">
            <t:nav />

            <div class="row justify-content-md-center align-items-center">
                <div class="col col-md-6">
                    <jsp:doBody/>
                </div>
            </div>
        </div>
    </body>
</html>
