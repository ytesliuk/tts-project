<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<html>

<head>
    <title>TTS - New Task</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet"
          type="text/css">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.css" rel="stylesheet" type="text/css">

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
<%@include file="menu.jsp" %>

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-8">
            <div class="panel panel-default">
                <div class="panel-heading">
                    New task
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form role="form" action="${pageContext.request.contextPath}/servlet/create">
                                <div class="form-group">
                                    <label>Category</label>
                                    <select class="form-control" name="category">
                                        <option disabled selected value style="display:none"> -- select category -- </option>
                                        <c:forEach var="category" items="${requestScope.categories}">
                                        <option><c:out value="${category}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class='col-lg-12' >
                                    <hr style="border-color:#005f8d">
                                </div>
                                <div class="form-group">
                                    <label>Priority</label>
                                    <label class="radio-inline">
                                        <input type="radio" name="priority" id="optionsRadiosInline1" value="Low" checked>Low
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="priority" id="optionsRadiosInline2" value="Medium">Medium
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="priority" id="optionsRadiosInline3" value="High">High
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="priority" id="optionsRadiosInline4" value="Critical">Critical
                                    </label>
                                </div>
                                <div class="form-group">
                                    <label>Title</label>
                                    <input class="form-control" name="title">
                                </div>
                                <div class='col-lg-12' >
                                    <hr style="border-color:#005f8d">
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <textarea class="form-control" rows="6" name="description"></textarea>
                                </div>
                                <div class='col-lg-12' >
                                    <hr style="border-color:#005f8d"><br/>
                                </div>
                                <div class="form-group">
                                    <label>Initiator: <c:out value="${sessionScope.user.lastName} ${sessionScope.user.firstName}"/></label>
                                </div>
                                <div class='col-lg-12' >
                                    <hr style="border-color:#005f8d">
                                </div>
                                <button type="submit" class="btn btn-success">Create</button>
                            </form>
                        </div>
                        <!-- /.col-lg-6 (nested) -->

                    </div>
                    <!-- /.row (nested) -->
                </div>
                <!-- /.panel-body -->
            </div>
        </div>
    </div>
</div>
<!-- jQuery -->
<script type="text/javascript">
    <%@include file="/resources/js/jquery.js"%>
</script>
<script type="text/javascript">
    <%@include file="/resources/js/bootstrap.min.js"%>
</script>

<script type="text/javascript">
    <%@include file="/resources/js/plugins/metisMenu/metisMenu.min.js"%>
</script>

<script type="text/javascript">
    <%@include file="/resources/js/sb-admin-2.js"%>
</script>

</body>
</html>