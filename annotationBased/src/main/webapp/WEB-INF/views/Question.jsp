<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">



		<link
			href="<c:url value="/resources/css/library/simplePagination.css" />"
			rel="stylesheet">
		<link href="<c:url value="/resources/css/custom/agency.css" />"
			rel="stylesheet">
		<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>  -->
		<script type="text/javascript"
			src="<c:url value="/resources/js/library/jquery-2.1.1.min.js"/>"></script>

		<script type="text/javascript"
			src="<c:url value="/resources/js/library/bootstrap.min.js"/>"></script>
<script type="text/javascript"
				src="<c:url value="/resources/js/library/jquery.simplePagination.js"/>"></script>
			<script type="text/javascript"
				src="<c:url value="/resources/js/custom/question.js"/>"></script>
			<script type="text/javascript"
				src="<c:url value="/resources/js/custom/animate.js"/>"></script>
			

		<!-- DATA TABES SCRIPT -->


		<div class="body">
			<div class="row">
				<div class="col-lg-12">
					<ol class="breadcrumb">
						<li><i class="fa fa-dashboard"></i> <a
							href="../admin/dashboard" style="color: white;">Dashboard</a></li>
						<li class="active" style="color: white;"><i
							class="fa fa-desktop"></i> Create Questions</li>
					</ol>
				</div>
			</div>
			<div class="contianer">
				<div class=" col-md-8" style="margin-top: 45;">
					<section>
						<!-- CHECK BOX CHECK BOX CHECK BOX CHECK BOX CHECK BOX CHECK BOX CHECK BOX CHECK BOX CHECK BOX CHECK BOX CHECK BOX  -->
						<!-- CHECK BOX CHECK BOX CHECK BOX CHECK BOX CHECK BOX CHECK BOX CHECK BOX CHECK BOX CHECK BOX CHECK BOX CHECK BOX  -->


						<div class="row contianer-fluid">
							<section>


								<h1 style="color: #777">Insert Question</h1>

								<div class="panel-body">
									<!-- <form role="form" method="post" action="#"> -->
									<div class="form-group" id="choose-qType" style="margin: 0">
										<label for="sel1">Question type</label> <select
											name="questionType" class="form-control input-sm" id="qType">

										</select>
									</div>

									<ul class="list-group" style="margin: 0">

										<li class="list-group-item">
											<div class="form-group" id="qbox">
												<label>Question</label>
												<textarea class="form-control" rows="3" id="question"
													name="name"></textarea>
											</div>
										</li>
									</ul>
									<!--   -->
									<div id="options">
										<div class="row" id="option-box" class="CHECKBOX"
											style="margin: 0">
											<ul class="list-group">

												<li class="list-group-item" style="margin: 0">
													<div class=" col-md-2">Option:1</div>
													<div class=" col-md-8">
														<div class="input-group">
															<span class="input-group-addon"> <input
																type="checkbox" name="listOfAnswers" class="rdo"
																aria-label="sss" value="1">
															</span> <input type="text" name="listOfOptions"
																class="form-control txt" aria-label="...">
														</div>
													</div>
													<div class=" col-md-2">
														<button type="button" class="btn btn-primary btn-sm"
															id="add-option">Add More</button>
													</div>
												</li>
												<li class="list-group-item" style="margin: 0"id="RADIO" class="roption-box">
													<div class=" col-md-2">Option:1</div>
													<div class=" col-md-8">
														<div class="input-group">
															<span class="input-group-addon"> <input
																type="radio" name="listOfAnswers" class="rdo"
																aria-label="sss" value="1">
															</span> <input type="text" name="listOfOptions"
																class="form-control txt" aria-label="...">
														</div>
													</div>
													<div class=" col-md-2">
														<button type="button" class="btn btn-default btn-sm"
															id="add-option">Add More</button>
													</div>
												</li>
											</ul>
										</div>

									</div>
									<div class='row' style="margin: 0">
										<div class="col-md-1"></div>
										<div class="col-md-10" style="margin: 0">
											<button type="submit" class="btn btn-block btn-primary"
												id="submit" onclick="createQuestion()" style="margin: 0">Submit</button>
										</div>
										<div class="col-md-1"></div>
									</div>
									</form>
								</div>

							</section>
						</div>

					</section>
				</div>

				<div class=" col-md-3 side-bar" style="margin-top: 50px">
					<section class="links">
						<h1 class="section-heading text-highlight">
							<span class="line" style="color: #777">Quick Links</span>
						</h1>
						<div class="section-content">
							<p>
								<a href="#"><i class="fa fa-caret-right"></i>E-learning
									Portal</a>
							</p>
							<p>
								<a href="#"><i class="fa fa-caret-right"></i>Gallery</a>
							</p>
							<p>
								<a href="#"><i class="fa fa-caret-right"></i>Job Vacancies</a>
							</p>
							<p>
								<a href="#"><i class="fa fa-caret-right"></i>Contact</a>
							</p>
						</div>
						<!--//section-content-->
					</section>
				</div>
			</div>

			<div class="row contianer-fluid  ">

				<div class="col-md-11">
					<div class="row" id="search-and-pageSize">
						<div class=" col-md-2">
							<div class="form-group">
								<label for="sel1">Show:</label> <select
									class="form-control input-sm" id="show" style="height: 35px"
									style="margin: 0">
									<option>5</option>
									<option>4</option>
									<option>8</option>
									<option>20</option>
								</select>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row" id="msg"></div>
						</div>
						<div class=" col-md-6" align="right">
							<div class="form-group">
								<div class="input-group" style="margin-top: 25px">
									<input type="text" class="form-control" placeholder="Search"
										id="search" name="q">
									<div class="input-group-btn">
										<a href="#" class="btn btn-primary " style="height: 34;"
											onclick="searchQuestions()"><i class="fa fa-search"></i>
										</a>

									</div>
								</div>

							</div>
						</div>

						<div id="allQuestionContianer">
						
						
						</div>

						<div id="pagination"></div>
					</div>
					<div class="col-md-4">
						<div class="row" style="margin-left: 20px;">
							<div class="row" id="clickquestion" style="padding-top: 100px">

							</div>
						</div>
						<div></div>
					</div>

					<div class="row" id="ques"></div>
					<!--col-md-4  -->
					<div class="col-md-4" id="ques">
						<ul id="showquestion" style="color: yellow"></ul>
					</div>
				</div>
			</div>
	</tiles:putAttribute>
</tiles:insertDefinition>


