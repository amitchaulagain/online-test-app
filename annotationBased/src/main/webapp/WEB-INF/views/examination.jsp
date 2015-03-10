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
	src="<c:url value="/resources/js/custom/examination.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/custom/seatPlanning.js"/>"></script>

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
					class="fa fa-desktop"></i> Create Examination</li>
			</ol>
		</div>
	</div>
	<div id="page-wrapper">
		<div id="examinationContainer">

			<div class="container-fluid" style="margin-top: 35px">
				<div class="row">
					<div class="col-lg-12">
						<div style="float: right">
							<button id="createExamination" class="btn btn-primary btn-sm">Create</button>
						</div>

					</div>
				</div>
				<div class="row" id="examinationPanel" class="col-lg-8"
					style="display: none">
					<div>
						<input type="hidden" id="examId" value="0"
							class="form-control input-sm" />
						<div class="col-md-4">
							<div class="form-group ">
								<label for="firstName" style="color: #777">Examination
									Name</label> <input type="text" id="name" class="form-control input-sm" />
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group ">
								<label for="examinationTime" style="color: #777">Examination
									Time</label> <input type="text" id="time" class="form-control input-sm" />
							</div>
						</div>
						<div class="col-md-4">
							<label for="assignTest" style="color: #777">Assign Test </label>
							<select id="assignTest" class="form-control">
							</select>
						</div>
						<div class="row"></div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group" style="margin: 15px">
									<button type="submit" class="btn btn-primary btn-sm"
										id="submit">Submit</button>
								</div>
							</div>
						</div>
						<div id="showAssignedGroup" style="clear: both"></div>
					</div>
				</div>
			</div>
			<div id="allGroups" style="display: none">
				<div class="row">
					<h2>SHowing All Groups</h2>
					<div class="col-lg-2">
						<div class="form-group">
							<label for="sel1">Show:</label> <select
								class="form-control input-sm" id="showAllGroupsPerPage"
								style="height: 35px" style="margin: 0">
								<option>3</option>
								<option>4</option>
								<option>5</option>
								<option>6</option>
							</select>
						</div>

					</div>
					<div class="col-lg-4" style="float: right">
						<div class="form-group ">
							<label for="name" style="color: #777"></label> <input type="text"
								id="search" class="form-control input-sm" />
						</div>
					</div>
				</div>
				<div class="col-lg-12">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th width="100px">ID</th>

								<th width="100px">Name</th>
							</tr>
						</thead>
						<tbody id="showGroup">

						</tbody>
					</table>

				</div>

				<div class="row">
					<div class="col-md-5">
						<div id="allGroupsPagination"></div>
					</div>
				</div>
			</div>

			<h2>SHowing All Examination</h2>

			<div class="row">
				<div class="col-lg-2">
					<div class="form-group">
						<label for="sel1">Show:</label> <select
							class="form-control input-sm" id="showExaminationPerPage"
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
							<th width="20px">Exam ID</th>
							<th width="100px">Exam Name</th>
							<th width="100px">Examination Time</th>
							<th width="100px">Assigned Group</th>
							<th width="100px">Assigned Test</th>
							<th width="100px">Assign Exam</th>

							<th width="100px">Action</th>
						</tr>
					</thead>
					<tbody id="showExamination">

					</tbody>
				</table>
			</div>

			<div class="row">
				<div class="col-md-5">
					<div id="examinationPagination"></div>
				</div>
			</div>


			<div id="assignedGroups" style="display: none"></div>
		</div>
		<div id="seatPlanningContainer" style="display: none">
			<div class="row">
				<div class="col-lg-2">
						<div class="form-group">
							<label for="sel1">Show:</label> <select
								class="form-control input-sm" id="showStudentsSeatPerPage"
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
								<th width="100px">Date-Of-Birth</th>
								<th width="100px">Seat Number</th>
								
								<th width="200px">Set Assigned</th>
							</tr>
						</thead>
						<tbody id="showExamSeat">

						</tbody>
					</table>
				</div>

				<div class="row">
					<div class="col-md-5">
						<div id="examSeatPagination"></div>
					</div>
				</div>


		</div>
	</div>
</body>
</html>
