<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
 <link href="<c:url value="/resources/css/library/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
	<title>Question Bank</title>
</head>
<body>

	<div class="container">
		<h1>All Question</h1>




		<table class="table table-striped table-bordered">

			<thead>
				<tr>
					<th width="10px">ID</th>
					<th width="50px">Name</th>
					<th width="50px">Action</th>

				</tr>
			</thead>


			<tbody>
				<c:forEach var="question" items="${questions}">




					<tr>
						<td>${question.id}</td>
						<td><div id="Question"></div></td>
						<td><a href="../admin/deleteQuestion/${question.id}"><span
								class="glyphicon glyphicon-trash"></span></i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
           <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">Data Table With Full Features</h3>
                                </div><!-- /.box-header -->
                                <div class="box-body table-responsive">
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Name</th>
                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="question" items="${questionsForTest}">
                                            <tr>
                                            
                                                <td>${question.id}</td>
                                                <td>${question.name}</td>
                                                
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
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                             <!-- jQuery 2.0.2 -->
        <!-- Bootstrap -->
        <script type="text/javascript"
	src="<c:url value="/resources/js/library/jquery-2.1.1.min.js"/>"></script>
        <script type="text/javascript"
	src="<c:url value="/resources/js/library/bootstrap.min.js"/>"></script>
        <!-- DATA TABES SCRIPT -->
           <script type="text/javascript"
	src="<c:url value="/resources/js/library/jquery.dataTables.js"/>"></script>
        <script src="<c:url value="/resources/js/library/dataTables.bootstrap.js"/>" type="text/javascript"></script>
           <script src="<c:url value="/resources/js/custom/animate.js"/>" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <!-- AdminLTE for demo purposes -->
        <!-- page script -->
        <script type="text/javascript">
            $(function() {
                $("#example1").dataTable();
                $('#example2').dataTable({
                    "bPaginate": true,
                    "bLengthChange": false,
                    "bFilter": true,
                    "bSort": true,
                    "bInfo": true,
                    "bAutoWidth": false
                });
            });
        </script>
         <div class="row">
                        <div class="col-md-6">
                            <!-- Custom Tabs -->
                            <div class="nav-tabs-custom">
                                <ul class="nav nav-tabs">
                                <c:forEach var= "type" items="${allQuestionTypes}">
                                <li ><a  class="active" href="#${type}" data-toggle="tab">${type} </a></li>
                                </c:forEach>
                                   
                                    <li><a href="#tab_2" data-toggle="tab">Tab 2</a></li>
                                    <li class="dropdown">
                                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                            Dropdown <span class="caret"></span>
                                        </a>
                                        <ul class="dropdown-menu">
                                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Action</a></li>
                                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Another action</a></li>
                                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Something else here</a></li>
                                            <li role="presentation" class="divider"></li>
                                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Separated link</a></li>
                                        </ul>
                                    </li>
                                    <li class="pull-right"><a href="#" class="text-muted"><i class="fa fa-gear"></i></a></li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane active" id="CHECKBOX_TYPE">
                                        <div class="my-form">
										<form role="form" method="post" action='../admin/setQuestion'>
											
											<div id="checkBoxContainer">
												<div class="input-group input-group  panel-body">
													<span class="input-group-addon">Question</span> <input
														type="text" id="question" placeholder="Type question here"
														class="form-control" size="75" name='name' /><br>

												</div>
												<div class="col-xs-6 col-sm-10">
													<div class="list-group">

														<div class="option-box list-group-item">
															<div class="row">
																<div class="col-xs-1 col-sm-3">

																	<label for="box1">Option: <span
																		class="box-number ">1</span></label> <input type="checkbox"
																		name="listOfAnswers" id="inlineCheckbox3" value="1">
																</div>
																<div class="col-xs-6 col-sm-6">

																	<input type="text" name="listOfOptions"
																		class=" form-control input-sm"
																		placeholder="listofoptions" id="optionbox1 " size="20" />
																</div>
																<div class="col-xs-6 col-sm-2">
																	<a class="add-box" href="#">Add More</a>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div id="radioBoxContainer">
												<h1 style="color: green;">You are hero</h1>
											</div>
											<p>
												<input type="submit" class="btn btn-primary btn-lg"
													value="Submit" />
											</p>
									</div></div><!-- /.tab-pane -->
                                    <div class="tab-pane" id="RADIO_TYPE">
                                        <div class="my-form">
										<form role="form" method="post" action='../admin/setQuestion'>
											<div class="btn-group" role="group" aria-label="...">

												
											</div>
											<div id="checkBoxContainer">
												<div class="input-group input-group  panel-body">
													<span class="input-group-addon">Question</span> <input
														type="text" id="question" placeholder="Type question here"
														class="form-control" size="75" name='name' /><br>

												</div>
												<div class="col-xs-6 col-sm-10">
													<div class="list-group">

														<div class="option-box list-group-item">
															<div class="row">
																<div class="col-xs-1 col-sm-3">

																	<label for="box1">Option: <span
																		class="box-number ">1</span></label> <input type="radio"
																		name="listOfAnswers" id="inlineCheckbox3" value="1">
																</div>
																<div class="col-xs-6 col-sm-6">

																	<input type="text" name="listOfOptions"
																		class=" form-control input-sm"
																		placeholder="listofoptions" id="optionbox1 " size="20" />
																</div>
																<div class="col-xs-6 col-sm-2">
																	<a class="add-box" href="#">Add More</a>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div id="radioBoxContainer">
												<h1 style="color: green;">You are hero</h1>
											</div>
											<p>
												<input type="submit" class="btn btn-primary btn-lg"
													value="Submit" />
											</p>
									</div>
                                    </div><!-- /.tab-pane -->
                                </div><!-- /.tab-content -->
                            </div><!-- nav-tabs-custom -->
                        </div><!-- /.col -->

                      <!-- /.col -->
                    </div> <!-- /.row -->
                            
</body>
</html>