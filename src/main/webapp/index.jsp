<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="labels"/>

<html lang="${param.lang}>
<head>
    <title>Dolphin Hotel</title>
    <link rel="stylesheet" type="text/css" href="/css/common.css'/">
</head>

<body link="#000000" vlink="#006600" class="h-page-bg">
<center>
    <table width="100%">
        <tr>
            <td width="100%">
                <div align="center">
                    <table width="100%" bgcolor="#8feda0">
                        <tr>
                            <td valign="top"><img
                                    src="resources/logo.png"
                                    width="300" height="176">
                            </td>

                            <td valign="top"><img
                                    src="resources/pets.png"
                                    width="559" height="176">
                            </td>
                        </tr>
                        <tr>
                            <td width="100%" height="50">
                                <a href="?lang=en_US"><fmt:message key="language_eng" /></a></li>
                                <a href="?lang=ru_RU"><fmt:message key="language_rus" /></a></li>
                            </td>
                        </tr>
                        <tr>
                            <td width="100%" valign="middle">
                                <ul>
                                    <li>
                                        <p align="left" class="h-links"><a
                                                href="${pageContext.request.contextPath}/hotel"
                                                target="_blank"><fmt:message key="hotel"/></a>
                                    </li>
                                    <li>
                                        <p align="left" class="h-links"><a
                                                href="${pageContext.request.contextPath}/login.jsp"
                                                target="_blank"><fmt:message key="login"/></a>
                                    </li>
                                    <li>
                                        <p align="left" class="h-links"><a
                                                href="${pageContext.request.contextPath}/registration_page.jsp"
                                                target="_blank"><fmt:message key="registration"/></a>
                                    </li>
                                    <li>
                                        <p align="left" class="h-links"><a
                                                href="${pageContext.request.contextPath}/user"
                                                target="_blank"><fmt:message key="profile"/></a>
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
