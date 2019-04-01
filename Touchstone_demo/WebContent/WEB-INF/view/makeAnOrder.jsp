<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Make Order!</title>

    <style> 
        label {
            display: block;
            font: 1rem 'Fira Sans', sans-serif;
            }
        input,
        label {
            margin: .4rem 0;
        }
        .note {
            font-size: .8em;
        }
    </style>
</head>
<body>
	<form:form action="${pageContext.request.contextPath }/makeAnOrder" method="POST">
        Customer <input type="text" name="cus_name">
        <button type="submit">Order</button>
    </form:form>
    <p>Click on the Order button to submit the form.</p>
</body>
</html>