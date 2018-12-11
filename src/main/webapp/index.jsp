<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="messages" var="lang"/>
    <fmt:requestEncoding value="UTF-8"/>

    <title>TTS - Login</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/resources/font-awesome-4.1.0/css/font-awesome.min.css"
          rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="div_lang" style=" width: 30%">
                    <a href="?lang=en"><img src="${pageContext.request.contextPath}/resources/flags/blank.gif" class="flag flag-england" alt="en"></a>
                    <a href="?lang=Ua_ua"><img src="${pageContext.request.contextPath}/resources/flags/blank.gif" class="flag flag-england" alt="ua"></a>
                </div>
                <div class="panel-heading">
                    <h3 class="panel-title"><fmt:message key="please_log_in" bundle="${lang}"/></h3>
                </div>
                <div class="panel-body">
                    <form role="form" method="POST" action="${pageContext.request.contextPath}/servlet/login">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="<fmt:message key="login" bundle="${lang}"/>"
                                       name="name" type="text" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="<fmt:message key="password" bundle="${lang}"/>"
                                       name="password" type="password"
                                       value="">
                            </div>
                            <br/>
                            <!-- Change this to a button or input when using this as a form -->
                            <input type="submit" value="<fmt:message key="login_button" bundle="${lang}"/>"
                                   class="btn btn-lg btn-success btn-block">
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/plugins/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.js"></script>

</body>

</html>
