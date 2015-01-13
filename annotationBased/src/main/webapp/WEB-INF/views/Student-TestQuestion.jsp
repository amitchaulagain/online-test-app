<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link href="<c:url value="/resources/css/library/bootstrap.min.css" />" rel="stylesheet" />

	<title>Shop List page</title> <script type="text/javascript"
		src="//code.jquery.com/jquery-latest.js"></script>
</head>
<body>
	<h3>Student test</h3>
	<form :form role="form" action='../checkTest' method="post">

		<!-- 	<c:forEach var="question" items="${questionsList}">
			<h3 style="color: green;" name ="listOfTestQuestion" >${question.name}</h3>
			
			
			<c:forEach var="option" items="${question.options}">
			
				<input type="checkbox" name="listOfAnswers"
					value="${option.id}">${option.name}</input><br />

			</c:forEach>
			<jsp:useBean id="questionAnsMap" class="java.util.LinkedHashMap"/>
<c:set  target="${questionAnsMap}" property="${question.name}" value="${questionAnswer}" />
			

		</c:forEach>
 -->

		<c:forEach var="map" items="${questionAnsMap}">
			<h1 style="color: green">${map.key.id}${map.key.name}</h1>
			<c:forEach var="hero" items="${map.value}">
				<c:forEach var="option" items=" ${hero.name}">
						<c:forEach var="hhh" items=" ${hero.id}">

					 
					<input type="checkbox" name ="listOfAnswers"  value="${map.key.id}a${hhh}">${option}</input>
				</c:forEach>
					<br>
				</c:forEach>
			</c:forEach>
			<br />
			<br />
		</c:forEach>


		<button type="submit" class="btn btn-primary btn-sm">Submit</button>
	<form:form/>
	
</body>
<script type="text/javascript">


</script>


</html>