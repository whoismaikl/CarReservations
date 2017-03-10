<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 3/6/2017
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Data</title>

    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

</head>
<body>
<h1>User Details</h1>

<table class="tg">
    <tr>
        <th width="50">Id</th>
        <th width="110">Name</th>
        <th width="110">Surname</th>
        <th width="110">Current Reservation Id</th>
    </tr>
    <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.surname}</td>
        <td>${user.currentReservationId}</td>
    </tr>
</table>
</body>
</html>
