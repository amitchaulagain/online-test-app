var aaExamInfo = [];


$(document).ready(function (){
	$('#showStudentsSeatPerPage').on('change', function() {

		var show = $('#showStudentsSeatPerPage option:selected').val();
		var index = $('#examSeatPagination').pagination('getCurrentPage');

		showStudent(show, index, aaExamInfo);
		pagi();
	});
});


function findSeatPlanningByExamId(examId) {
	$('#seatPlanningContainer').show();
	$('#examinationContainer').hide();
	$('#seatPlanning').show();
	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/seatPlan/" + examId,
		dataType : "json",

		success : function(data) {
			renderAllSeatPlan(data);
		}
	});

}
function renderAllSeatPlan(examInfos) {
 
	while (aaExamInfo.length > 0) {
		aaExamInfo.pop();
	}
	$.each(examInfos, function(idx, examInformation) {
		
		var oneExamInfo = {
			sId : examInformation.student.studentId,
			sName : examInformation.student.firstName + " " + examInformation.student.lastName,
			sEmail : examInformation.student.email,
			sAddress : examInformation.student.address,
			sCountry : examInformation.student.country,
			sPhoneNumber : examInformation.student.phoneNumber,
			sUsername : examInformation.student.username,
			sPassword : examInformation.student.password,
			sDateOfBirth : examInformation.student.dateOfBirth,
			seatNumber:examInformation.seatNumber,
			setAssigned :examInformation.set.name
		}
		aaExamInfo.push(oneExamInfo);

	})
//	aaStudent.reverse();
	pagi()
	var show = $("#showStudentsSeatPerPage option:selected").text();
	var index = $('#examSeatPagination').pagination('getCurrentPage');

	showStudent(show, index, aaExamInfo)
}
function pagi() {
	var show = $("#showStudentsSeatPerPage option:selected").text();
	var totalData = aaExamInfo.length;
	$(function() {
		$('#examSeatPagination').pagination(
				{
					items : totalData,
					itemsOnPage : show,
					cssStyle : 'light-theme',
					onPageClick : function(pageNumber, event) {
						var show = $("#showStudentsSeatPerPage option:selected")
								.text();
						var index = $('#examSeatPagination').pagination('getCurrentPage');
						showStudent(show, index, aaExamInfo);
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
	$('#showExamSeat tr td').remove();
	$.each(limitedStudent,
					function(idx, student) {
						$('#showExamSeat')
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
												+ student.sDateOfBirth
												+ '</td><td>'
												+ student.seatNumber
												+ '</td><td width="20px">'
												+ student.setAssigned
												+ '</td></tr>')
					})

}


