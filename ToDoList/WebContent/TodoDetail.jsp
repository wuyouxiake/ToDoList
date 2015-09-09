<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>To-do Detail</title>
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
<div class="container">
  <table class="table table-hover">
    <thead>
      <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Due Date</th>
        <th>Complete Date</th>
        <th>Priority</th>
        <th>Status</th>
      </tr>
    </thead>
    <tbody>
      ${fullList}
    </tbody>
  </table>
</div>



</body>
</html>