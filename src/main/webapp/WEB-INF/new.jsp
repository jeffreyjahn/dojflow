<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.lang.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>New Question</title>
    <link rel="stylesheet" type="text/css" href="/css/styles.css" />
    <script type="text/javascript" src="/js/app.js"></script>
</head>
<body>
	<h1>What is your question?</h1>
	<form:form method="post" action="/questions/new" modelAttribute="newQuestion">
		<p>
			<form:label path="question">Question</form:label>
			<form:textarea path="question"/>
			<form:errors path="question"/>
		</p>
		<p>Tags:
			<input type="text" name="addTag">
		</p>
		<p>
			<input type="submit" value="Submit">
		</p>
	</form:form>
	<c:if test="${ errors != null }">
		<p> <c:out value="${ errors }"/></p>
	</c:if>
</body>
</html>