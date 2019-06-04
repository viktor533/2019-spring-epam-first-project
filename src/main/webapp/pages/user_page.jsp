<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User</title>
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
            <li> User ${user.getId()} </li>
            <li> <span>Role:</span> <em>${user.getRole()}</em> </li>
            <li> <span>Login:</span> <em>${user.getLogin()}</em> </li>
            <li> <span>Password:</span> <em>${user.getPassword()}</em> </li>
        </ul>

        <table class="table_grizzly">
            <tr>
                <th>ID</th>
                <th>BookingId</th>
                <th>UserId</th>
                <th>RoomId</th>
                <th>Status</th>
            </tr>
            <c:forEach items="${user.getBills()}" var ="bill">
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
