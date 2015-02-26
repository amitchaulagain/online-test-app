var aaStudent = [];
var todo;
$(document).ready(function() {
	findAllStudents();

	$(document).on("click", "#createStudent", function(e) {
		$('#studentPanel').show();
		clearStudentFormValues();
		setDisabledFormsParameter(false);
		if ($(this).text().trim() == 'Create') {
			
			$(this).html('Close');
			$(this).css("background-color", "red");
		} else if ($(this).text().trim() == 'Close') {
			$(this).html('Create');
			$(this).css("background-color", "#337ab7");
			$('#studentPanel').hide();
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
		
		createStudent();
	});
	$('#showStudentPerPage').on('change', function() {

		var show = $('#showStudentPerPage option:selected').val();
		var index = $('#studentPagination').pagination('getCurrentPage');

		showStudent(show, index, aaStudent);
		pagi();
	});
	$(document).on("click", ".alert", function(e) {
		  todo = $(this).attr("actionToBeDone");
		var studentId = $(this).attr("data-id");
		if (todo == "view") {
			viewSingleStudent(studentId,todo);

		} else if (todo == "update") {
			viewSingleStudent(studentId,todo);

			//					$( "#page-wrapper" ).hide();
		} else if (todo == "delete") {
			alert("delete")
		}
	});

});

function findAllStudents() {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/viewAllStudents",
		dataType : "json",

		success : renderStudent
	});

}
function renderStudent(students) {
	while (aaStudent.length > 0) {
		aaStudent.pop();
	}
	$.each(students, function(idx, student) {
		var oneStudent = {
			sId : student.studentId,
			sName : student.firstName + " " + student.lastName,
			sEmail : student.email,
			sAddress : student.address,
			sCountry : student.country,
			sPhoneNumber : student.phoneNumber,
			sUsername : student.username,
			sPassword : student.password,
			sDateOfBirth : student.dateOfBirth,
		}
		aaStudent.push(oneStudent);

	})
	aaStudent.reverse();
	pagi()
	var show = $("#showStudentPerPage option:selected").text();
	var index = $('#studentPagination').pagination('getCurrentPage');

	showStudent(show, index, aaStudent)
}
function pagi() {
	var show = $("#showStudentPerPage option:selected").text();
	var totalData = aaStudent.length;
	$(function() {
		$('#studentPagination').pagination(
				{
					items : totalData,
					itemsOnPage : show,
					cssStyle : 'light-theme',
					onPageClick : function(pageNumber, event) {
						var show = $("#showStudentPerPage option:selected")
								.text();
						var index = $('#studentPagination').pagination(
								'getCurrentPage');
						showStudent(show, index, aaStudent);
						// Callback triggered when a page is clicked
						// Page number is given as an optional parameter
					}
				});
	});

}
function showStudent(show, index, students) {
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
	$('#showStudent tr td').remove();
	$
			.each(
					limitedStudent,
					function(idx, student) {
						$('#showStudent')
								.append(
										'<tr><td>'
												+ student.sName
												+ '</td><td>'
												+ student.sEmail
												+ '</td><td>'
												+ student.sPhoneNumber
												+ '</td><td>'
												+ student.sAddress
												+ '</td><td>'
												+ student.sCountry
												+ '</td><td>'
												+ student.sDateOfBirth
												+ '</td><td width="20px">'
												// +test.tType+'</td><td>'
												+ '<a class='
												+ "alert"
												+ ' href="#'
												+ '" actionToBeDone="view" data-id="'
												+ student.sId
												+ '" >'
												+ '<i class="fa fa-eye" style="color:#00D000"></i></a>'
												+ '<a class='
												+ "alert"
												+ ' href="#" actionToBeDone="update" data-id="'
												+ student.sId
												+ '" >'
												+ '<i style="color:rgba(189, 189, 41, 0.94);"class="fa fa-pencil-square-o"></i></a>'
												+ '<a class='
												+ "alert"
												+ ' href="#'

												+ '" actionToBeDone="delete" data-id="'
												+ student.sId
												+ '" >'
												+ '<i class="fa fa-trash-o" style="color:red"></i>'
												+ '</a>' + '</td></tr>')
					})

}
function createStudent() {
	$.ajax({
		type : 'POST',
		url : "http://localhost:8085/annotationBased/admin/saveStudent",
		dataType : "json",
		contentType : "application/json",
		accept : "application/json",
		data : studentToJSON(),
		success : function(msg) {

			alert("created")
			findAllStudents()
		},
		error : function(msg) {
			alert("error while saving test");
		}
	});

}
function studentToJSON() {
	var studentId = 0;
	return JSON.stringify({

		"studentId" : studentId,
		"firstName" : $('#firstName').val(),
		"middleName" : $('#middleName').val(),
		"lastName" : $('#lastName').val(),
		"email" : $('#email').val(),
		"phoneNumber" : $('#phoneNumber').val(),
		"dateOfBirth" : $('#dateOfBirth').val(),
		"address" : $('#address').val(),
		"country" : $('#country').val()
	})

}

function viewSingleStudent(studentId) {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/viewStudent/" + studentId,
		dataType : "json",

		success : showStudentInformation 
	});

}
function showStudentInformation(student) {
	$('#studentPanel').show();
	$('#studentId').val(student.studentId);

	$('#firstName').val(student.firstName);
	$('#middleName').val("Prasad");
	$('#lastName').val(student.lastname);
	$('#email').val(student.email);
	$('#phoneNumber').val(student.phoneNumber);
	$('#dateOfBirth').val(student.dateOfBirth);
	$('#address').val(student.address);
	$('#country').val(student.country);
	setDisabledFormsParameter(false);
	if(todo=="view"){
		setDisabledFormsParameter(true);
		
	}


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

function setDisabledFormsParameter(value){
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
