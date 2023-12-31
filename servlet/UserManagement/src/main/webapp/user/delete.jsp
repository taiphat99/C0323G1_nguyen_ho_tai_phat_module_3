<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dannynguyen
  Date: 04/07/2023
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Form</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
</head>
<body>
<h1 style="text-align: center">Delete User</h1>
<a class="btn btn-outline-success" href="/UserManagement">Back to User List</a>
<div style="margin: 20px 50px">
    <form action="/UserManagement?action=delete&id=${user.getId()}" method="post">
        <fieldset>
            <legend>User Information</legend>
            <table>
                <tr>
                    <td>ID:</td>
                    <td>${user.getId()}</td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td>${user.getName()}</td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>${user.getEmail()}</td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td>${user.getCountry()}</td>
                </tr>
                <tr>
                    <td>Are you sure?</td>
                    <td><input type="submit" value="Yes"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><p style="color: crimson">
                        <c:if test='${requestScope["message"] != null}'>
                            <span class="message">${requestScope["message"]}</span>
                        </c:if>
                    </p></td>
                </tr>
            </table>
        </fieldset>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
