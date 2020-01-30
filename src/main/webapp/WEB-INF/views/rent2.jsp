<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h2>Rent a book</h2>
<form:form action="/rent2" method="post" modelAttribute="bookToRentDTO">
<input type="text" id="bookId" name="bookId" value="222" style="display:none"/>
<label>Rent book to: </label>
<select name="userId">
  <option value="Weee">Weee</option>
  <option value="Heee">Heee</option>
  <option value="Geee">Geee</option>
</select>
<input type="submit" value="Rent a book">
</form:form>