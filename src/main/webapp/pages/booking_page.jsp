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
                    <a href="${pageContext.request.contextPath}?lang=en_US"><fmt:message key="language_eng"/></a></li>
                    <a href="${pageContext.request.contextPath}?lang=ru_RU"><fmt:message key="language_rus"/></a></li>
                    <a href="${pageContext.request.contextPath}?lang=cat_CAT"><fmt:message key="language_cat"/></a></li>
                    <a href="${pageContext.request.contextPath}?lang=dog_DOG"><fmt:message key="language_dog"/></a></li>                </td>
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
                            <li> Booking id: ${booking.getId()} </li>
                            <li> <span>Class:</span> <em>${booking.getRoomClass()}</em> </li>
                            <li> <span>Start:</span> <em>${booking.getStart()}</em> </li>
                            <li> <span>End:</span> <em>${booking.getEnd()}</em> </li>
                        </ul>
                    </dev>
                </td>
            </tr>
        </table>
        <p align="center">Pet Hotel, New York, 8-800-555-35-35</p>
    </div>
</body>
</html>


