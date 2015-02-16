<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-md-12">

	<div id="testPanel" class="col-md-12" style="clear: both" test-type="">
		<div class="row">
			<nav class="navbar navbar-default navbar-static-top"
				role="navigation">
				<div class="navbar-header" style="margin-bottom: 7px;">
					<a class="navbar-brand" href="#" style="padding: 6px;">Test
						Panel</a>
				</div>
				<div>
					<ul id="leftNavBar" class="nav navbar-nav"
						style="margin-bottom: 7px;">
						<li class="active"><a href="#" style="padding-top: 6px;">Add
								Question</a></li>
						<li><a href="#" style="padding-top: 6px;" id="create-form">Create
								Form</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" style="padding-top: 6px;"> insert<b
								class="caret"></b>
						</a>
							<ul class="dropdown-menu"">
								<li><a href="#" id="addSet"> set</a></li>
								<li class="divider"></li>
								<li><a href="#" id="addSections">sections</a></li>
								<li class="divider"></li>
							</ul></li>
						<ul class="nav navbar-right">
							<a href="#"><i id="cancle" " class="fa fa-times"></i></a>
						</ul>
					</ul>
				</div>
			</nav>
		</div>
		<div class="row">
			<div class="row" id="createTestForm">
				<div id="drop-Header" class="col-md-12" >
					<div class="dropable-box" class="col-md-12" style="width:98%">
						<div class="col-md-12 dropable" style="width:98%" id="dropable-header">HEADER HERE</div>
					</div>
				</div>
				<div class="col-md-12">

					<div class="form-group ">
						<label for="name">Name</label> <input type="text" name="testName"
							id="name" class="form-control input-sm" />
					</div>
				</div>

				<div class="col-md-4">
					<div class="form-group ">
						<label for="fullmark">Full Mark</label> <input type="text"
							name="fullmark" class="form-control input-sm" id="fullmark" />
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group ">
						<label for="passmark">Pass Mark</label> <input type="text"
							name="passmark" class="form-control input-sm" id="passmark" />
					</div>
				</div>
				<div class="col-md-4">
					<div class="datepicker form-group " data-date-format="yyyy/mm/dd">
						<label for="testDate">Test Date</label> <input type="text"
							name="testDate" class=" form-control input-sm" id="testDate"   />
					</div>




				</div>

				<div class="col-md-4">
					<div class=" ">
						<label for="testDuration">Test Duration</label> <input type="text"
							name="duration" class="form-control input-sm" id="duration" />
					</div>
				</div>
			</div>


			<div id="testPanel" style="clear: both">
				<div class="row " id="drop-container" display="none">

					<div class="dropable-box">
						<div class="col-md-4 dropable" id="dropable1">DROP HERE</div>
						<div class="col-md-4 dropable" id="dropable2">DROP HERE</div>
						<div class="col-md-4 dropable" id="dropable3">DROP HERE</div>

						<div class="col-md-4 dropable" id="dropable4">DROP HERE</div>
						<div class="col-md-4 dropable" id="dropable5">DROP HERE</div>
						<div class="col-md-4 dropable" id="dropable6">DROP HERE</div>

					</div>


				</div>
			</div>


		</div>
		<div class="col-md-12" id="createSetPanel"></div>
		<div class="col-md-8"></div>
		<div class="col-md-4">
			<div class="form-group">
				<button type="button" class="btn btn-success btn-block"
					id="createTest" data-step="step1">Next</button>

			</div>
		</div>
	</div>