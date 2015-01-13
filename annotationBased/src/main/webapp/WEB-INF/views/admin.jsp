<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
<head>

<link href="<c:url value="/resources/css/library/dataTables.bootstrap.css"/>"
	rel="stylesheet" type="text/css" />

<link href="<c:url value="/resources/css/library/bootstrap.min.css" />" rel="stylesheet">
	
<link href="<c:url value="/resources/css/custom/agency.css" />"
	rel="stylesheet">
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>  -->
<script type="text/javascript"
	src="<c:url value="/resources/js/library/jquery.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/library/bootstrap.min.js"/>"></script>

<!-- DATA TABES SCRIPT -->
<script type="text/javascript"
	src="<c:url value="/resources/js/custom/question.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/library/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/resources/js/library/dataTables.bootstrap.js"/>"
	type="text/javascript"></script>

</head>
<body onload="findAllQuestions()">


	<div>
		<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header page-scroll">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand page-scroll" href="#page-top">Experimental
				</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="hidden"><a href="#page-top"></a></li>
					<li><a class="page-scroll" href="#services">Services</a></li>
					<li><a class="page-scroll" href="#portfolio">Portfolio</a></li>
					<li><a class="page-scroll" href="#about">About</a></li>
					<li><a class="page-scroll" href="#team">Team</a></li>
					<li><a class="page-scroll" href="#contact">Contact</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>

		<!-- /.container-fluid --> </nav>

		<div class="row contianer-fluid  ">
			<div class=" col-md-2 side-bar">

				<div class="panel-group" id="accordion" role="tablist"
					aria-multiselectable="true">
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingOne">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseOne" aria-expanded="true"
									aria-controls="collapseOne"> academics </a>
							</h4>
						</div>
						<div id="collapseOne" class="panel-collapse collapse "
							role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">
								<ul class="list-group">
									<li class="list-group-item" id="lg1"><a
										class="page-scroll" href="#content" id="show-question-console">Insert
											Question</a></li>
									<li class="list-group-item"><a class="page-scroll"
										href="#question-table" id="show-allquestion-table">show Question</a></li>
									<li class="list-group-item">Morbi leo risus</li>
									<li class="list-group-item">Porta ac consectetur ac</li>
									<li class="list-group-item">Vestibulum at eros</li>
								</ul>

							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingTwo">
							<h4 class="panel-title">
								<a class="collapsed" data-toggle="collapse"
									data-parent="#accordion" href="#collapseTwo"
									aria-expanded="false" aria-controls="collapseTwo">
									Collapsible Group Item #2 </a>
							</h4>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingTwo">
							<div class="panel-body">Anim pariatur cliche reprehenderit,
								
								sustainable VHS.</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="headingThree">
							<h4 class="panel-title">
								<a class="collapsed" data-toggle="collapse"
									data-parent="#accordion" href="#collapseThree"
									aria-expanded="false" aria-controls="collapseThree">
									Collapsible Group Item #3 </a>
							</h4>
						</div>
						<div id="collapseThree" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingThree">
							<div class="panel-body">Anim pariatur cliche reprehenderit,
								enim eiusmod high life accusamus terry richardson ad squid. 3
								
								sustainable VHS.</div>
						</div>
					</div>
				</div>

			</div>
			<div class=" col-md-6 side-bar">
				<form role="form" method="post" action='../admin/setQuestion'>
					<div id="content">
						<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
							<c:forEach var="type" items="${allQuestionTypes}">
								<li><a href="#${type}" data-toggle="tab">${type}</a></li>
							</c:forEach>


						</ul>
						<div id="my-tab-content" class="tab-content">
							<div class="tab-pane active" id="red">
								<h1>Red</h1>
								<p>red red red red red red</p>
							</div>
							<div class="tab-pane" id="CHECKBOX_TYPE">
								<h1>Orange</h1>
								<p>orange orange orange orange orange</p>
							</div>
							<div class="tab-pane" id="RADIO_TYPE">
								<div class="row">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h3 class="panel-title">Insert Question</h3>
										</div>
										<div class="panel-body">
											<ul class="list-group">

												<li class="list-group-item">
													<div class="input-group">
														<span class="input-group-addon" id="question-txt"
															name='name'>question</span> <input type="text"
															class="form-control" placeholder="Type Question Here">
													</div>
												</li>
											</ul>
											<div class="row" id="option-box">
												<ul class="list-group">

													<li class="list-group-item">
														<div class=" col-md-2">Option:1</div>
														<div class=" col-md-8">
															<div class="input-group">
																<span class="input-group-addon"> <input
																	type="radio" aria-label="sss">
																</span> <input type="text" class="form-control"
																	aria-label="...">
															</div>
														</div>
														<div class=" col-md-2">
															<button type="button" class="btn btn-default"
																id="add-option">Add More</button>
														</div>
													</li>
												</ul>
											</div>
										</div>
									</div>


								</div>
							</div>
							<div class="tab-pane" id="INPUT_TEXT_TYPE">
								<h1>Green</h1>
								<p>green green green green green</p>
							</div>

						</div>
					</div>
					<div class="box" id="question-table">
						<div class="box-header">
							<h3 class="box-title">Data Table With Full Features</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body table-responsive">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>ID</th>
										<th>Name</th>

									</tr>
								</thead>
								<tbody>
								<c:forEach var="item" items="${questionsForTest}">
								<tr>
								
								<td>${item.id}</td>
								<td>${item.name}</td>
								
								</tr>
								</c:forEach>
								</tbody>
								
								<tfoot>
									<tr>
										<th>ID</th>
										<th>Name</th>
									</tr>
								</tfoot>
							</table>
						</div>
						<!-- /.box-body -->
					</div>
	</form>
					<script type="text/javascript">
						
					</script>
			</div>
		
		</div>
		<div class=" col-md-4">

			<!-- /.box -->
		</div>
	</div>

	<!-- jQuery 2.0.2 -->
	<!-- Bootstrap -->

	<!-- AdminLTE App -->
	<!-- AdminLTE for demo purposes -->
	<!-- page script -->
	<script type="text/javascript">
	
	</script>
	


	</div>

	<script type="text/javascript"
		src="<c:url value="/resources/js/custom/animate.js"/>"></script>

</body>

</html>

