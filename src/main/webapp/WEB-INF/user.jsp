<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
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
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Blank</h1>
                <a href="${pageContext.request.contextPath}/logout">Logout</a>
                <a href="${pageContext.request.contextPath}/new">task-4</a>

                <br/>
                <label>Name: <c:out value="${sessionScope.user.firstName} ${sessionScope.user.lastName}"/></label>
                <label>Department: <c:out value="${sessionScope.user.department}"/></label>
                <label>Position: <c:out value="${sessionScope.user.position}"/></label>
                <label>email: <c:out value="${sessionScope.user.email}"/></label>

                <br/>

                <div style="float:left; width:80%;">

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Assigned tasks:
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="assigned_tasks">
                                    <thead>
                                    <tr>
                                        <th width="80px">Task ID</th>
                                        <th width="500px">Title</th>
                                        <th width="120px">Status</th>
                                        <th width="200px">Create Time</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="task" items="${requestScope.assignedTask}">
                                    <tr class="odd gradeX">
                                        <td><a href="${pageContext.request.contextPath}/task-${task.id}"><c:out value="${task.id}"/></a></td>
                                        <td><a href="${pageContext.request.contextPath}/task-${task.id}"><c:out value="${task.title}"/></a></td>
                                        <td><c:out value="${task.lastUpdate.status}"/></td>
                                        <td><c:out value="${task.createTime}"/></td>
                                    </tr>
                                    </tbody>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Created tasks:
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="created_tasks">
                                    <thead>
                                    <tr>
                                        <th width="80px">Task ID</th>
                                        <th width="500px">Title</th>
                                        <th width="120px">Status</th>
                                        <th width="200px">Create Time</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="task" items="${requestScope.createdTask}">
                                    <tr class="odd gradeX">
                                        <td><a href="${pageContext.request.contextPath}/task-${task.id}"><c:out value="${task.id}"/></a></td>
                                        <td><a href="${pageContext.request.contextPath}/task-${task.id}"><c:out value="${task.title}"/></a></td>
                                        <td><c:out value="${task.lastUpdate.status}"/></td>
                                        <td><c:out value="${task.createTime}"/></td>
                                    </tr>
                                    </tbody>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
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