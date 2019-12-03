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
    <title>TrLibrary - Student</title>
    
<script src="<c:url value="resources/js/jquery-3.4.1.js"/>"></script>
<script src="<c:url value="resources/js/app.js"/>"></script>    
</head>
<body>
<%@ include file="header.jsp" %> 
Seems like you're student. Well, first three years are difficult but don't worry, second year is easier.<br><br>

<p><b><font color="red">CAUTION! Below options should be used only to test menu that is available for administrator and user!<br>
Once clicked, log out/in to allow changes to take effect.</font></b></p>
<form><div>
    <button type="submit" id="studenttouser">Turn student to user</button>
</div></form>

<form><div>
    <button type="submit" id="studenttoadmin">Turn student to admin</button>
</div></form>

<div id="messagediv"></div>
</body>
</html>