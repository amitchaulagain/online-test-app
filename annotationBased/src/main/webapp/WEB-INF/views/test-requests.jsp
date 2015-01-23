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
<script type="text/javascript"
	src="<c:url value="/resources/js/custom/testrequest.js"/>"></script>
	<script type="text/javascript"
			src="<c:url value="/resources/js/library/jquery.simplePagiTestRequest.js"/>"></script>
			<script type="text/javascript"
			src="<c:url value="/resources/js/custom/testRequestDataTable.js"/>"></script>

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
	float:right;
}
</style>
<body onload="findAllTestRequests('PENDING')">


	<div class="body">

		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							${username} --> Select Tests <small>Here :</small>
						</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> Dashboard
							</li>
						</ol>
					</div>

				</div>

				<div class="col-lg-6">
					<h2>SHowing All Test Requests</h2>
					<div class=" col-md-2">
							<div class="form-group">
								<label for="sel1">Show:</label> <select
									class="form-control input-sm" id="show" style="height: 35px"
									style="margin: 0">
									<option>4</option>
									<option>6</option>
									<option>8</option>
									<option>20</option>
								</select>
							</div>
						</div>
					<div class="btn-group" data-toggle="buttons" style="float: right">
						<label style="margin-right: 25px"
							class="btn btn-success btn-xs  active "> <input
							type="radio" name="options" id="option1" checked status="PENDING">
							Pending

						</label> <label style="margin-right: 25px" class="btn btn-success btn-xs">
							<input type="radio" name="options" id="option2"
							status="COMPLETED"> Completed
						</label> <label class="btn btn-success btn-xs"> <input
							type="radio" name="options" id="option3" status="REJECTED">
							Rejected
						</label>
					</div>
					<br></br>
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead>
								<tr>
									<th>ID</th>
									<th>Test Name</th>
									<th>Test Initiated By</th>
									<th>Test Requested Date</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody id="showTest">

							</tbody>
						</table>
						<div id="pagination"></div>
					</div>

					<br></br>
					<div id="testRequestDetailPanel" class="col-ms-8">
						<div class="panel panel-green">
							<div class="panel-heading">
								<h3 class="panel-title">Test Request Detail</h3>
							</div>
							<div class="panel-body">
								<table id="testRequestContentPanel">

								</table>
								<div   class="form-group" style="margin: 10px">
									<div  class="buttonPanel" >
									<button id="confirm" type="submit" class="btn btn-primary btn-sm">Confirm</button>
									<button id="reject" type="submit" class="btn btn-primary btn-sm"
										style="margin-left: 20px">Reject</button>
									
									</div>

								</div>
							</div>
						</div>
					</div>

				</div>


			</div>


		</div>

	</div>
</body>
</html>
