<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
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
            <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/servlet/profile">Profile</a></li>
                <li><a href="${pageContext.request.contextPath}/servlet/new_task">Create task</a></li>
                <li><a href="${pageContext.request.contextPath}/servlet/profile">Contact</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li class="dropdown-header">Nav header</li>
                        <li><a href="#">Separated link</a></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="../navbar/">Default</a></li>
                <li class="active"><a href="./">Static top <span class="sr-only">(current)</span></a></li>
                <li><a href="${pageContext.request.contextPath}/servlet/logout">Logout</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>


<div class="container">

</div> <!-- /container -->
</body>
</html>