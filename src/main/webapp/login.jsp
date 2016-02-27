<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title>Messaging system sign in</title>
	<script src="http://code.jquery.com/jquery-1.12.1.min.js"></script>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
	<link rel="stylesheet" href=/resources/css/my_styles.css"/>
</head>
<body>
	<a href="<c:url value="/register.jsp" />">
		<button class="btn btn-default" type="button" >
			Sign up
		</button>
	</a>
	<br><br>
	<c:if test="${not empty param.error}">
		<font color="red"> Username or password is incorrect </font>
	</c:if>
	<form method="POST" action="<c:url value="/login" />">
		<table>
		<tr>
			<td>
				Login:
			</td>
			<td>
				<input type="text" name="username" />
			</td>
		</tr>
		<tr>
			<td>
				Password:
			</td>
			<td>
				<input type="password" name="password" />
			</td>
		</tr>
		<tr>
			<td>
				Remember me:
			</td>
			<td>
				<input type="checkbox" name="_spring_security_remember_me" />
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="Login" />
			</td>
			<td>
				<input type="reset" value="Reset" />
			</td>
		</tr>
		</table>
	</form>
</body>
</html>