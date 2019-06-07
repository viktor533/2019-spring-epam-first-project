<%--
  Created by IntelliJ IDEA.
  User: viktor
  Date: 05.06.19
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking</title>
    <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/css/common.css'/>
</head>
<body>
    <dev class="centralCard">

        <ul class="descriptionList">
            <li> Booking id: ${booking.getId()} </li>
            <li> <span>Class:</span> <em>${booking.getRoomClass()}</em> </li>
            <li> <span>Start:</span> <em>${booking.getStart()}</em> </li>
            <li> <span>End:</span> <em>${booking.getEnd()}</em> </li>
        </ul>
    </dev>

</body>
</html>
