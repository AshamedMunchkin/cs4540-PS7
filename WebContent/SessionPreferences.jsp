<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
<title>PS7 | Session Preferences</title>
<style>
body {
background:
<c:choose>
<c:when test="${!empty backgroundColor}">${backgroundColor}</c:when>
<c:otherwise>white</c:otherwise>
</c:choose>
;

color:
<c:choose>
<c:when test="${!empty fontColor}">${fontColor}</c:when>
<c:otherwise>black</c:otherwise>
</c:choose>
;

font-size:
<c:choose>
<c:when test="${!empty fontSize}">${fontSize}px</c:when>
<c:otherwise>16px</c:otherwise>
</c:choose>
;
}
</style>
</head>
<body>
<h1>Session Preferences</h1>
<ul>
<li><a href="./">Index</a></li>
</ul>
<form method="post">
<label for="backgroundColor">Background Color</label>
<select id="backgroundColor" name="backgroundColor">
<option value="default">Default</option>
<c:forEach items="${colors}" var="color">
<option value="${color.key}" <c:if test="${color.key == sessionScope.backgroundColor}">selected</c:if>>${color.value}</option>
</c:forEach>
</select>
<label for="fontColor">Font Color</label>
<select id="fontColor" name="fontColor">
<option value="default">Default</option>
<c:forEach items="${colors}" var="color">
<option value="${color.key}" <c:if test="${color.key == sessionScope.fontColor}">selected</c:if>>${color.value}</option>
</c:forEach>
</select>
<label for="fontSize">Font Size</label>
<select id="fontSize" name="fontSize">
<option value="default">Default</option>
<c:forEach items="${fontSizes}" var="fontSize">
<option value="${fontSize}" <c:if test="${fontSize == sessionScope.fontSize}">selected</c:if>>${fontSize}</option>
</c:forEach>
</select>
<input type="submit" value="Submit">
</form>
</body>
</html>