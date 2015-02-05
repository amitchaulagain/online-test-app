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

			<div class="container">


				<div class="row">
					<h1 style="color: #777">Create Test Page</h1>

					<div class="col-md-12">


						<div class="row">
							<div class="col-md-12">
								<div id="chooseTestOption">
									<div class="col-lg-3 col-md-6">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<div class="row">
													<div class="col-xs-3">
														<i class="fa fa-th-list fa-5x"></i>
													</div>
													<div class="col-xs-9 text-right">
														<div style="margin-left: 20px">Test Without Set And
															SECTION</div>
													</div>
												</div>
											</div>
											<a href="#">
												<div class="panel-footer" id="testTypeOne">
													<span class="pull-left">create</span> <span
														class="pull-right"><i
														class="fa fa-arrow-circle-right"></i></span>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</div>
									<div class="col-lg-3 col-md-6">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<div class="row">
													<div class="col-xs-3">
														<i class="fa fa-align-left fa-5x"></i>
													</div>
													<div class="col-xs-9 text-right">
														<div>Test With Set And without SECTION</div>
													</div>
												</div>
											</div>
											<a href="#">
												<div class="panel-footer">
													<span class="pull-left">View Details</span> <span
														class="pull-right"><i
														class="fa fa-arrow-circle-right"></i></span>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</div>
									<div class="col-lg-3 col-md-6">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<div class="row">
													<div class="col-xs-3">
														<i class="fa fa-align-left fa-5x"></i>
													</div>
													<div class="col-xs-9 text-right">
														<div>Test With Set And without SECTION</div>
													</div>
												</div>
											</div>
											<a href="#">
												<div class="panel-footer">
													<span class="pull-left">View Details</span> <span
														class="pull-right"><i
														class="fa fa-arrow-circle-right"></i></span>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</div>
									<div class="col-lg-3 col-md-6">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<div class="row">
													<div class="col-xs-3">
														<i class="fa fa-align-left fa-5x" style="color: yellow"></i>
													</div>
													<div class="col-xs-9 text-right">
														<div style="color: #777">Test With Set And SECTION</div>
													</div>
												</div>
											</div>
											<a href="#">
												<div class="panel-footer">
													<span class="pull-left">Create</span> <span
														class="pull-right"><i
														class="fa fa-arrow-circle-right"></i></span>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
									</div>

								</div>
							</div>
						</div>
						<div class="row" style="margin-right: -22; margin-left: -22;">
							<div id="formToCreateTest" class="col-md-6">
								<div class="row" id="test-toolbar">
									<div class="col-md-2">
										<button class="btn btn-success btn-xs" type="button"
											id="addQuestionInTest">Add Questions</button>
									</div>

									<div class="col-md-2">
										<button class="btn btn-success btn-xs" id="create-form"
											type="button">create form</button>
									</div>
									<div class="col-md-6"></div>
								</div>
								<div id="testOneInfo">
									<div class="form-group ">
										<label for="name" style="color: #777">Name</label> <input
											type="text" name="name" class="form-control input-sm"
											id="testname" style="width:" />
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group ">
												<label for="fullmark" style="color: #777">Full Mark</label>
												<input type="text" id="fullmark"
													class="form-control input-sm" />
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group ">
												<label for="passmark" style="color: #777">Pass Mark</label>
												<input type="text" id="passmark"
													class="form-control input-sm" />
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group hero-unit">
												<label for="testDate" style="color: #777">Test Date</label>
												<input type="text" id="testDate" placeholder="DD/MM/yy"
													class="form-control input-sm" />
											</div>
										</div>
									</div>

								</div>

								<div class="form-group">


									<div class="row" id="updateInput">
										<div class="col-md-4">
											<div class="form-group ">
												<label for="passmark" style="color: #777">TEST-SET</label> <input
													type="text" id="testset" class="form-control input-sm" />
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group ">
												<label for="passmark" style="color: #777">SECTION</label> <input
													type="text" id="section" class="form-control input-sm" />
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group ">
												<label for="passmark" style="color: #777">SOMETHING
													HERE</label> <input type="text" id="asd"
													class="form-control input-sm" />
											</div>
										</div>



									</div>
									<div class="contianer" id="drop-container">

										<div class="row dropable-box">
												<div class="col-md-4 dropable" id="dropable1">DROP HERE</div>
												<div class="col-md-4 dropable" id="dropable2">DROP HERE</div>
												<div class="col-md-4 dropable" id="dropable3">DROP HERE</div>
										</div>

										<div class="row dropable-box">

											<div class="col-md-3 dropable" id="dropable4">DROP HERE</div>
											<div class="col-md-3 dropable" id="dropable5">DROP HERE</div>
											<div class="col-md-3 dropable" id="dropable6">DROP HERE</div>

										</div>


									</div>
									<div class="form-group ">

										<p>
											Negative Marking <input type="checkbox" name="equalMarking"
												value="true">
										</p>

										<button type="submit" class="btn btn-primary btn-sm"
											onclick="createTest()">Submit</button>
									</div>

								</div>


							</div>
							<div class="col-md-3">
								<div id="dynamic-option"></div>
							</div>
							<div class="col-md-4" style="margin-top: -68px;">
								<section>
								<div id="selectedQuestion" style="position: fixed;">
									<h1 style="color: #777">question in test</h1>

								</div>
								</section>
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
					<div class="col-md-8">
						<section>
						<div class="col-md-12">

							<div class="row">
								<div class="col-md-12">
									<div class="panel-group" id="accordion" role="tablist"
										aria-multiselectable="true">
										<h1 style="color: #777">EXISTING Test</h1>
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
											<div class="col-md-4"></div>
											<div class="col-md-4"></div>
										</div>

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
			</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
