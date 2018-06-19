<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.lang.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Question Profile</title>
    <link rel="stylesheet" type="text/css" href="/css/styles.css" />
    <script type="text/javascript" src="/js/app.js"></script>
</head>
<body>
	<h1><c:out value="${ question.question }"/></h1>
	<table>
		<tr>
			<th>Answers</th>
		</tr>
		<c:forEach var="answer" items="${ question.answers }">
			<tr>
				<td><c:out value="${ answer.answer }"/></td>
			</tr>
		</c:forEach>
	</table>
	
	<form:form method="post" action="/questions/${question.id}" modelAttribute="addAnswer">
		<p>
			<form:label path="answer" value="Answer:"/>
			<form:input path="answer"/>
			<form:errors path="answer"/>
		</p>
		<p>
			<input type="submit" value="Add Answer">
		</p>
	</form:form>
</body>
</html>