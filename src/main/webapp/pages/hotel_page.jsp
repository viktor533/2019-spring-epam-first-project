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
<head>
    <title>Hotel Page</title>
    <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/css/common.css'/>
    <script>
        function goRoom(id) {
            location.href = '${pageContext.request.contextPath}/room?roomId='+id;
        }
    </script>
</head>
<body>
    <dev class="centralCard">

        <ul class="descriptionList">
            <li> Hotel ${hotel.getName()} </li>
            <li> <span>Location:</span> <em>${hotel.getLocation()}</em> </li>
            <li> <span>Stars:</span> <em>${hotel.getLuxury()}</em> </li>
        </ul>

        <table class="table_grizzly">
            <tr>
                <th>Number</th>
                <th>Places</th>
                <th>Price</th>
                <th>Service</th>
            </tr>
            <c:forEach items="${hotel.getRooms()}" var = "bill">
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
