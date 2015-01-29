<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<tiles:insertDefinition name="defaultTemplate">

	<tiles:putAttribute name="body">




		<div class="body">
			<script type="text/javascript"
				src="<c:url value="/resources/js/library/jquery.simplePagination.js"/>"></script>

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

			<div class="container" style="margin-right: -22; margin-left: -22;">


				<div class="row" style="margin-right: -22; margin-left: -22;">
					<h1>Create Test Page</h1>

					<div class="col-md-12">


						<div class="row">
							<div class="col-md-12">
								<div id="chooseTestOption">
									<div class="col-lg-3 col-md-6" id="testTypeOne">
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
									<div class="col-lg-3 col-md-6" id="testTypeTwo">
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
									<div class="col-lg-3 col-md-6" id="testTypeThree">
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
									<div class="col-lg-3 col-md-6" id="testTypeFour">
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
						</div>
						<div class="row" style="margin-right: -22; margin-left: -22;">
							<div id="formToCreateTest" class="col-md-6">

								<form role="form"   method="post">
									<div id="testOneInfo">
										<div class="form-group ">
											<label for="name">Name</label> <input type="text" name="name"
												class="form-control input-sm" id="testname" style="width:" />
										</div>
										<div class="row">
											<div class="col-md-4">
												<div class="form-group ">
													<label for="fullmark">Full Mark</label> <input type="text"
														id="fullmark" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group ">
													<label for="passmark">Pass Mark</label> <input type="text"
														id="passmark" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group hero-unit">
													<label for="testDate">Test Date</label> <input type="text"
														id="testDate" placeholder="DD/MM/yy"
														class="form-control input-sm" />
												</div>
											</div>
										</div>

									</div>

									<div class="form-group">


										<div class="row" id="updateInput">
											<div class="col-md-4">
												<div class="form-group ">
													<label for="passmark">TEST-SET</label> <input type="text"
														id="testset" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group ">
													<label for="passmark">SECTION</label> <input type="text"
														id="section" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group ">
													<label for="passmark">SOMETHING HERE</label> <input
														type="text" id="asd" class="form-control input-sm" />
												</div>
											</div>



										</div>
										<div class="form-group ">

											<p>
												Negative Marking <input type="checkbox" name="equalMarking"
													value="true">
											</p>
											<a id="addQuestionInTest" href="#addQuestionInTest">Add
												Questions </a>
										</div>
										<button type="submit" class="btn btn-primary btn-sm"
											onclick="createTest()">Submit</button>
									</div>
								</form>

							</div>
							<div class="col-md-1" style="margin-top: -68px; height: 100%;"></div>
							<div class="col-md-5" style="margin-top: -68px;">
								<section>
								<div id="selectedQuestion" style="position: fixed;">
									<h1>question in test</h1>
									<button type="submit"
										class="update-Test-Btn btn btn-primary btn-sm " id="">update</button>

								</div>
								</section>
							</div>
						</div>
					</div>
					</section>
					<div class="container" style="margin-right: 0; margin-left: 0;">
						<div class="row">
							<section>
							<div class="col-md-6">
								<div id="questionsInTest"></div>
								<div class="panel-group" id="panelContainer"></div>
							</section>
							<section>
							<div class="col-md-6"></div>
							</section>
						</div>
					</div>
					<section>
					<div class="col-md-6">
						<div id="questionsInTest"></div>
						<div class="panel-group" id="panelContainer"></div>
					</section>

				</div>

			</div>
		</div>
		<div class="container" style="margin-right: 0; margin-left: 0;">

			<div class="row">
				<div class="col-md-12">
					<section>
					<div class="col-md-12">

						<div class="row">
							<div class="col-md-12">
								<div class="panel-group" id="accordion" role="tablist"
									aria-multiselectable="true">
									<h1>EXISTING Test</h1>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label for="sel1">Show:</label> <select
													class="form-control input-sm" id="show"
													style="height: 35px" style="margin: 0">
													<option>3</option>
													<option>4</option>
													<option>5</option>
													<option>6</option>
												</select>
											</div>


										</div>
									</div>


								</div>

							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="row">
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


					<div class="row">
						<div class="col-md-5">
							<div id="pagination"></div>
						</div>
					</div>
				</div>
				</section>
			</div>

			<script type="text/javascript"
				src="<c:url value="/resources/js/custom/test.js"/>"></script>
			<%-- <script type="text/javascript"
						src="<c:url value="/resources/js/custom/dataTable.js"/>"></script>

 --%>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
