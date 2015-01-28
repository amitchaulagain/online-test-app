<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<link href="<c:url value='/resources/css/library/bootstrap.min.css'/>"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="<c:url value='/resources/css/custom/sb-admin.css'/>"
	rel="stylesheet">
<!-- Morris Charts CSS -->
<link href="<c:url value='/resources/css/library/plugins/morris.css'/>"
	rel="stylesheet">

<link
	href="<c:url value='/resources/font-awesome-4.1.0/css/font-awesome.min.css'/>"
	rel="stylesheet">
<link
	href="<c:url value="/resources/css/library/simplePagination.css" />"
	rel="stylesheet">
<script type="text/javascript"
	src="<c:url value="/resources/js/library/jquery.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/library/bootstrap.min.js"/>"></script>
<!-- <script type="text/javascript" -->
<%-- 	src="<c:url value="/resources/js/custom/testrequest.js"/>"></script> --%>
<!-- <script type="text/javascript" -->
<%-- 	src="<c:url value="/resources/js/library/jquery.simplePagiTestRequest.js"/>"></script> --%>
<!-- <script type="text/javascript" -->
<%-- 	src="<c:url value="/resources/js/custom/testRequestDataTable.js"/>"></script> --%>
<script type="text/javascript"
	src="<c:url value="/resources/js/custom/allTests.js"/>"></script>
	
	
<html>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link href="<c:url value="/resources/css/library/bootstrap.min.css" />"
	rel="stylesheet">
<script type="text/javascript"
	src="<c:url value="/resources/js/library/bootbox.js"/>"></script>
<head>

<title>Welcome ${username}</title>
</head>
<style>
.theKey {
	/* 	float: left; */
	margin-left: 30px;
	margin-right: 30px;
	color: green;
	margin-right: 30px;
}

.theValue {
	/* 	float: left; */
	margin-left: 30px;
	margin-right: 30px;
	color: black;
}

.buttonPanel {
	float: right;
}
</style>
<body onload="findAllTests()">


	<div class="body">

		<div id="page-wrapper">

			<div class="container-fluid">

				<div class="row">
					<div class="col-lg-8">
						<div class="buttonPanel">
							<button id="createTestButton" class="btn btn-primary btn-sm">Create
								Test</button>
						</div>
					</div>
					<div id="createTestPanel" class="col-lg-8">
						<br></br>
						<div id="testPanel" style="clear: both">
							<form role="form" action='../admin/createTest' method="post">
							<input type="hidden" id="id" name="id" />
								<div class="form-group ">
									<label for="name">Name</label> <input type="text" name="name" id="name"
										class="form-control input-sm" />
								</div>
								<div class="form-group ">
									<label for="fullmark">Full Mark</label> <input type="text"
										name="fullmark" class="form-control input-sm" id="fullmark"/>
								</div>
								<div class="form-group ">
									<label for="passmark">Pass Mark</label> <input type="text"
										name="passmark" class="form-control input-sm" id="passmark" />
								</div>
								<div class="form-group ">
									<label for="testDate">Test Date</label> <input type="text"
										name="testDate" class="form-control input-sm" id="testDate" />
								</div>
								<div class="form-group ">
									<label for="testDuration">Test Duration</label> <input type="text"
										name="duration" class="form-control input-sm" id="duration" />
								</div>
								 
								<div class="form-group">
									<button type="submit" class="btn btn-primary btn-sm" id="submit" onclick="createOrEditTest()">Submit</button>

								</div>
							</form>
						</div>
					</div>
				</div>
				<div id="testTablePanel" class="row">
					<div class="col-lg-8">
						<h2>SHowing All Tests</h2>
						<div class="row">
							<div class="col-lg-2">
								<div class="form-group">
									<label for="sel1">Show:</label> <select
										class="form-control input-sm" id="show" style="height: 35px">
										<option>4</option>
										<option>6</option>
										<option>8</option>
										<option>20</option>
									</select>
								</div>
							</div>
						</div>
						<div class="table-responsive">
							<table class="table table-bordered table-hover table-striped">
								<thead>
									<tr>
										<th>Test ID</th>
										<th>Test Name</th>
										<th>Full Mark</th>
										<th>Pass Mark</th>
										<th>Test Date</th>
										<th>Test Duration</th>
										<th>Test Type</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody id="showTest">

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
