<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<link href="<c:url value='/resources/css/library/bootstrap.min.css'/>"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="<c:url value='/resources/css/custom/agency.css'/>"
	rel="stylesheet">
<!-- Morris Charts CSS -->
<link href="<c:url value='/resources/css/library/plugins/morris.css'/>"
	rel="stylesheet">

<link
	href="<c:url value='/resources/font-awesome-4.1.0/css/font-awesome.min.css'/>"
	rel="stylesheet">
<link
	href="<c:url value="/resources/css/library/jquery.bs_pagination.css" />"
	rel="stylesheet">
	<link href="<c:url value="/resources/css/library/jquery.bs_pagination.bs2.css" />"rel="stylesheet">
	 
<script type="text/javascript"
	src="<c:url value="/resources/js/library/jquery.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/library/jquery-ui.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/library/bootstrap.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/library/jquery.simplePagination.js"/>"></script>
	<script type="text/javascript"
	src="<c:url value="/resources/js/custom/allStudents.js"/>"></script>
	
	<link href="<c:url value="/resources/css/library/simplePagination.css" />"
	rel="stylesheet">

<html>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<head>

<title>Welcome ${username}</title>
</head>

<body >

	<div class="row">
		<div class="col-lg-12">
			<ol class="breadcrumb">
				<li><i class="fa fa-dashboard"></i> <a
					href="../admin/dashboard" style="color: white;">Dashboard</a></li>
				<li class="active" style="color: white;"><i
					class="fa fa-desktop"></i> Create Student</li>
			</ol>
		</div>
	</div>
	<div >

		<div id="page-wrapper">

			<div class="container-fluid">
					<h2>Showing All Students</h2>
					<div class="row">
						<div class="col-lg-2">
							<div class="form-group">
								<label for="sel1">Show:</label> <select
									class="form-control input-sm" id="showStudentPerPage" style="height: 35px"
									style="margin: 0">
									<option>3</option>
									<option>4</option>
									<option>5</option>
									<option>6</option>
								</select>
							</div>


						</div>
					</div>
					<div class="">
						<table class="table table-bordered table-hover table-striped">
							<thead>
								<tr>
									<th width="100px">Full Name</th>
									<th width="100px">Username</th>
									<th width="100px">Password</th>
									<th width="100px">Email</th>
									<th width="100px">Phone Number</th>
									<th width="100px">Address</th>
									<th width="100px">Country</th>
									<th width="100px">Date-Of-Birth</th>
									<th width="100px">Action</th>
								</tr>
							</thead>
							<tbody id="showStudent">

							</tbody>
						</table>
					</div>

					<div class="row">
						<div class="col-md-5">
							<div id="studentPagination"></div>
						</div>
					</div>

				</div>
			</div>
		</div>
</body>
</html>
