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

<script type="text/javascript"
	src="<c:url value="/resources/js/library/jquery.simplePagination.js"/>"></script>
	
	<script type="text/javascript"
	src="<c:url value="/resources/js/library/bootbox.js"/>"></script>
	
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	 -->
<html>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
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

#chooseTestOption {
	display: none;
}
</style>
<body onload="findAllTests()">


	<div class="body">

		<div id="page-wrapper">

			<div class="container-fluid" id="testPage">
				<div class="row">
					<div class="col-lg-8">
						<div class="buttonPanel">
							<button id="createTestButton" class="btn btn-primary btn-sm">Create
							</button>
						</div>
					</div>
					<br></br>
					<div class="col-md-12">

						<div id="chooseTestOption">
							<div class="col-lg-3 col-md-6" id="testTypeOne">
								<div class="testTypes" id="testTypeOne">
									<a href="#">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<div class="row">
													<div class="col-xs-3">
														<i class="fa fa-th-list fa-5x"></i>
													</div>
													<div class="col-xs-9 text-right">
														<div style="margin-left: 20px">Test Without SET And
															SECTION</div>
													</div>
												</div>
											</div>

											<div class="panel-footer">
												<span class="pull-left">create</span> <span
													class="pull-right"><i
													class="fa fa-arrow-circle-right"></i></span>
												<div class="clearfix"></div>
											</div>

										</div>
									</a>
								</div>
							</div>
							<div class="col-lg-3 col-md-6">
								<div class="testTypes" id="testTypeTwo">
									<a href="#">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<div class="row">
													<div class="col-xs-3">
														<i class="fa fa-th-list fa-5x"></i>
													</div>
													<div class="col-xs-9 text-right">
														<div style="margin-left: 20px">Test With SETS Only</div>
													</div>
												</div>
											</div>

											<div class="panel-footer">
												<span class="pull-left">create</span> <span
													class="pull-right"><i
													class="fa fa-arrow-circle-right"></i></span>
												<div class="clearfix"></div>
											</div>

										</div>
									</a>
								</div>
							</div>
							<div class="col-lg-3 col-md-6">
								<div class="testTypes" id="testTypeThree">
									<a href="#">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<div class="row">
													<div class="col-xs-3">
														<i class="fa fa-th-list fa-5x"></i>
													</div>
													<div class="col-xs-9 text-right">
														<div style="margin-left: 20px">Test With SECTIONS
															Only</div>
													</div>
												</div>
											</div>

											<div class="panel-footer">
												<span class="pull-left">create</span> <span
													class="pull-right"><i
													class="fa fa-arrow-circle-right"></i></span>
												<div class="clearfix"></div>
											</div>

										</div>
									</a>
								</div>
							</div>
							<div class="col-lg-3 col-md-6">
								<div class="testTypes" id="testTypeFour">
									<a href="#">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<div class="row">
													<div class="col-xs-3">
														<i class="fa fa-th-list fa-5x"></i>
													</div>
													<div class="col-xs-9 text-right">
														<div style="margin-left: 20px">Test With Both SETS
															And SECTIONS</div>
													</div>
												</div>
											</div>

											<div class="panel-footer">
												<span class="pull-left">create</span> <span
													class="pull-right"><i
													class="fa fa-arrow-circle-right"></i></span>
												<div class="clearfix"></div>
											</div>

										</div>
									</a>
								</div>
							</div>


						</div>

						<div class="row">

							<div id="createTestPanel" class="col-lg-8">
								<br></br>
								<div id="testPanel" style="clear: both">
									<input type="hidden" id="id" name="id" />
									<div class="form-group ">
										<label for="name">Name</label> <input type="text" name="name"
											id="name" class="form-control input-sm" />
									</div>
									<div class="form-group ">
										<label for="fullmark">Full Mark</label> <input type="text"
											name="fullmark" class="form-control input-sm" id="fullmark" />
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
										<label for="testDuration">Test Duration</label> <input
											type="text" name="duration" class="form-control input-sm"
											id="duration" />
									</div>

									<div class="form-group">
										<button type="submit" class="btn btn-primary btn-sm"
											id="submit" onclick="createOrEditTest()">Submit</button>

									</div>
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
												class="form-control input-sm" id="show" style="height: 35px"
												style="margin: 0">
												<option>3</option>
												<option>4</option>
												<option>5</option>
												<option>6</option>
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
								<div class="row">
									<div class="col-md-5">
										<div id="pagination"></div>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div id="hero"></div>
				</div>
			</div>
</body>
</html>
