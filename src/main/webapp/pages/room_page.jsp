<%--
  Created by IntelliJ IDEA.
  User: viktor
  Date: 05.06.19
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Room</title>
    <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/css/common.css'/>
    <script>
        function goBooking(id) {
            location.href = '${pageContext.request.contextPath}/booking?bookingId='+id;
        }
    </script>
</head>
<body>

    <dev class="centralCard">

        <ul class="descriptionList">
            <li> Room ${room.getId()} </li>
            <li> <span>Location:</span> <em>${room.getNumOfGuests()}</em> </li>
            <li> <span>Price:</span> <em>${room.getPricePerNight()}</em> </li>
            <li> <span>Service:</span> <em>${room.getRoomClass()}</em> </li>
        </ul>

        <table class="table_grizzly">
            <tr>
                <th>ID</th>
                <th>Start</th>
                <th>End</th>
            </tr>
            <c:forEach items="${room.getBookings()}" var ="booking">
                <tr>
                    <td onclick="goBooking(${booking.getId()});">${booking.getId()}</td>
                    <td onclick="goBooking(${booking.getId()});">${booking.getRoomClass()}</td>
                    <td onclick="goBooking(${booking.getId()});">${booking.getStart()}</td>
                    <td onclick="goBooking(${booking.getId()});">${booking.getEnd()}</td>
                </tr>
            </c:forEach>
        </table>

    </dev>
</body>
</html>
