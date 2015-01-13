<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link href="<c:url value="/resources/css/library/bootstrap.min.css" />" rel="stylesheet" />
<title>update Test page</title>

</head>
<body>
	
<h1>Update Test page</h1>

	<div class="contianer">
	
	
		<div class="row">
		
		<div id="questionsInTest" class="form-group col-md-2">
		
		</div>
          <h2>.  Set Question For "${message}" Test</h2>
			<div class="form-group col-md-6" id="questionsToSet">


				<form role="form" action='../updateTest/${testId}' method="post">
					

					<table class="table table-striped table-bordered">

						<thead>
							<tr>
								<th width="25px">ID</th>
								<th width="250px">Question</th>
								<th width="10px">Marks</th>
								<th width="50px">Set</th>
							</tr>
						</thead>


						<tbody>
							<c:forEach var="question" items="${listOfQuestions}">



								<tr>
									<td>${question.id}</td>
									<td><a href="../viewOptNAnsOf/${question.id}" >${question.name}</a></td>
									<td>
											<input type="text" class="form-control input-sm" />
										</td>
									<td><input type="checkbox" id="id"
										name="listOfTestQuestion" value="${question.id}"></td>

								</tr>
							</c:forEach>
						</tbody>
					</table>




					<p>
						<button type="submit" class="btn btn-primary btn-lg">Submit</button>
					</p>
				</form>
			</div>
			<div class="form-group col-md-2" > </div>
				
			<div id="questionsInTest" class="form-group col-md-3">
			 <h2>question in test ${message}</h2>
			
				<table class="table table-striped table-bordered">

					<thead>
						<tr>
							<th width="25px">ID</th>
							<th width="150px">Question in test ${message}</th>

						</tr>
					</thead>


					<tbody>
						<c:forEach var="question" items="${questionsintest}">



							<tr>
								<td>${question.id}</td>
								<td>${question.name}</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	
</body>
</html>