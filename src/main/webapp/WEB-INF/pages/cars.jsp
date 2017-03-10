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
<%@ page session="false" %>
<html>
<head>
    <title>Cars</title>

    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

</head>
<body>
<a href="../../index.jsp">Back To Main Menu</a>

<br/>
<br/>

<h1>List of Cars</h1>
<c:if test="${!empty getAllCars}">
    <table class="tg">
        <tr>
            <td width = "50" >Id</td>
            <td width = "110" >Brand</td>
            <td width = "110" >Model</td>
            <td width = "110" >Registration Number</td>
            <td width = "65" >Edit</td>
            <td width = "65" >Delete</td>
        </tr>
        <c:forEach items="${getAllCars}" var="car">
            <tr>
                <td>${car.id}</td>
                <td>${car.brand}</td>
                <td>${car.model}</td>
                <td>${car.registrationNumber}</td>
                <td><a href="<c:url value='/editCar/${car.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/deleteCar/${car.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h1>Add Car</h1>

<c:url var="addAction" value ="/cars/addCar"/>

<form:form action="${addAction}" commandName="car">
    <table>
        <c:if test="${!empty car.model}">
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
                <form:label path="brand">
                    <spring:message text="Brand"/>
                </form:label>
            </td>
            <td>
                <form:input path="brand"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="model">
                    <spring:message text="Model"/>
                </form:label>
            </td>
            <td>
                <form:input path="model"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="registrationNumber">
                    <spring:message text="RegistrationNumber"/>
                </form:label>
            </td>
            <td>
                <form:input path="registrationNumber"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty car.model}">
                    <input type="submit"
                           value="<spring:message text="Edit Car"/>"/>
                </c:if>
                <c:if test="${empty car.model}">
                    <input type="submit"
                           value="<spring:message text="Add Car"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>


</body>
</html>
