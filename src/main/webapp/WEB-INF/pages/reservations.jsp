<%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 3/6/2017
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false" %>
<html>
<head>
    <title>Reservations</title>

    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

</head>
<body>
<a href="../../index.jsp">Back To Main Menu</a>

<br/>
<br/>

<h1>List of Reservations</h1>
<c:if test="${!empty getAllReservations}">
    <table class="tg">
        <tr>
            <td width="50">Id</td>
            <td width="110">Car</td>
            <td width="110">Username</td>
            <td width="110">Reserved FromDate</td>
            <td width="110">Reserved From Time</td>
            <td width="110">Reserved Till Date</td>
            <td width="110">Reserved Till Time</td>
            <td width="65">Edit</td>
            <td width="65">Delete</td>
        </tr>
        <c:forEach items="${getAllReservations}" var="reservation">
            <tr>
                <td>${reservation.id}</td>

                <c:set var="carId" value="${fn:split(reservation.car, '|')}" />
                <td><a href="/cardata/${carId[0]}">${reservation.car}</a></td>
                <c:set var="userId" value="${fn:split(reservation.user, '|')}" />
                <td><a href="/userdata/${userId[0]}">${reservation.user}</a></td>

                <td>${reservation.dateReservedFrom}</td>
                <td>${reservation.timeReservedFrom}</td>
                <td>${reservation.dateReservedTill}</td>
                <td>${reservation.timeReservedTill}</td>
                <td><a href="<c:url value='/editReservation/${reservation.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/deleteReservation/${reservation.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h1>Add Reservation</h1>

<c:url var="addAction" value="/reservations/addReservation"/>

<form:form action="${addAction}" commandName="reservation" method="POST" modelAttribute="reservation">

    <table>

        <c:if test="${!empty reservation.dateReservedFrom}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="car">
                    <spring:message text="Car"/>
                </form:label>
            </td>
            <td>
                <form:select path="car" items="${getAllCars}"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="user">
                    <spring:message text="User"/>
                </form:label>
            </td>
            <td>
                <form:select path="user" items="${getAllUsers}"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="dateReservedFrom">
                    <spring:message text="DateReservedFrom"/>
                </form:label>
            </td>
            <td>
                <form:input path="dateReservedFrom"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="timeReservedFrom">
                    <spring:message text="TimeReservedFrom"/>
                </form:label>
            </td>
            <td>
                <form:input path="timeReservedFrom"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="dateReservedTill">
                    <spring:message text="DateReservedTill"/>
                </form:label>
            </td>
            <td>
                <form:input path="dateReservedTill"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="timeReservedTill">
                    <spring:message text="TimeReservedTill"/>
                </form:label>
            </td>
            <td>
                <form:input path="timeReservedTill"/>
            </td>
        </tr>
            <spring:message text="Date Format yyyy-MM-dd, Time Format hh:mm (2023-12-27, 22:34)"/>
        <tr>
            <td colspan="2">
                <c:if test="${!empty reservation.dateReservedFrom}">
                    <input type="submit"
                           value="<spring:message text="Edit Reservation"/>"/>
                </c:if>
                <c:if test="${empty reservation.dateReservedFrom}">
                    <input type="submit"
                           value="<spring:message text="Add Reservation"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
