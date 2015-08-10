<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 10.08.2015
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div align="center">
    <h1>Users List</h1>

    <h2><a href="/new">New Person</a></h2>

    <table border="1">
        <th>No</th>
        <th>Username</th>
        <th>Email</th>
        <th>Actions</th>

        <c:forEach var="person" items="${personList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${person.name}</td>
                <td>${user.email}</td>
                <td>
                    <a href="/edit?id=${person.id}">Edit</a>
                    <a href="/delete?id=${person.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
