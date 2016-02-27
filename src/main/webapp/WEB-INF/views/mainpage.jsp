<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Messaging server</title>
<script src="http://code.jquery.com/jquery-1.12.1.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/javaeett/resources/css/my_styles.css">
</head>
<body>
	<div>
	<div class="header">
		<h2>Messaging system test task</h2>
	</div>
	<div>
		<a href="<c:url value="/listAddressBook" />">
			<button class="btn btn-default" type="button" onclick="$('#addressbook').show();">
				Address book
			</button>
		</a>
		<button class="btn btn-default" type="button" onclick="$('#passwordChange').show();">
			Change password
		</button>
		<security:authorize access="hasRole('ROLE_ADMIN')">
			<a href="<c:url value="/listUsers" />">
			<button class="btn btn-default" type="button" onclick="$('#admincontrols').show();">
				Admin panel
			</button>
			</a>
			</security:authorize>
		<a href="<c:url value="/logout" />">
		<button class="btn btn-default" type="button">
			Logout
		</button>
		</a>
	</div>
	
	<hr>
	<!-- Password change -->
	<div id="passwordChange" class="centered  ">
		<form action="changePassword" method="POST">
			New password: <input type="text" name="newpassword">
			<input type="submit" value="Change password" />
		</form>
	</div>
	
	<!-- Messages list -->
	<div id="messages">
		<c:if test="${!empty messagesList}">
			<table class="table">
				<tr>
					<th><a href="/javaeett/index/0">From</a></th>
					<th><a href="/javaeett/index/1">Date</a></th>
					<th><a href="/javaeett/index/2">Subject</a></th>
					<th>&nbsp;</th>
				</tr>
				<c:forEach items="${messagesList}" var="message">
					<tr>
						<td>${message.fromuserlogin}</td>
						<td>${message.date}</td>
						<td>${message.subject}</td>
						<td><a href="deleteMessage/${message.messageId}">Delete message</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		
		<form:form method="POST" action="sendMessage" modelAttribute="message">
			To: <form:input path="touserlogin" />
			Subject: <form:input path="subject" />
			Text: <form:input path="text" />
			<input type="submit" value="Send" />
		</form:form>
	</div>
	
		<security:authorize access="hasRole('ROLE_ADMIN')">
		<!-- Administrator controls -->
		<div id="admincontrols" class="centered  ">
		<c:if test="${!empty usersList}">
				<table class="table">
					<tr>
						<th>Name:</th>
						<th>Login:</th>
						<th>E-Mail:</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${usersList}" var="user">
						<tr>
							<td>${user.username}</td>
							<td>${user.login}</td>
							<td>${user.email}</td>
							<td><a href="addAdminRights/${user.userId}">Add admin rights</a></td>
							<td><a href="removeAdminRights/${user.userId}">Remove admin rights</a></td>
							<td><a href="removeUser/${user.userId}">Delete user</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			
		<form:form method="post" action="addUser" modelAttribute="user">
			<form:label path="username">Username:</form:label> <form:input path="username" />
			<form:label path="login">Login:</form:label> <form:input path="login" />
			<form:label path="passwordHash">Password:</form:label> <form:input type="password" path="passwordHash" />
			<form:label path="email">Email:</form:label> <form:input path="email" />
			<input type="submit" value="Add user" />
		</form:form>
		</div>
	</security:authorize>
	
	<!-- One message -->
	<div id="show_one_message" class="centered">
		<table>
			<tr>
				<td>
					From:
				</td>
				<td>
					${oneMessage.fromuserlogin}
				</td>
			</tr>
			<tr>
				<td>
					Date:
				</td>
				<td>
					${oneMessage.date}
				</td>
			</tr>
			<tr>
				<td>
					Subject:
				</td>
				<td>
					${oneMessage.subject}
				</td>
			</tr>
			<tr>
				<td>
					${oneMessage.text}
				</td>
			</tr>
		</table>
	</div>
	
	<div id="send_one_message" class="centered">
		
	</div>
	
	<!-- Address book -->
	<div id="addressbook" class="centered  ">
		<c:if test="${!empty addressBookList}">
			<table id="ab_records" class="table">
				<tr>
					<th>Name:</th>
					<th>&nbsp;</th>
				</tr>
				<c:forEach items="${addressBookList}" var="addressBookItem">
					<tr>
						<td>${addressBookItem.itemuserlogin}</td>
						<!-- Link to send message -->
						<td><a href="removeAddressBookItem/${addressBookItem.itemId}">Send message</a></td>
						<td><a href="removeAddressBookItem/${addressBookItem.itemId}">Delete item</a></td>
					</tr>
				</c:forEach>
				<tr>
					<td>
						<a href="<c:url value="/listAddressBook" />">
							<button class="btn btn-default" type="button" onclick="$('#addressbook_add').show();">
								Add to address book
							</button>
						</a>
					</td>
				</tr>
			</table>
		</c:if>
	</div>
	<div id="addressbook_add" class="centered ">
		<c:forEach items="${usersList}" var="user">
						<tr>
							<td>${user.username}</td>
							<td>${user.login}</td>
							<td>${user.email}</td>
							<td><a href="addAddressBookItem/${user.userId}">Add</a></td>
						</tr>
					</c:forEach>
	</div>
</body>
</html>