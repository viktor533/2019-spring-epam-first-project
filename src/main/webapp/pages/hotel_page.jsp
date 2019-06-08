<%--
  Created by IntelliJ IDEA.
  User: viktor
  Date: 05.06.19
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<body>
<dev class="centralCard">

    <ul class="descriptionList">
        <li> Hotel ${hotel.getName()} </li>
        <li><span>Location:</span> <em>${hotel.getLocation()}</em></li>
        <li><span>Stars:</span> <em>${hotel.getLuxury()}</em></li>
    </ul>

    <table class="table_grizzly">
        <tr>
            <th>Number</th>
            <th>Places</th>
            <th>Price</th>
            <th>Service</th>
        </tr>
        <c:forEach items="${hotel.getRooms()}" var="bill">
            <tr>
                <td onclick="goRoom(${bill.getId()});">${bill.getNumber()}</td>
                <td onclick="goRoom(${bill.getId()});">${bill.getNumOfGuests()}</td>
                <td onclick="goRoom(${bill.getId()});">${bill.getPricePerNight()}</td>
                <td onclick="goRoom(${bill.getId()});">${bill.getRoomClass()}</td>
            </tr>
        </c:forEach>
    </table>
</dev>
</body>
</html>

<html>

<head>
    <title>Hotel Page</title>
    <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/css/common.css'/>
    <script>
        function goRoom(id) {
            location.href = '${pageContext.request.contextPath}/room?roomId=' + id;
        }
    </script>
</head>

<body link="#000000" vlink="#006600" class="h-page-bg">
<center>
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
            <td width="100%" valign="top">
                <div align="center">
                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                        <tr>
                            <td width="300" height="176" valign="top" background="images/bgbar.gif"><img
                                    border="0" src="https://cdn.onlinewebfonts.com/svg/img_74315.png"
                                    width="300" height="176"
                                    alt="Port Angeles Downtown Hotel   101 1/2 E. Front St.   Port Angeles, WA 98362  360-565-1125 or  866-688-8600">
                            </td>
                            <td valign="top" background="images/right.jpg">
                        </tr>
                        <tr>
                            <td width="200" background="images/bgbar.gif" height="500" valign="top">
                                <p align="center"><img border="0" src="images/scroll-gr.gif" width="177"
                                                       height="16">
                            </td>
                        </tr>
                        <tr>
                            <td width="100%" valign="middle">
                                <ul>
                                    <li>
                                        <p align="left" class="h-links"><a
                                                href="${pageContext.request.contextPath}/hotel"
                                                target="_blank">Hotel</a>
                                    </li>
                                    <li>
                                        <p align="left" class="h-links"><a
                                                href="${pageContext.request.contextPath}/login.jsp"
                                                target="_blank">Login</a>
                                    </li>
                                    <li>
                                        <p align="left" class="h-links"><a
                                                href="${pageContext.request.contextPath}/registration_page.jsp"
                                                target="_blank">Registration</a>
                                    </li>
                                    <li>
                                        <p align="left" class="h-links"><a
                                                href="${pageContext.request.contextPath}/user"
                                                target="_blank">Profile</a>
                                    </li>
                                    <p class="h-hotel" align="center">Pet Hotel,
                                        Saint-Petersburg,
                                        8-800-555-35-35
                                </ul>
                            </td>
                        </tr>
                    </table>
                </div>
            </td>
        </tr>
    </table>
</center>

</body>
</html>

