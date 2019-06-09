<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.epam.state.ServiceState" %>
<%@ page import="com.epam.service.UserService" %>
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
        function goUser(id) {
            location.href = '${pageContext.request.contextPath}/user?userId=' + id;
        }

        function refresh() {
            location.href = '${pageContext.request.contextPath};'
        }
    </script>
</head>

<body link="#000000" vlink="#006600" class="h-page-bg">
<table width="100%">
    <tr>
        <td width="100%">
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
                    <tr>
                        <td width="100%" valign="middle">
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
                                <p align="center">Pet Hotel, New York, 8-800-555-35-35</p>
                            </ul>
                        </td>
                        <td>
                            <dev class="centralCard">
                                <ul class="descriptionList">
                                    <li><fmt:message key="users"/></li>
                                </ul>
                                <button type="submit">+</button>
                                <table class="table_grizzly">
                                    <tr>
                                        <th>ID</th>
                                        <th><fmt:message key="role"/></th>
                                        <th><fmt:message key="accountName"/></th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                    <c:forEach items="${users}" var="user">
                                        <tr>
                                            <td onclick="goUser(${user.getId()});">${user.getId()}</td>
                                            <td onclick="goUser(${user.getId()});">${user.getRole()}</td>
                                            <td onclick="goUser(${user.getId()});">${user.getLogin()}</td>
                                            <td>
                                                <form action="" method="post">
                                                    <button type="submit" name=”delete” value=${user.getLogin()}>
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
            </div>
        </td>
    </tr>
</table>
</body>
</html>
