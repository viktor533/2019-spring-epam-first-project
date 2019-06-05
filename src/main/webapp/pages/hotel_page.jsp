<%--
  Created by IntelliJ IDEA.
  User: viktor
  Date: 05.06.19
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>--%>
<html>
<head>
    <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/css/hotel.css'/>
    <title>Hotel Page</title>
    <script>
        function goRoom(id) {
            location.href = '${pageContext.request.contextPath}/room?roomId='+id;
        }
    </script>

</head>
<body>
    <ul>
        <li> Hotel id: ${hotel.getId()} </li>
        <li> Hotel name: ${hotel.getName()} </li>
        <li> Hotel location: ${hotel.getLocation()} </li>
        <li> Hotel luxury: ${hotel.getLuxury()} </li>
    </ul>

    <table class="table_grizzly">
        <tr>
            <th>ID</th>
            <th>Места</th>
            <th>Стоимость</th>
            <th>Класс</th>
        </tr>

        <c:forEach items="${hotel.getRooms()}" var = "room">
            <tr>
                <td onclick="goRoom(${room.getId()});">${room.getId()}</td>
                <td onclick="goRoom(${room.getId()});">${room.getNumOfGuests()}</td>
                <td onclick="goRoom(${room.getId()});">${room.getPricePerNight()}</td>
                <td onclick="goRoom(${room.getId()});">${room.getRoomClass()}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
