<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h2>Rent a book</h2>
<form:form class="rentForm" action="/rentconfirm" method="post" modelAttribute="bookToRentDTO">
<input type="text" id="bookId" value="${rentBookId}" style="display:none"/>
Book "${rentBookName}"<br>
<label>Rent book to: </label>
<select id="selectId">
  <c:forEach items="${rentListOfStudents}" var="tempStudent">
  <option value="<c:out value="${tempStudent.userId}"/>">${tempStudent.name} ${tempStudent.surname}</option>
  </c:forEach>
</select>
<input type="submit" class="rentConfirmationButton" value="Rent a book">
</form:form>