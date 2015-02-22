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
	src="<c:url value="/resources/js/custom/examination.js"/>"></script>
	
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
					class="fa fa-desktop"></i> Create Examination</li>
			</ol>
		</div>
	</div>
	<div >

		<div id="page-wrapper">

			<div class="container-fluid">
					<h2>SHowing All Examination</h2>
					<div class="row">
						<div class="col-lg-2">
							<div class="form-group">
								<label for="sel1">Show:</label> <select
									class="form-control input-sm" id="showExaminationPerPage" style="height: 35px"
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
									<th width="20px">Exam ID</th>
									<th width="100px">Exam Name</th>
									<th width="100px">Examination Time</th>
									<th width="100px">Assigned Group</th>
									<th width="100px">Assigned Test</th>
									
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

				</div>
			</div>
		</div>
</body>
</html>
