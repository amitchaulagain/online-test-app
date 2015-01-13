<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link href="<c:url value="/resources/css/library/bootstrap.min.css" />"/>
	<title>Shop List page</title>
</head>
<body>
<button onclick="myFunction()">Try it</button>

<p id="demo"></p>

<script>

</script>

	<div class="container">
		<h1>Create Test Page</h1>
		<div class="form-group col-md-6" id="questionsToSet">
		


		<table class="table table-striped table-bordered">

			<thead>
				<tr>
					<th width="10px">${q.name}</th>
					<th width="10px">${q.id}</th>
				</tr>
			</thead>


			<tbody>
				<c:forEach var="tests" items="${o}">

					<tr>

						<td>${tests.name} </td>
						<td>${tests.id} </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	
	
<div class="form-group col-md-4" id="questionsToSet"><table class="table table-striped table-bordered">

			<thead>
				<tr>
					<th width="10px">answer</th>
				</tr>
			</thead>


			<tbody>
				<c:forEach var="tests" items="${a}">

					<tr>

						<td>${tests} </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
<button onclick="myFunction()" class="btn btn-primary btn-sm">Try it</button>

</body>
</html>