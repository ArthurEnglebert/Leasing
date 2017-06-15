<%@tag description="Overall Navigation template" pageEncoding="UTF-8"%>

<%@attribute name="header" fragment="true" %>
<%@attribute name="headerlinks" fragment="true" %>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <jsp:invoke fragment="header"/>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <jsp:invoke fragment="headerlinks" />
            </ul>
        </div>
    </div>
</nav>