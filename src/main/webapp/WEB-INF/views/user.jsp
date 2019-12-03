<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>TrLibrary - User</title>
    
<script src="<c:url value="resources/js/jquery-3.4.1.js"/>"></script>
<script src="<c:url value="resources/js/app.js"/>"></script>    
</head>
<body>
<%@ include file="header.jsp" %> 
Seems like you're only user. Still better love story than Twilight.<br>

<form><div>
    <button type="submit" id="listallbooks">List all books</button>
    <button type="submit" id="listavailablebooks">List available books</button>
    <button type="submit" id="listallstudents">List all students</button>
</div></form>
<br>

<div id="usermessagediv"></div>
</body>
</html>