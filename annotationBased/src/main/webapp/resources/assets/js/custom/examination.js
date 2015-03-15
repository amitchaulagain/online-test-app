var aaExam = [];
var aaGroup = [];
var aaTest = [];
var examId;
function findAllExaminations() {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/allExaminations",
		dataType : "json",

		success : function(data) {
			renderAllExaminations(data);
		}
	});

}
// :::::::::::::::::::::::::::::::::::::::::::::::::::::::RENDER ALL
// Examinations
function renderAllExaminations(exams) {

	while (aaExam.length > 0) {
		aaExam.pop();
	}
	$.each(exams, function(idx, examination) {
		// var hero=exam.test;
		var oneExam = {
			eId : examination.exam.id,
			eName : examination.exam.name,
			eTime : examination.exam.examinationTime,
			eAssignedTest : examination.exam.test.name,
			eAssignedGroup : examination.groups,
			eIsAssigned : examination.exam.isAssigned
		}
		aaExam.push(oneExam);

	})
	aaExam.reverse();
	examPagi()
	var show = $("#showExaminationPerPage option:selected").text();
	var index = $('#examinationPagination').pagination('getCurrentPage');

	showExam(show, index, aaExam);
}

// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::DOCUMENT>READY>FUNCTION
$(document)
		.ready(
				function() {
					findAllExaminations();
					findAllTests();
					$(document).on("click", "#submit", function(e) {
						createOrEditExam();
					});
					
					
					$('#showExaminationPerPage').on('change', function() {

						var show = $("#showExaminationPerPage option:selected").text();
						var index = $('#examinationPagination').pagination('getCurrentPage');

						showExam(show, index, aaExam);
						 examPagi();
					});
					$(document)
							.on(
									"change",
									".assignExam",
									function(e) {
										  examId = $(this).attr("data-id");
										var isAssigned;
										if (this.checked) {
											isAssigned = true;
											if (confirm("Are you sure, you would like to assign this exam ?")) {
												assignExam(examId, isAssigned);
											} else {
												findAllExaminations()
											}
										} else {
											if (confirm("Unassign this exam?") == true) {
												assignExam(examId, isAssigned);
											} else {
												findAllExaminations()
											}
										}

									});

					$(document).on("click", "#createExamination", function(e) {
						if ($(this).text().trim() == 'Create') {
							$('#showAssignedGroup').hide();
							makeFormValueDisabled(false);
							enableAddAndRemoveGroup(false);
							$(this).html('Close');
							$(this).css("background-color", "red");
							$('#examinationPanel').show();
							clearFormValues();

						} else if ($(this).text().trim() == 'Close') {
							findAllExaminations();
							showDefaultView();
							$(this).html('Create');
							$(this).css("background-color", "#337ab7");
							$('#examinationPanel').hide();
						} else if ($(this).text().trim() == 'Edit') {

							makeFormValueDisabled(false);
							enableAddAndRemoveGroup(false);

							$(this).html('Close');
							$(this).css("background-color", "red");
							$('#chooseTestOption').hide();
						}

					});
					$(document)
							.on(
									"click",
									".alert",
									function(e) {
										todo = $(this).attr("actionToBeDone");

										if (todo == "view") {
											examId = $(this).attr("data-id");
											alert(examId)
											showExaminationById(examId);
											// makeFormValueDisabled(true);
											$('#examinationPanel').show();
											$('#createExamination')
													.html("Edit");
											$('#createExamination')
													.css("background-color",
															"green");

										} else if (todo == "update") {

											alert("doing update");
											// $( "#page-wrapper" ).hide();
										} else if (todo == "delete") {
											alert("doing delete");
										} else if (todo == "showGroup") {
											$('#showAllGroup').hide()
											findAndShowAllGroups()
										} else if (todo == "addGroupToExam") {
											alert(examId);
											groupId = $(this).attr("data-id");
											addGroupToExamination(examId,
													groupId);
										} else if (todo == "removeGroup") {
											groupId = $(this).attr("data-id");
											alert(examId);
											removeGroupFromExamination(examId,
													groupId);
										} else if (todo == "seatPlan") {
											   examId = $(this).attr("data-id");
											findSeatPlanningByExamId(examId);
										}

									});
				});

// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::: PAGINATION
// FUNCTION
function examPagi() {
	var show = $("#showExaminationPerPage option:selected").text();
	var totalData = aaExam.length;
	// $(function() {
	$('#examinationPagination').pagination(
			{
				items : totalData,
				itemsOnPage : show,
				cssStyle : 'light-theme',
				onPageClick : function(pageNumber, event) {
					var show = $("#showExaminationPerPage option:selected")
							.text();
					var index = $('#examinationPagination').pagination(
							'getCurrentPage');
					showExam(show, index, aaExam);
					// Callback triggered when a page is clicked
					// Page number is given as an optional parameter
				}
			});
	// });
}
function showExam(show, index, allExams) {
	var tInfo = []

	var initial = 0;
	var finale = show;

	if (index > 0) {

		initial = (index - 1) * show;
		finale = index * show;
		var limitedExam = allExams.slice(initial, finale);

	}

	else {
		limitedExam = allExams.slice(initial, finale);
	}
	$('#showExamination').children().remove();
	$
			.each(
					limitedExam,
					function(idx, exam) {
						$('#showExamination')
								.append(
										'<tr><td>'
												+ exam.eId
												+ '</td><td>'
												+ exam.eName
												+ '</td><td>'
												+ exam.eTime
												+ '</td><td> <div id="assGroup'
												+ exam.eId
												+ '">'
												+ '</div>'
												// + exam.eAssignedGroup
												+ '</td><td>'
												+ exam.eAssignedTest
												+ '</td><td width="50px">'
												+ '<input title="AssignExam" type="checkbox" class="assignExam"'
												+ 'data-id="'
												+ exam.eId
												+ '" value="'
												+ exam.eIsAssigned
												+ '" >'
												+ '</td><td width="20px">'
												// +exam.tType+'</td><td>' +
												+ '<a class='
												+ "alert"
												+ ' href="#'
												+ exam.eId
												+ '" actionToBeDone="view" data-id="'
												+ exam.eId
												+ '" >'
												+ '<i class="fa fa-eye" style="color:#00D000"></i></a>'
												+ '<a class='
												+ "alert"
												+ ' href="#" actionToBeDone="update" data-id="'
												+ exam.eId
												+ '" >'
												+ '<i style="color:rgba(189, 189, 41, 0.94);"class="fa fa-pencil-square-o"></i></a>'
												+ '<a class='
												+ "alert"
												+ ' href="#'

												+ exam.eId
												+ '" actionToBeDone="delete" data-id="'
												+ exam.tId
												+ '" >'
												+ '<i class="fa fa-trash-o" style="color:red"></i>'
												+ '</a>'
												+ '<a class='
												+ "alert"
												+ ' href="#'
												+ '" actionToBeDone="seatPlan" data-id="'
												+ exam.eId
												+ '" >'
												+ '<i class="fa fa-plus" style="color:green"></i></a>'
												+ '</td></tr>')
						var count = 0;

						$.each(exam.eAssignedGroup, function(idx, group) {
							count++;
							var qHtml = '<div id="assignedGroup' + group.id
									+ '"><li>' + group.name + '</li></div>';
							$('#assGroup' + exam.eId).append(qHtml);
						})

					})
	//					
	// $(".assignExam").filter(function() {
	//
	// return $(this).val() == true;
	// }).checked=true;

	$(".assignExam").filter(function() {

		return $(this).val() == "true";
	}).attr("checked", true);

}

function findAllGroups() {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/viewAllGroups",
		dataType : "json",

		success : renderGroup
	});

}
function renderGroup(groups) {
	while (aaGroup.length > 0) {
		aaGroup.pop();
	}

	$.each(groups, function(idx, group) {

		aaGroup.push(group);

	})
	aaGroup.reverse();
	groupPagi()
	var show = $("#showAllGroupsPerPage option:selected").text();
	var index = $('#allGroupsPagination').pagination('getCurrentPage');

	showGroup(show, index, aaGroup)
}
function groupPagi() {
	var show = $("#showAllGroupsPerPage option:selected").text();
	var totalData = aaGroup.length;
	$(function() {
		$('#allGroupsPagination').pagination(
				{
					items : totalData,
					itemsOnPage : show,
					cssStyle : 'light-theme',
					onPageClick : function(pageNumber, event) {
						var show = $("#showAllGroupsPerPage option:selected")
								.text();
						var index = $('#allGroupsPagination').pagination(
								'getCurrentPage');
						showGroup(show, index, aaGroup);
						// Callback triggered when a page is clicked
						// Page number is given as an optional parameter
					}
				});
	});
}
function showGroup(show, index, groups) {
	var initial = 0;
	var finale = show;

	if (index > 0) {

		initial = (index - 1) * show;
		finale = index * show;
		var limitedGroup = groups.slice(initial, finale);

	}

	else {
		limitedGroup = groups.slice(initial, finale);
	}
	$('#showGroup tr td').remove();
	$
			.each(
					limitedGroup,
					function(idx, group) {
						$('#showGroup')
								.append(
										'<tr><td>'
												+ group.id
												+ '</td><td>'
												+ group.name
												+ '</td><td width="20px">'
												// +test.tType+'</td><td>'
												+ '<a class='
												+ "alert"
												+ ' href="#'
												+ group.id
												+ '" actionToBeDone="addGroupToExam" data-id="'
												+ group.id
												+ '" >'
												+ '<i class="fa fa-plus" style="color:#00D000"></i></a>'
												+ '</td></tr>')
					})

}

function showExaminationById(examId) {
	$.ajax({
		type : 'GET',
		url : 'http://localhost:8085/annotationBased/admin/exam/' + examId,
		dataType : "json",

		success : function(data) {
			renderSingleExamination(data);
			makeFormValueDisabled(true);
			enableAddAndRemoveGroup(true);

		}
	});

}
function showExaminationByIdWhileEditing(examId) {
	$.ajax({
		type : 'GET',
		url : 'http://localhost:8085/annotationBased/admin/exam/' + examId,
		dataType : "json",

		success : function(data) {
			renderSingleExamination(data);
			makeFormValueDisabled(false);
			findAllExaminations();

		}
	});

}

function renderSingleExamination(examination) {

	var count = 0;
	var value;
	$('#examId').val(examination.exam.id);
	$('#name').val(examination.exam.name);
	$('#time').val(examination.exam.examinationTime);
	setSelectedTest(examination.exam.test);
	if (examination.assignedGroups.length > 0) {
		value = "Assigned Groups :"
		addGroupView(value, examination);
	} else {
		value = "Groups not assigned yet !";
		addGroupView(value, examination);
	}

	$
			.each(
					examination.assignedGroups,
					function(idx, group) {
						count++;
						var qHtml = ('<div class="panel panel-default">'
								+ '<div class="panel-heading" role="tab" id="q'
								+ group.id
								+ '" >'
								+ '<div class="row">'
								+ '<div class="col-lg-1">'
								+ count
								+ '</div>'
								+ '<div class="col-lg-5">'
								+ '<h4 class="panel-title" >'
								+ '<a data-toggle="collapse" class="question" data-parent="#accordion"href="#collapse" question-id="'
								+ group.id
								+ '" aria-expanded="true"aria-controls="collapseOne">'
								+ group.name
								+ ' </a>'
								+ '</h4>'
								+ '</div>'
								+ '<div class="col-lg-1">'
								+ '<a id="removeGroup'
								+ group.id
								+ '" class="alert"  actionToBeDone="removeGroup" data-id="'
								+ group.id
								+ '" >'
								+ '<i class="fa fa-trash-o"  style="color:red;cursor:pointer"></i>'
								+ ' </a>' + '</div>' + '</div>' + '</div>' + '</div>')
						$('#showAssignedGroup').append(qHtml);
					})

}

function findAllTests() {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/allTests",
		dataType : "json",

		success : renderAllTests
	});

}
// ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::RENDER
// ALL TEST
function renderAllTests(tests) {
	var initial = '<option value="0">SELECT' + '</option>'
	$('#assignTest').append(initial);
	$.each(tests, function(id, test) {
		aaTest = tests;
		var tHtml = '<option value="' + test.id + '">' + test.name
				+ '</option>'

		$('#assignTest').append(tHtml);
	})

}

function findAndShowAllGroups() {
	$('#allGroups').show();

	findAllGroups();
}
function showDefaultView() {
	$('#allGroups').hide();
	$('#showAssignedGroup').hide();
	// $('#examinationPanel').hide();

}
function makeFormValueDisabled(value) {
	$('#name').prop('disabled', value);
	$('#time').prop('disabled', value);
	$('#assignTest').prop('disabled', value);
	$('#submit').prop('disabled', value);

}

function setSelectedTest(test) {
	var requiredIndex;
	for (var k = 0; k < aaTest.length; k++) {
		if (test.id == aaTest[k].id) {
			requiredIndex = aaTest[k].id;
			break;
		}
	}
	$("#assignTest option").filter(function() {

		return $(this).val() == requiredIndex;
	}).prop("selected", true);
}
function clearFormValues() {
	$('#name').val("");
	$('#time').val("");
	$('#assignTest').children().remove();
	findAllTests();
}
function addGroupView(value, examination) {
	var eId;
	$('#showAssignedGroup').show();
	$('#showAssignedGroup').children().remove();
	if (examination != null) {
		eId = examination.exam.id;
	} else {
		eId = 0;
	}
	var mess = '<h4>' + value + '<a  id ="showAllGroup" class=' + "alert"
			+ ' href="#' + '" actionToBeDone="showGroup" data-id="' + eId
			+ '" > Add Group	'
			+ '<i class="fa fa-plus" style="color:green"></i></a>' + '</h4>'


		$('#showAssignedGroup').append(mess);

}
function addGroupToExamination(examId, groupId) {
	$
			.ajax({
				type : 'GET',
				url : "http://localhost:8085/annotationBased/admin/examination/addGroup/"
						+ examId + "/" + groupId,
				dataType : "json",

				success : function(msg) {
					alert(msg);
					showExaminationByIdWhileEditing(examId);
				}
			});

}
function removeGroupFromExamination(examId, groupId) {
	$
			.ajax({
				type : 'GET',
				url : "http://localhost:8085/annotationBased/admin/examination/removeGroup/"
						+ examId + "/" + groupId,
				dataType : "json",

				success : function(msg) {
					alert(msg);
					showExaminationByIdWhileEditing(examId);

				}
			});

}

function createOrEditExam() {
	$.ajax({
		type : 'POST',
		url : "http://localhost:8085/annotationBased/admin/saveExam",
		dataType : "json",
		contentType : "application/json",
		accept : "application/json",
		data : examToJSON(),
		success : function(examKoId) {

			findAllExaminations()
			examId = examKoId;
			showExaminationByIdWhileEditing(examId);
			addGroupView("Groups not assigned yet !", null)
			$('#showAssignedGroup').show();
			alert("created");
		},
		error : function(msg) {
			alert("error while saving examinations");
		}
	});
}
function examToJSON() {

	var examKoId = $('#examId').val();
	var testId = $('#assignTest').val();
	return JSON.stringify({
		"testId" : testId,
		exam : {
			"id" : examKoId,
			"name" : $('#name').val()
		}

	})
}

function enableAddAndRemoveGroup(value) {
	$('#showAllGroup').prop('disabled', value);
	if (value) {
		$('#showAllGroup').css('color', 'gray');
		$('#showAllGroup').children().css('color', 'gray');
		$(".alert").filter(function() {
			var ss = $(this).attr("actionToBeDone") == "removeGroup";
			return ss;
		}).children().css("color", "gray");
		$(".alert").filter(function() {
			var ss = $(this).attr("actionToBeDone") == "removeGroup";
			return ss;
		}).prop("disabled", true);
	} else {
		$('#showAllGroup').css('color', 'blue');
		$('#showAllGroup').children().css('color', 'green');
		$(".alert").filter(function() {
			var ss = $(this).attr("actionToBeDone") == "removeGroup";
			return ss;
		}).children().css("color", "red");
		$(".alert").filter(function() {
			var ss = $(this).attr("actionToBeDone") == "removeGroup";
			return ss;
		}).prop("disabled", false);
	}
}

function assignExam(examId, isAssigned) {
	$.ajax({
		type : 'POST',
		url : "http://localhost:8085/annotationBased/admin/assignExam",
		dataType : "json",
		contentType : "application/json",
		accept : "application/json",
		data : assignExamToJSON(examId, isAssigned),
		success : function(examKoId) {
			findAllExaminations()
			alert("done");
		},
		error : function(msg) {
			alert("error while saving examinations");
		}
	});
}

function assignExamToJSON(examId, isAssigned) {
	return JSON.stringify({
		exam : {
			"id" : examId,
			"isAssigned" : isAssigned
		}

	})
}
