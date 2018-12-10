<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<html>

<head>
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
        <div class="col-lg-12">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-6">
                                <button type="button" class="btn btn-primary" data-toggle="collapse"
                                        data-parent="#accordion" href="#change">Change
                                </button>
                                <div id="change" class="panel-collapse collapse">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <button type="button" class="btn btn-primary" data-toggle="collapse"
                                                        data-parent="#accordion" href="#assign">Assignee
                                                </button>
                                                <button type="button" class="btn btn-primary" data-toggle="collapse"
                                                        data-parent="#accordion" href="#category">Category
                                                </button>
                                                <button type="button" class="btn btn-primary" data-toggle="collapse"
                                                        data-parent="#accordion" href="#worklog">Work log
                                                </button>
                                                <button type="button" class="btn btn-primary" data-toggle="collapse"
                                                        data-parent="#accordion" href="#status">Status
                                                </button>
                                            </h4>
                                        </div>

                                        <div id="assign" class="panel-collapse collapse">
                                            <form method="post"
                                                  action="${pageContext.request.contextPath}/servlet/searchUser">
                                                <input type="hidden" name="searchList" value="userListAssign">
                                                <div class="panel-body">
                                                    <label class="radio-inline">
                                                        <input type="radio" name="searchCriteria"
                                                               value="byDepartment">
                                                        <select class="form-control">
                                                            <option>All departments</option>
                                                            <option>OPR</option>
                                                            <option>IT</option>
                                                        </select>
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="searchCriteria" value="byName"
                                                               checked>
                                                        <input class="form-control" placeholder="Search name..."
                                                               name="name">
                                                    </label>
                                                    <br/><br/>
                                                    <button type="submit"
                                                            class="btn btn-outline btn-primary btn-block">
                                                        Search
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                        <form method="post"
                                              action="${pageContext.request.contextPath}/servlet/assign">
                                            <table class="table table-hover">
                                                <c:if test="${sessionScope.userListAssign != null}">
                                                    <thead>
                                                    <tr>
                                                        <th>Name</th>
                                                        <th>Department</th>
                                                        <th>Position</th>
                                                        <th></th>
                                                    </tr>
                                                    </thead>
                                                </c:if>
                                                <tbody>
                                                <c:forEach var="user" items="${sessionScope.userListAssign}">
                                                    <tr>
                                                        <td><c:out value="${user.lastName}"/></td>
                                                        <td><c:out value="${user.department}"/></td>
                                                        <td><c:out value="${user.position}"/></td>
                                                        <td>
                                                            <button type="button" name="assignee"
                                                                    value="${user.id}"
                                                                    class="btn btn-default btn-circle"></button>
                                                        </td>

                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>

                                            <div id="category" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                    <select class="form-control" name="category">
                                                        <option selected><c:out
                                                                value="${sessionScope.task.lastUpdate.category}"/></option>
                                                        <option>Category</option>
                                                        <option>OPR</option>
                                                        <option>IT</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div id="worklog" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                    <label>Time(min):
                                                        <input class="form-control" name="duration">
                                                    </label>
                                                    <br>
                                                    <div lass="col-lg-12">
                                                        <label>Comment: </label>
                                                        <textarea class="form-control" rows="2"
                                                                  name="comment"></textarea>

                                                    </div>
                                                </div>
                                            </div>
                                            <div id="status" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                    <select class="form-control" name="status">
                                                        <option selected><c:out
                                                                value="${sessionScope.task.lastUpdate.status}"/></option>
                                                        <option>Category</option>
                                                        <option>OPR</option>
                                                        <option>IT</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div>
                                                <button type="submit" class="btn btn-success">Save</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <button type="button" class="btn btn-primary" data-toggle="collapse"
                                        data-parent="#accordion" href="#watcher">Add watcher
                                </button>
                                <div id="watcher" class="panel-collapse collapse">
                                    <form method="post"
                                          action="${pageContext.request.contextPath}/servlet/searchUser">
                                        <input type="hidden" name="searchList" value="userListWatch">
                                        <div class="panel-body">
                                            <label class="radio-inline">
                                                <input type="radio" name="searchCriteria"
                                                       value="byDepartment">
                                                <select class="form-control">
                                                    <option>All departments</option>
                                                    <option>OPR</option>
                                                    <option>IT</option>
                                                </select>
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="searchCriteria" value="byName"
                                                       checked>
                                                <input class="form-control" placeholder="Search name..."
                                                       name="name">
                                            </label>
                                            <br/><br/>

                                            <button type="submit" class="btn btn-outline btn-primary btn-block">
                                                Search
                                            </button>
                                        </div>
                                    </form>

                                    <form method="post"
                                          action="${pageContext.request.contextPath}/servlet/addWatcher">
                                        <table class="table table-hover">
                                            <c:if test="${sessionScope.userListWatch != null}">
                                                <thead>
                                                <tr>
                                                    <th>Name</th>
                                                    <th>Department</th>
                                                    <th>Position</th>
                                                    <th></th>
                                                </tr>
                                                </thead>
                                            </c:if>
                                            <c:forEach var="user" items="${sessionScope.userListWatch}">
                                                <tr>
                                                    <td><c:out value="${user.lastName}"/></td>
                                                    <td><c:out value="${user.department}"/></td>
                                                    <td><c:out value="${user.position}"/></td>
                                                    <td>
                                                        <div>
                                                            <button type="submit" class="btn btn-success"
                                                                    name="watcher" value="${user.id}">Add
                                                            </button>
                                                        </div>
                                                    </td>

                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>

                                    </form>
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

                                <form method="post" action="${pageContext.request.contextPath}/servlet/comment">
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
                                        <li><a href="#history" data-toggle="tab">History</a>
                                        </li>
                                    </ul>

                                    <!-- Tab panes -->
                                    <div class="tab-content">
                                        <div class="tab-pane fade in active" id="comment">
                                            </br>
                                            <c:forEach var="comment" items="${requestScope.comments}">
                                                <p><c:out value="${comment.recorder.lastName}"/> <c:out
                                                        value="${comment.recordTime}"/></p>
                                                <textarea class="form-control" rows="3"><c:out
                                                        value="${comment.comment}"/></textarea>
                                            </c:forEach>
                                        </div>
                                        <div class="tab-pane fade" id="history">
                                            </br>

                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
                                                eiusmod
                                                tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                                                veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex
                                                ea
                                                commodo consequat. Duis aute irure dolor in reprehenderit in
                                                voluptate
                                                velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint
                                                occaecat cupidatat non proident, sunt in culpa qui officia deserunt
                                                mollit anim id est laborum.</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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