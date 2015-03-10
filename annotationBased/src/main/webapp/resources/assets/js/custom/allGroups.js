var aaStudent = [];
var aaGroupStudents=[];
var aaGroup = [];
var groupId;
$(document).ready(function() {
	findAllGroups();
	$(document).on("click", "#createGroup", function(e) {
		$('#groupPanel').show();
		if ($(this).text().trim() == 'Create') {

			$(this).html('Close');
			$(this).css("background-color", "red");
		} else if ($(this).text().trim() == 'Close') {
			$(this).html('Create');
			$(this).css("background-color", "#337ab7");
			$('#groupPanel').hide();
		} else if ($(this).text().trim() == 'Edit') {
			$('#id').prop('disabled', false);
			$('#name').prop('disabled', false);
			$('#fullmark').prop('disabled', false);
			$('#passmark').prop('disabled', false);
			$('#duration').prop('disabled', false);
			$('#testDate').prop('disabled', false);
			$('#submit').prop('disabled', false);
			$(this).html('Close');
			$(this).css("background-color", "red");
			$('#chooseTestOption').hide();
		}
	});
	$(document).on("click", "#submit", function(e) {

		createGroup();
	});
	$(document).on("click", "#clickHere", function(e) {

		$('#allStudents').show();
		 
		var searchText=$(this).val();
		showAllStudentInformation(searchText)
	});
	
	$(document).on("keyup", "#search", function(e) {

		var searchText=$(this).val();
		showAllStudentInformation(searchText)
	});
	
	$('#showGroupPerPage').on('change', function() {

		var show = $('#showGroupPerPage option:selected').val();
		var index = $('#groupPagination').pagination('getCurrentPage');

		showGroup(show, index, aaGroup);
		pagi();
	});
	$('#showGroupStudentsPerPage').on('change', function() {

		var show = $('#showGroupStudentsPerPage option:selected').val();
		var index = $('#groupStudentsPagination').pagination('getCurrentPage');

		showGroupStudents(show, index, aaGroupStudents);
		groupStudentsPagi();
	});
	$(document).on("click", ".alert", function(e) {
		todo = $(this).attr("actionToBeDone");
		  groupId = $(this).attr("data-id");
		if (todo == "view") {
			$('#gStudents').show();
			showAllStudentInformationByGroupId(groupId)

		} else if (todo == "update") {

			alert(groupId)
			//					$( "#page-wrapper" ).hide();
		} else if (todo == "delete") {
			alert(groupId)
		}
	});
	$(document).on("click", ".addStudentToGroup", function(e) {
		alert("hero");
		addStudentToGroup($(this).attr("data-id"));
	});

});

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
	pagi()
	var show = $("#showGroupPerPage option:selected").text();
	var index = $('#groupPagination').pagination('getCurrentPage');

	showGroup(show, index, aaGroup)
}
function pagi() {
	var show = $("#showGroupPerPage option:selected").text();
	var totalData = aaGroup.length;
	$(function() {
		$('#groupPagination').pagination({
			items : totalData,
			itemsOnPage : show,
			cssStyle : 'light-theme',
			onPageClick : function(pageNumber, event) {
				var show = $("#showGroupPerPage option:selected").text();
				var index = $('#groupPagination').pagination('getCurrentPage');
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
	$.each(limitedGroup,function(idx, group) {
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
												+ '" actionToBeDone="view" data-id="'
												+ group.id
												+ '" >'
												+ '<i class="fa fa-eye" style="color:#00D000"></i></a>'
												+ '<a class='
												+ "alert"
												+ ' href="#" actionToBeDone="update" data-id="'
												+ group.id
												+ '" >'
												+ '<i style="color:rgba(189, 189, 41, 0.94);"class="fa fa-pencil-square-o"></i></a>'
												+ '<a class='
												+ "alert"
												+ ' href="#'

												+ group.id
												+ '" actionToBeDone="delete" data-id="'
												+ group.id
												+ '" >'
												+ '<i class="fa fa-trash-o" style="color:red"></i>'
												+ '</a>' + '</td></tr>')
					})

}

function createGroup() {
	$.ajax({
		type : 'POST',
		url : "http://localhost:8085/annotationBased/admin/saveGroup",
		dataType : "json",
		contentType : "application/json",
		accept : "application/json",
		data : groupToJSON(),
		success : function(msg) {

			findAllGroups()
			alert("created")
		},
		error : function(msg) {
			alert("error while saving group");
		}
	});

}
function groupToJSON() {
	var groupId = 0;
	return JSON.stringify({
		group : {
			"id" : groupId,
			"name" : $('#name').val()
		}
	})

}

function viewSingleStudent(studentId) {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/viewStudent/"
				+ studentId,
		dataType : "json",

		success : showStudentInformation
	});

}
function showAllStudentInformationByGroupId(groupId) {
	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/viewGroupStudents/"
				+ groupId,
		dataType : "json",

		success : renderGroupStudents
	});

}
function clearStudentFormValues() {

	$('#studentId').val(0);

	$('#firstName').val("");
	$('#middleName').val("");
	$('#lastName').val("");
	$('#email').val("");
	$('#phoneNumber').val("");
	$('#dateOfBirth').val("");
	$('#address').val("");
	$('#country').val("");
}

function setDisabledFormsParameter(value) {
	$('#id').prop('disabled', value);
	$('#firstName').prop('disabled', value);
	$('#middleName').prop('disabled', value);
	$('#lastName').prop('disabled', value);
	$('#email').prop('disabled', value);
	$('#phoneNumber').prop('disabled', value);
	$('#dateOfBirth').prop('disabled', value);
	$('#address').prop('disabled', value);
	$('#country').prop('disabled', value);
	$('#submit').prop('disabled', value);
}
function renderGroupStudents(groupStudents) {
	while (aaGroupStudents.length > 0) {
		aaGroupStudents.pop();
	}

	$.each(groupStudents, function(idx, student) {

		aaGroupStudents.push(student);

	})
	aaGroupStudents.reverse();
	groupStudentsPagi()
	var show = $("#showGroupStudentsPerPage option:selected").text();
	var index = $('#groupStudentsPagination').pagination('getCurrentPage');

	showGroupStudents(show, index, aaGroupStudents)
}
function groupStudentsPagi() {
	var show = $("#showGroupStudentsPerPage option:selected").text();
	var totalData = aaGroupStudents.length;
	$(function() {
		$('#groupStudentsPagination').pagination({
			items : totalData,
			itemsOnPage : show,
			cssStyle : 'light-theme',
			onPageClick : function(pageNumber, event) {
				var show = $("#showGroupStudentsPerPage option:selected").text();
				var index = $('#groupStudentsPagination').pagination('getCurrentPage');
				showGroupStudents(show, index, aaGroupStudents);
				// Callback triggered when a page is clicked
				// Page number is given as an optional parameter
			}
		});
	});
}
function showGroupStudents(show, index, students) {
	var initial = 0;
	var finale = show;

	if (index > 0) {

		initial = (index - 1) * show;
		finale = index * show;
		var limitedStudent = students.slice(initial, finale);
		show

	}

	else {
		limitedStudent = students.slice(initial, finale);
	}
	$('#showStudent tr td').remove();
	$.each( limitedStudent,
					function(idx, student) {
						$('#showStudent')
								.append(
										'<tr><td>'
												+ student.firstName
												+ '</td><td>'
												+ student.lastName
												+ '</td><td>'
												+ student.email
												+ '</td><td>'
												+ student.address
												+ '</td><td>'
												+ student.country
												+ '</td><td>'
												+ student.dateOfBirth
												+ '</td><td width="20px">'
												// +test.tType+'</td><td>'
												+ '<a class='
												+ "alert"
												+ ' href="#'
												+ '" actionToBeDone="view" data-id="'
												+ student.sId
												+ '" >'
												+ '<i class="fa fa-trash-o" style="color:red"></i></a>'
												 +'</td></tr>')
					})


}
function showAllStudentInformation(searchText) {
 
	$.ajax({
		type : 'POST',
		url : "http://localhost:8085/annotationBased/admin/searchStudent",
		dataType : "json",
		contentType : "application/json",
		accept : "application/json",
		data : searchStringToJSON(searchText),
		success :  renderStudentInformation
	})
}
function renderStudentInformation(allStudents) {
	while (aaStudent.length > 0) {
		aaStudent.pop();
	}


	$.each(allStudents, function(idx, student) {

		aaStudent.push(student);

	})
	aaStudent.reverse();
	allStudentsPagi()
	var show = $("#showAllStudentsPerPage option:selected").text();
	var index = $('#allStudentsPagination').pagination('getCurrentPage');

	showAllStudents(show, index, aaStudent)
}
function allStudentsPagi() {
	var show = $("#showAllStudentsPerPage option:selected").text();
	var totalData = aaStudent.length;
	$(function() {
		$('#allStudentsPagination').pagination({
			items : totalData,
			itemsOnPage : show,
			cssStyle : 'light-theme',
			onPageClick : function(pageNumber, event) {
				var show = $("#showAllStudentsPerPage option:selected").text();
				var index = $('#allStudentsPagination').pagination('getCurrentPage');
				showAllStudents(show, index, aaStudent);
				// Callback triggered when a page is clicked
				// Page number is given as an optional parameter
			}
		});
	});
}
function showAllStudents(show, index, students) {
	var initial = 0;
	var finale = show;

	if (index > 0) {

		initial = (index - 1) * show;
		finale = index * show;
		var limitedStudent = students.slice(initial, finale);

	}

	else {
		limitedStudent = students.slice(initial, finale);
	}
	$('#showAllStudent tr td').remove();
	$.each( limitedStudent,
					function(idx, student) {
						$('#showAllStudent')
								.append(
										'<tr><td>'
												+ student.firstName
												+ '</td><td>'
												+ student.lastName
												+ '</td><td>'
												+ student.email
												+ '</td><td>'
												+ student.address
												+ '</td><td>'
												+ student.country
												+ '</td><td>'
												+ student.dateOfBirth
												+ '</td><td width="20px">'
												// +test.tType+'</td><td>'
												+ '<a class='
												+ "addStudentToGroup"
												+ ' href="#'
												+ '" actionToBeDone="addStudent" data-id="'
												+ student.studentId
												+ '" >'
												+ '<i class="fa fa-plus" style="color:green"></i></a>'
												 +'</td></tr>')
					})


}
function addStudentToGroup(studentId){
	$.ajax({
		type : 'POST',
		url : "http://localhost:8085/annotationBased/admin/addStudentsToGroup",
		dataType : "json",
		contentType : "application/json",
		accept : "application/json",
		data : valueToJSON(studentId),
		success : function(msg) {
			refreshGroupStudentsView();
		},
		error : function(msg) {
			alert("error while saving group");
		}
	});

}
function refreshGroupStudentsView(){
	showAllStudentInformationByGroupId(groupId);

}
function valueToJSON(studentId){
	return JSON.stringify({
		group : {
			"id" : groupId
		},
		student :{
			"id":studentId
		}
	})
}

function searchStringToJSON(searchText){
	return JSON.stringify({
	"searchString" : searchText
	})
}