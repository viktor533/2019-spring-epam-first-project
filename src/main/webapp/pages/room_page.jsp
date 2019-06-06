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
        function goBill(id) {
            location.href = '${pageContext.request.contextPath}/bill?billId='+id;
        }
    </script>
</head>
<body>

    <dev class="centralCard">

        <ul class="descriptionList">
            <li> Room ${room.getId()} </li>
            <li> <span>Number:</span> <em>${room.getNumber()}</em> </li>
            <li> <span>Guests:</span> <em>${room.getNumOfGuests()}</em> </li>
            <li> <span>Price:</span> <em>${room.getPricePerNight()}</em> </li>
            <li> <span>Service:</span> <em>${room.getRoomClass()}</em> </li>
        </ul>

        <table class="table_grizzly">
            <tr>
                <th>ID</th>
                <th>BookingId</th>
                <th>UserId</th>
                <th>RoomId</th>
                <th>Status</th>
            </tr>
            <c:forEach items="${room.getBills()}" var ="bill">
                <tr>
                    <td onclick="goBill(${bill.getId()});">${bill.getId()}</td>
                    <td onclick="goBill(${bill.getId()});">${bill.getBookingId()}</td>
                    <td onclick="goBill(${bill.getId()});">${bill.getUserId()}</td>
                    <td onclick="goBill(${bill.getId()});">${bill.getRoomId()}</td>
                    <td onclick="goBill(${bill.getId()});">${bill.getStatus()}</td>
                </tr>
            </c:forEach>
        </table>

    </dev>
</body>
</html>
