<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TouchStone Demo</title>
</head>
<body>
	<h1>TouchStone Demo</h1>
	<div>
		<p>
		ID <div id="user_id">${user_id }</div>
		</p>
		<p>
		Roles <div class="user_role"> ${user_role } </div>
		<p>
	</div>
	<form:form action="${pageContext.request.contextPath }/logout"
				method="POST">
		<input type="submit" value="Logout" />
	</form:form>
	
	<table>
		<tr>
			<th>Order No</th>
			<th>Process</th>
			<th>Customer</th>
			<th>Order Date</th>
			<th>Confirm</th>
		</tr>
		
		<c:forEach var="order" items="${orders}">
		<tr>
			<td>${order.order_id}</td>
			<td>${order.order_process}</td>
			<td>${order.order_cus}</td>
			<td>${order.order_date}</td>
			<td>
				<form:form action="${pageContext.request.contextPath }/updateOrder" method="POST">
					<input type="hidden" value="${order.order_id}" name="order_id"/>
					<button type="submit" class="orderConfirm">Confirm</button>
				</form:form>
			</td>
		</tr>
		</c:forEach>
		
	</table>

<script>
 let user_id = "${user_id }"
 let user_roles = "${user_roles}"
</script>
</body>
</html>