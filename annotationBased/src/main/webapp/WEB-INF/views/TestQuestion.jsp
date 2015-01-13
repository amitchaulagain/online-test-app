<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link href="<c:url value="/resources/css/library/bootstrap.min.css" />" rel="stylesheet"/>

	<title>Shop List page</title>
</head>
<body>
<h1> Test : ${t.name}</h1>

	

		<div width="contialer">

			<table class="table table-striped table-bordered">

				<thead>
					<tr>
						<th width="25px">ID</th>
						<th width="250px">Name</th>
					</tr>
				</thead>


				<tbody>
					<c:forEach var="question" items="${questionsInTestToSelect}">
					


						<tr>
							
							<td><div>
									<a href="../viewOptNAnsOf/${question.id}" >${question.id}</a>
								</div></td>
							<td>${question.name}</td>

						</tr>
						
					</c:forEach>
				</tbody>
			</table>
		</div>

	
	
</body>
</html>