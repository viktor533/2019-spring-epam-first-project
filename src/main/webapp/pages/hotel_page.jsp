<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="labels"/>

<html lang="${param.lang}">
<head>
    <title>Dolphin Hotel</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
    <script>
        function goRoom(id) {
            location.href = '${pageContext.request.contextPath}/room?roomId='+id;
        }
    </script>
</head>
<body link="#000000" vlink="#006600" class="h-page-bg">

    <div align="center">
        <table width="1000" height="176" bgcolor="#8feda0">
            <tr>
                <td width="100%" valign="top">
                    <a href="${pageContext.request.contextPath}">
                        <img
                                src="resources/logo.png"
                                width="300" height="176">
                    </a>
                </td>

                <td width="100%" valign="top">
                    <a href="${pageContext.request.contextPath}">
                        <img
                                src="resources/pets.png"
                                width="559" height="176">
                    </a>
                </td>
            </tr>
            <tr>
                <td height="50">
                    <a href="?lang=en_US"><fmt:message key="language_eng"/></a></li>
                    <a href="?lang=ru_RU"><fmt:message key="language_rus"/></a></li>
                </td>
            </tr>
        </table>
        <table width="1000" height="176" bgcolor="#8feda0">
            <tr>
                <td width="100px" valign="middle">
                    <ul>
                        <li>
                            <p align="left" class="h-links"><a
                                    href="${pageContext.request.contextPath}/login"
                                    target="_self"><fmt:message key="login"/></a>
                        </li>
                        <li>
                            <p align="left" class="h-links"><a
                                    href="${pageContext.request.contextPath}/registration"
                                    target="_self"><fmt:message key="registration"/></a>
                        </li>
                        <li>
                            <p align="left" class="h-links"><a
                                    href="${pageContext.request.contextPath}/hotel"
                                    target="_self"><fmt:message key="hotel"/></a>
                        </li>
                    </ul>
                </td>
                <td>
                    <dev class="centralCard">

                        <ul class="descriptionList">
                            <li> <fmt:message key="hotel"/>: ${hotel.getName()} </li>
                            <li><span><fmt:message key="location"/>:</span> <em>${hotel.getLocation()}</em></li>
                            <li><span><fmt:message key="stars"/>:</span> <em>${hotel.getLuxury()}</em></li>
                        </ul>
                        <button type="submit">+</button>
                        <table class="table_grizzly">
                            <tr>
                                <th><fmt:message key="number"/></th>
                                <th><fmt:message key="places"/></th>
                                <th><fmt:message key="price"/></th>
                                <th><fmt:message key="service"/></th>
                                <th><fmt:message key="delete"/></th>
                                <th><fmt:message key="update"/></th>
                            </tr>
                            <c:forEach items="${hotel.getRooms()}" var="room">
                                <tr>
                                    <td onclick="goRoom(${room.getId()});">${room.getNumber()}</td>
                                    <td onclick="goRoom(${room.getId()});">${room.getNumOfGuests()}</td>
                                    <td onclick="goRoom(${room.getId()});">${room.getPricePerNight()}</td>
                                    <td onclick="goRoom(${room.getId()});">${room.getRoomClass()}</td>
                                    <td>
                                        <form>
                                            <button  action="${pageContext.request.contextPath}/removeRoom?roomId=${items.getId()}" method="post">
                                                <fmt:message key="delete"/>
                                            </button>
                                        </form>
                                    </td>
                                    <td>
                                        <form>
                                            <button>
                                                <fmt:message key="update"/>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </dev>
                </td>
            </tr>
        </table>
        <p align="center">Pet Hotel, New York, 8-800-555-35-35</p>

    </div>

</body>
</html>
