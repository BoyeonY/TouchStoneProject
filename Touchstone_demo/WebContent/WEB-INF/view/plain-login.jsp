<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Log In : TouchStone</title>
</head>
<body>
	<div>		
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">			
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Sign In</div>
				</div>
				<div style="padding-top: 30px" class="panel-body">
					<form:form action="${pageContext.request.contextPath }/authenticateTheUser"	method="POST" class="form-horizontal">
						<div class="form-group">
					        <div class="col-xs-15">
					            <div>
									<c:if test="${param.error != null }">
										<div class="alert alert-danger col-xs-offset-1 col-xs-10">
											Sorry! You entered invalid ID/Password
										</div>
									</c:if>
									<c:if test="${param.logout != null }">
										<div class="alert alert-danger col-xs-offset-1 col-xs-10">
										Sorry! You have been logged out.
										</div>
									</c:if>
								</div>
							</div>
						</div>
						<div style="margin-bottom:25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							<input type="text" name="username" class="form-control" id="userNameInput" placeholder="user id" />
						</div>
						<div style="margin-bottom:25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
							<input type="password" name="password" class="form-control" id="passwordInput" placeholder="password" />
						</div>	
						<button type="submit" class="btn btn-success" value="Login" style="width:100%">Login</button>
						
					</form:form>
				</div>
				<div class="panel-body">
					<form:form action="${pageContext.request.contextPath }/makeAnOrder"	method="GET" class="form-horizontal">
						<!-- <input type="hidden" name="username" id="customerUserName" style="display: none" />
						<input type="hidden" name="password" id="customerPassword" style="display: none" /> -->		
						<div>	
							<button type="submit" class="btn btn-info" id="customerLogin" value="Login" style="width:100%">Customer/Order</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	</body>
</html>