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

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <div class="row">
                <div class="col-lg-12">
                    <%--<div class="panel panel-default">--%>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <button type="button" class="btn btn-primary" data-toggle="collapse"
                                                    data-parent="#accordion" href="#collapse">Assign
                                            </button>
                                            <button type="button" class="btn btn-primary" data-toggle="collapse"
                                                    data-parent="#accordion" href="#collapse">Close
                                            </button>
                                        </h4>
                                    </div>
                                    <div id="collapse" class="panel-collapse collapse">
                                        <form method="post" action="${pageContext.request.contextPath}/searchUser">
                                            <div class="panel-body">
                                                <label class="radio-inline">
                                                    <input type="radio" name="searchCriteria" value="department"
                                                           checked>
                                                    <div class="form-group">
                                                        <select class="form-control">
                                                            <option>All departments</option>
                                                            <option>OPR</option>
                                                            <option>IT</option>
                                                        </select>
                                                    </div>
                                                </label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="searchCriteria" value="name">
                                                    <div class="form-group">
                                                        <input type="text" class="form-control"
                                                               placeholder="Search name...">
                                                    </div>
                                                </label>
                                                <button type="submit" class="btn btn-primary btn-lg btn-block">Search
                                                </button>
                                            </div>
                                        </form>
                                        <table class="table table-hover">
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Name</th>
                                                <th>Department</th>
                                                <th>Position</th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="task" items="${requestScope.searchResult}">
                                                <tr>
                                                    <td><c:out value="${user.name}"/></td>
                                                    <td><c:out value="${user.department}"/></td>
                                                    <td><c:out value="${user.position}"/></td>
                                                    <form method="post"
                                                          action="${pageContext.request.contextPath}/assign">
                                                        <td>
                                                            <button type="submit" name="${user.id}"
                                                                    class="btn btn-default btn-circle"></button>
                                                        </td>
                                                    </form>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                                <p class="form-control-static"><c:out value="${sessionScope.task.title}"/></p>

                                <label>Create Time: <c:out value="${sessionScope.task.createTime}"/></label><br/>
                                <label>Initiator: <c:out
                                        value="${sessionScope.task.creator.lastName}"/></label><br/>
                                <label>Status: <c:out value="${sessionScope.task.lastUpdate.status}"/></label><br/>
                                <label>Assignee: <c:out
                                        value="${sessionScope.task.lastUpdate.owner.lastName}"/></label><br/>

                                <label>Description</label>
                                <textarea class="form-control" rows="10" disabled><c:out
                                        value="${sessionScope.task.description}"/></textarea>

                                <br/>

                                <form method="post" action="${pageContext.request.contextPath}/comment">
                                    <textarea class="form-control" rows="2" name="comment"></textarea>
                                    <button type="submit">Comment</button>
                                </form>
                            </div>
                            <div class="col-lg-6">
                                <%--<div class="panel panel-default">--%>
                                <!-- /.panel-heading -->
                                <div class="panel-body">
                                    <!-- Nav tabs -->
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a href="#comment" data-toggle="tab">Comment</a>
                                        </li>
                                        <li><a href="#profile" data-toggle="tab">Profile</a>
                                        </li>
                                    </ul>

                                    <!-- Tab panes -->
                                    <div class="tab-content">
                                        <div class="tab-pane fade in active" id="comment">
                                            <h3>Comments</h3>
                                            <c:forEach var="comment" items="${requestScope.comments}">
                                                <p><c:out value="${comment.recorder.lastName}"/> <c:out
                                                        value="${comment.recordTime}"/></p>
                                                <textarea class="form-control" rows="3"><c:out
                                                        value="${comment.comment}"/></textarea>
                                            </c:forEach>
                                        </div>
                                        <div class="tab-pane fade" id="profile">
                                            <h4>Profile Tab</h4>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                                tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                                                veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                                                commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
                                                velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint
                                                occaecat cupidatat non proident, sunt in culpa qui officia deserunt
                                                mollit anim id est laborum.</p>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.panel-body -->
                                <%--</div>--%>
                            </div>

                        </div>
                    </div>
                    <%--</div>--%>
                </div>
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