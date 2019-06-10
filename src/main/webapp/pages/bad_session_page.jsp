
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="labels"/>

<html lang="${param.lang}">
<head>
    <title>Title</title>
</head>
<body>
    <%--<h1>Hello!</h1>--%>
    <h1> <fmt:message key="bad_access"/></h1>
    <a href="${pageContext.request.contextPath}/"><fmt:message key="home_page"/></a>
</body>
</html>
