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
    <title>Car Data</title>

    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>
<h1>Car Details</h1>

<table class="tg">
    <tr>
        <th width="50">Id</th>
        <th width="110">Brand</th>
        <th width="110">Model</th>
        <th width="110">Registration Number</th>
        <th width="110">Current Reservation Id</th>
    </tr>
    <tr>
        <td>${car.id}</td>
        <td>${car.brand}</td>
        <td>${car.model}</td>
        <td>${car.registrationNumber}</td>
        <td>${car.currentReservationId}</td>
    </tr>
</table>
</body>
</html>
