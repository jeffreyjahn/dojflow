<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.lang.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Questions Dashboard</title>
    <link rel="stylesheet" type="text/css" href="/css/styles.css" />
    <script type="text/javascript" src="/js/app.js"></script>
</head>
<body>
	<h1>Questions Dashboard</h1>
	<table>
		<tr>
			<th>Question</th>
			<th>Tags</th>
		</tr>
		<c:forEach items="${ questions }" var="question">
			<tr>
				<td><a href="questions/${ question.id }"><c:out value="${ question.question }"/></a></td>
				<td><c:forEach var="tag" items="${ tags }"><c:if test="${ tag.questions.contains(question) }"><c:out value="${ tag.subject }"/>, </c:if></c:forEach></td>
			</tr>
		</c:forEach>
	</table>
	<a href="/questions/new">New Question</a>
</body>
</html>