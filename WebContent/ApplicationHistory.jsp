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
<title>PS7 | Application History</title>
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
<h1>Application History</h1>
<ul>
<li><a href="./">Index</a></li>
</ul>
<form method="post">
<fieldset>
<label for="refreshRate">Rate</label>
<select id="refreshRate" name="refreshRate">
<option value="no">No</option>
<option value="5" <c:if test="${applicationRefreshRate == 5}">selected</c:if>>5</option>
<option value="10" <c:if test="${applicationRefreshRate == 10}">selected</c:if>>10</option>
<option value="20" <c:if test="${applicationRefreshRate == 20}">selected</c:if>>20</option>
<option value="30" <c:if test="${applicationRefreshRate == 30}">selected</c:if>>30</option>
<option value="60" <c:if test="${applicationRefreshRate == 60}">selected</c:if>>60</option>
</select>
<button type="submit" name="submit" value="refresh">Refresh</button>
</fieldset>
<fieldset>
<button type="submit" name="submit" value="applicationReset">Application Reset</button>
</fieldset>
</form>
<p>Distinct Browsers: ${counter}</p>
<ul>
<c:forEach items="${applicationHistory}" var="historyItem">
<li>
<table>
<tr>
<th>Browser ID:</th>
<td>${historyItem.browserId}</td>
</tr>
<tr>
<th>Browser Type:</th>
<td>${historyItem.browserType}</td>
</tr>
<tr>
<th>Request Date:</th>
<td>${historyItem.requestDate}</td>
</tr>
<tr>
<th>Request URL:</th>
<td>${historyItem.requestUrl}</td>
</tr>
<tr>
<th>Referer URL:</th>
<td>${historyItem.refererUrl}</td>
</tr>
</table>
</li> 
</c:forEach>
</ul>
</body>
</html>