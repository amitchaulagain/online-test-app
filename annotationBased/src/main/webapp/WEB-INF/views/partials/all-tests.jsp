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
	<link
	href="<c:url value="/resources/css/library/simplePagination.css" />"
	rel="stylesheet">
	<link href="<c:url value='/resources/css/library/plugins/datepicker.css'/>"
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
	src="<c:url value="/resources/js/library/plugins/datepicker.js"/>"></script>
	<script type="text/javascript"
	src="<c:url value="/resources/js/library/bootstrap-timepicker.js"/>"></script>
	<script type="text/javascript"  	src="<c:url value="/resources/js/library/jquery.bs_pagination.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/custom/allTests.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/custom/test.js"/>"></script>
	<script type="text/javascript"
	src="<c:url value="/resources/js/custom/question.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/custom/animate.js"/>"></script>

<html>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<head>

<title>Welcome ${username}</title>
</head>

<body onload="findAllTests()">

	<div class="row">
		<div class="col-lg-12">
			<ol class="breadcrumb">
				<li><i class="fa fa-dashboard"></i> <a
					href="../admin/dashboard" style="color: white;">Dashboard</a></li>
				<li class="active" style="color: white;"><i
					class="fa fa-desktop"></i> Create Test</li>
			</ol>
		</div>
	</div>
	<div class="body" style="margin: 0px 46px 0px 45px;">

		<div id="page-wrapper">

			<div class="container-fluid" id="testPage">
				<div class="row">

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
							<div class="col-md-6" id="createTestPanel" style="height: 40%">
								<!-- PANEL FOR FORMS AND TABS TO CREATE TEST -->
							</div>
							
							
							
							
							
							
<div class="col-md-3" id="createDOption">
			<div class="box box-primary">
				<div class="box-header">
					<h4 class="box-title">Create Form Parameter</h4>
					<i id="closeDoption"  class="fa fa-times"><a href="#"></i></a>
				</div>
				

					
					<div class="box-body">
						<!-- the events -->
						<div  class="row" id="external-events">
							<span class="label">Default</span> <span
								class="label label-success">Success</span>
						</div>
					</div>
					<!-- /.box-body -->
				
				<!-- /. box -->
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title">Create Form Field</h3>
					</div>
					<div id="field-holder" class="box-body">

						<div class="input-group">
							<input id="new-event" type="text" class="form-control"
								placeholder="Event Title">
							<div class="input-group-btn">
								<button id="new-field" type="button"
									class="btn btn-default btn-flat">Add</button>
							</div>
							<!-- /btn-group -->
						</div>
						<!-- /input-group -->
					</div>
				</div>
			</div>



		</div>
							<div class="col-md-6" id="questions">
								<!-- 	<div id="allQuestionContianer"></div> -->
								<div  id=loadQuestion style="margin-right: 32px;">
									<h1>Choose questions</h1>
									<div class="row">
										<div class="col-lg-2">
											<div class="form-group">
												<label for="sel1">Show:</label> <select
													class="form-control input-sm" id="showQuestion"
													style="height: 35px" style="margin: 0">
													<option>5</option>
													<option>8</option>
													<option>10</option>
													<option>14</option>
												</select>
											</div>


										</div>
									</div>
									<div id="allQuestionContianer"></div>

								</div>
								<div id="questionPagination"></div>




							</div>
						</div>
					</div>
				</div>
				<div id="testTablePanel" class="row"></div>
				<div class="cc" style="margin-top: 15%;">
					<h2>SHowing All Tests</h2>
					<div class="row">
						<div class="col-lg-2">
							<div class="form-group">
								<label for="sel1">Show:</label> <select
									class="form-control input-sm" id="showtestperpage" style="height: 35px"
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
									<th width="20px">Test ID</th>
									<th width="100px">Test Name</th>
									<th width="50px">Full Mark</th>
									<th width="50px">Pass Mark</th>
									<th width="50px">Test Date</th>
									<th width="50px">Test Duration</th>

									<th width="100px">Action</th>
								</tr>
							</thead>
							<tbody id="showTest">

							</tbody>
						</table>
					</div>

					<div class="row">
						<div class="col-md-5">
							<div id="testPagination"></div>
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
	</div>
</body>
</html>
