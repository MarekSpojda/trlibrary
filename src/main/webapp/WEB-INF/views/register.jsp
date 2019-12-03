<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>TrLibrary - Register</title>
    
    <script src="<c:url value="resources/js/jquery-3.4.1.js"/>"></script>
</head>
<body>
<%@ include file="header.jsp" %> 
        <h2>Create account</h2>
        <table>
        <form:form action="/register" method="post" modelAttribute="userDTO">
            <tr>
               <td>Your name</td>
               <td><div><input type="text" name="name" placeholder="Name"/></div></td>
            </tr>
            <tr>
               <td>Your surname</td>
               <td><div><input type="text" name="surname" placeholder="Surname"/></div></td>
            </tr>
            <tr>
               <td>Your e-mail</td>
               <td><div><input type="email" name="email" placeholder="Email"/></div></td>
            </tr>
            <tr>
               <td>Your birthdate</td>
               <td><div><input type="date" name="birthDate" placeholder="Birthdate"/></div></td>
            </tr>
            <tr>
               <td>Your password</td>
               <td><div><input type="password" name="password" placeholder="Password"/></div></td>
            </tr>
            <tr>
               <td>Confirm password</td>
               <td><div><input type="password" name="password2" placeholder="Confirm pass"/></div></td>
            </tr>
            <tr>
               <td><div><button type="submit">Create account</button></div></td>
            </tr>
        </form:form>
        </table>
</body>
</html>