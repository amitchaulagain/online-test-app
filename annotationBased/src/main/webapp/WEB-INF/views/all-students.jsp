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
<link
	href="<c:url value="/resources/css/library/jquery.bs_pagination.bs2.css" />"
	rel="stylesheet">

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

<link
	href="<c:url value="/resources/css/library/simplePagination.css" />"
	rel="stylesheet">

<html>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<head>

<title>Welcome ${username}</title>
</head>

<body>

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
	<div>

		<div id="page-wrapper">


			<div class="container-fluid">
				<h2>Showing All Students</h2>
				<div class="row">

					<div class="col-lg-12">
						<div style="float: right">
							<button id="createStudent" class="btn btn-primary btn-sm">Create</button>
						</div>
					
					</div>
				</div>
				<div class="row" id="studentPanel" class="col-lg-8" style="display: none">
					<div >
					<input type="hidden" id="studentId" class="form-control input-sm" />
						<div class="col-md-4">
								<div class="form-group ">
									<label for="firstName" style="color: #777">First Name</label> <input
										type="text" id="firstName" class="form-control input-sm" />
								</div>
						</div>
						<div class="col-md-4">
							<div class="form-group ">
								<label for="middleName" style="color: #777">Middle Name</label>
								<input type="text" id="middleName" class="form-control input-sm" />
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="lastName" style="color: #777">Last Name</label> <input
									type="text" id="lastName" class="form-control input-sm" />
							</div>
						</div>
						<div class="col-md-4">
							<div>
								<div class="form-group ">
									<label for="email" style="color: #777">Email</label> <input
										type="text" id="email" class="form-control input-sm" />
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group ">
								<label for="phoneNumber" style="color: #777">Phone
									Number</label> <input type="text" id="phoneNumber"
									class="form-control input-sm" />
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="dateOfBirth" style="color: #777">Date Of Birth</label> <input
									type="text" id="dateOfBirth" class="form-control input-sm" />
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="address" style="color: #777">Address</label> <input
									type="text" id="address" class="form-control input-sm" />
							</div>
						</div>
						 
						<div class="col-md-4">
							<div class="form-group hero-unit">
								<label for="country" style="color: #777">Country</label> <input
									type="text" id="country" class="form-control input-sm" />
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group hero-unit">
								<label for="examDate" style="color: #777">Exam Date</label> <input
									type="text" id="examDate" placeholder="DD/MM/yy"
									class="form-control input-sm" />
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<button type="submit" class="btn btn-primary btn-sm" id="submit">Submit</button>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
				<div class="col-lg-2">
						<div class="form-group">
							<label for="sel1">Show:</label> <select
								class="form-control input-sm" id="showStudentPerPage"
								style="height: 35px" style="margin: 0">
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
								 
								<th width="100px">Email</th>
								<th width="100px">Phone Number</th>
								<th width="100px">Address</th>
								<th width="100px">Country</th>
								<th width="100px">Date-Of-Birth</th>
								<th width="200px">Action</th>
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
