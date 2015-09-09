<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>
<c:set var="username" scope="session" value="${username}" />
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.jsp">To-Do List</a>
			</div>
			<div>
				<ul class="nav navbar-nav">
						<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
						<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
					<c:if test="${username != null}">
						<li><a href="addList.jsp">New To-Do</a></li>
						<li><a href="GetMyList">My List</a></li>
						<li><a href="SignOut">Sign Out</a></li>
						</c:if>	
					<c:if test="${username == null}">
						<li><a href="signin.jsp">Sign In</a></li>
						<li><a href="signup.jsp">Sign Up</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	<h3>Welcome to To-Do List!</h3>
</body>
</html>