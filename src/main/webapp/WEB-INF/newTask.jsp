<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<body>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
<a href="${pageContext.request.contextPath}/profile">Profile</a>

<h2>New Task</h2>

<div style="float:left; width:50%;">

    <br />

    <br />

    <form method="post" action="${pageContext.request.contextPath}/create">
        <input type="text" name="title"><br/>
        <input type="text" name="description"><br/><br/>
        <input type="text" name="category"><br/><br/>
        <input class="button" type="submit" value="Create">

    </form>

</div>
</body>
</html>