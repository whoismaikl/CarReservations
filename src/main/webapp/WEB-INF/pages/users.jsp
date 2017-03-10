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
    <title>Users</title>

    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

</head>
<body>
<a href="../../index.jsp">Back To Main Menu</a>

<br/>
<br/>

<h1>User List</h1>
<c:if test="${!empty getAllUsers}">
    <table class="tg">
        <tr>
            <td width = "50" >Id</td>
            <td width = "110" >Name</td>
            <td width = "110" >Surname</td>
            <td width = "65" >Edit</td>
            <td width = "65" >Delete</td>
        </tr>
        <c:forEach items="${getAllUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td><a href="<c:url value='/editUser/${user.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/deleteUser/${user.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h1>Add User</h1>

<c:url var="addAction" value ="/users/addUser"/>

<form:form action="${addAction}" commandName="user">
    <table>
        <c:if test="${!empty user.name}">
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
                <form:label path="name">
                    <spring:message text="Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="surname">
                    <spring:message text="Surname"/>
                </form:label>
            </td>
            <td>
                <form:input path="surname"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty user.name}">
                    <input type="submit"
                           value="<spring:message text="Edit User"/>"/>
                </c:if>
                <c:if test="${empty user.name}">
                    <input type="submit"
                           value="<spring:message text="Add User"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>


</body>
</html>
