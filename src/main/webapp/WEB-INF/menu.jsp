<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>


<html>
<head>

    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="messages" var="lang"/>
    <fmt:requestEncoding value="UTF-8" />

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <%--<link href="${pageContext.request.contextPath}/resources/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">--%>

    <!-- Custom CSS -->
    <%--<link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.css" rel="stylesheet">--%>

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/resources/font-awesome-4.1.0/css/font-awesome.min.css"
          rel="stylesheet" type="text/css">
</head>

<body>
<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand">TTS v1.0</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li <c:if test="${requestScope.page == 'profile'}"> class="active"</c:if>
                ><a href="${pageContext.request.contextPath}/servlet/profile"><fmt:message key="profile" bundle="${lang}"/></a></li>
                <li <c:if test="${requestScope.page == 'dashboards'}"> class="active"</c:if>><a href="#"><fmt:message key="statistics" bundle="${lang}"/></a></li>
                <li <c:if test="${requestScope.page == 'new_task'}"> class="active"</c:if>><a href="${pageContext.request.contextPath}/servlet/new_task"><fmt:message key="create_task" bundle="${lang}"/></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/servlet/logout"><fmt:message key="logout" bundle="${lang}"/></a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>


<div class="container">

</div> <!-- /container -->
</body>
</html>