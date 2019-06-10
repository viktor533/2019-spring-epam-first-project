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
        function goBill(id) {
            location.href = '${pageContext.request.contextPath}/bill?billId='+id;
        }
    </script>
</head>

    <body link="#000000" vlink="#006600" class="h-page-bg">
        <div align="center">
            <table width="1000" height="176" bgcolor="#8feda0">
                <tr>
                    <td width="20%" valign="top">
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
                    <td width="100px">
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
                                <li> <fmt:message key="user"/> ${user.getId()} </li>
                                <li> <span><fmt:message key="role"/>:</span> <em>${user.getRole()}</em> </li>
                                <li> <span><fmt:message key="accountName"/>:</span> <em>${user.getLogin()}</em> </li>
                                <li> <span><fmt:message key="password"/>:</span> <em>${user.getPassword()}</em> </li>
                            </ul>
                            <button type="submit">+</button>
                            <table class="table_grizzly">
                                <tr>
                                    <th>ID</th>
                                    <th><fmt:message key="bookingId"/></th>
                                    <th><fmt:message key="userId"/></th>
                                    <th><fmt:message key="roomId"/></th>
                                    <th><fmt:message key="status"/></th>
                                    <th><fmt:message key="delete"/></th>
                                    <th><fmt:message key="update"/></th>
                                </tr>
                                <c:forEach items="${user.getBills()}" var ="bill">
                                    <tr>
                                        <td onclick="goBill(${bill.getId()});">${bill.getId()}</td>
                                        <td onclick="goBill(${bill.getId()});">${bill.getBookingId()}</td>
                                        <td onclick="goBill(${bill.getId()});">${bill.getUserId()}</td>
                                        <td onclick="goBill(${bill.getId()});">${bill.getRoomId()}</td>
                                        <td onclick="goBill(${bill.getId()});">${bill.getStatus()}</td>
                                        <td>
                                            <form>
                                                <button>
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
