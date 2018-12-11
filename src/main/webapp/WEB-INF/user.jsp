<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>TTS - Profile</title>

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
<%@include file="menu.jsp" %>
<!-- Page Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"><c:out value="${sessionScope.user.firstName} ${sessionScope.user.lastName}"/></h1>
            <h4>
                <label>Department: <c:out value="${sessionScope.user.department}"/></label><br/>
                <label>Position: <c:out value="${sessionScope.user.position}"/></label><br/>
                <label>email: <c:out value="${sessionScope.user.email}"/></label>
            </h4>
            <br/>

            <div style="float:left; width:80%;">
                <div class="panel-body">
                    <!-- Nav tabs -->
                    <ul class="nav nav-pills">
                        <li class="active"><a href="#initiator" data-toggle="tab">Initiated tasks</a>
                        </li>
                        <li><a href="#owner" data-toggle="tab">Owned tasks</a>
                        </li>
                        <li><a href="#watcher" data-toggle="tab">Watched tasks</a>
                        </li>
                    </ul>
                    <!-- Tab panes -->
                    <div class="tab-content">

                        <div class="tab-pane fade in active" id="initiator">
                            <div class="table-responsive table-bordered">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th width="80px">ID</th>
                                        <th width="500px">Title</th>
                                        <th width="120px">Status</th>
                                        <th width="200px">Create Time</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="task" items="${requestScope.taskListInitiated}">
                                        <tr class="odd gradeX">
                                            <td>
                                                <a href="${pageContext.request.contextPath}/servlet/task-${task.id}"><c:out
                                                        value="${task.id}"/></a></td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/servlet/task-${task.id}"><c:out
                                                        value="${task.title}"/></a></td>
                                            <td><c:out value="${task.lastUpdate.status}"/></td>
                                            <td><c:out value="${task.createTime}"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="owner">
                            <div class="table-responsive table-bordered">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th width="80px">Task ID</th>
                                        <th width="500px">Title</th>
                                        <th width="120px">Status</th>
                                        <th width="200px">Create Time</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="task" items="${requestScope.taskListAssigned}">
                                        <tr class="odd gradeX">
                                            <td>
                                                <a href="${pageContext.request.contextPath}/servlet/task-${task.id}"><c:out
                                                        value="${task.id}"/></a></td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/servlet/task-${task.id}"><c:out
                                                        value="${task.title}"/></a></td>
                                            <td><c:out value="${task.lastUpdate.status}"/></td>
                                            <td><c:out value="${task.createTime}"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div class="tab-pane fade" id="watcher">
                            <div class="table-responsive table-bordered">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th width="80px">Task ID</th>
                                        <th width="500px">Title</th>
                                        <th width="120px">Status</th>
                                        <th width="200px">Create Time</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="task" items="${requestScope.taskListWatched}">
                                        <tr class="odd gradeX">
                                            <td>
                                                <a href="${pageContext.request.contextPath}/servlet/task-${task.id}"><c:out
                                                        value="${task.id}"/></a></td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/servlet/task-${task.id}"><c:out
                                                        value="${task.title}"/></a></td>
                                            <td><c:out value="${task.lastUpdate.status}"/></td>
                                            <td><c:out value="${task.createTime}"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
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