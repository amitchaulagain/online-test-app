var aaExam = [];
function findAllExaminations() {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/allExaminations",
		dataType : "json",

		success : renderAllExaminations
	});

}
// ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::RENDER
// ALL TEST
function renderAllExaminations(exams) {

	$.each(exams, function(idx, examination) {
//		var hero=exam.test;
		var oneExam = {
			eId : examination.exam.id,
			eName :  examination.exam.name,
			eTime :  examination.exam.examinationTime,
			eAssignedTest:examination.exam.test.name,
			eAssignedGroup:examination.groups
		}
		aaExam.push(oneExam);

	})
	aaExam.reverse();
	examPagi()
	var show = $("#showExaminationPerPage option:selected").text();
	var index = $('#examinationPagination').pagination('getCurrentPage');

	showExam(show, index, aaExam)
}

// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::DOCUMENT>READY>FUNCTION
$(document).ready(function() {
findAllExaminations();	 
});

 
// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::: PAGINATION
// FUNCTION
function examPagi() {
	var show = $("#showExaminationPerPage option:selected").text();
	var totalData = aaExam.length;
	// $(function() {
	$('#examinationPagination').pagination({
		items : totalData,
		itemsOnPage : show,
		cssStyle : 'light-theme',
		onPageClick : function(pageNumber, event) {
			var show = $("#showExaminationPerPage option:selected").text();
			var index = $('#examinationPagination').pagination('getCurrentPage');
			showExam(show, index, aaExam);
			// Callback triggered when a page is clicked
			// Page number is given as an optional parameter
		}
	});
	// });
}
function showExam(show, index,allExams ) {
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
	$('#showExam tr td').remove();
	$.each(limitedExam,	function(idx, exam) {
						$('#showExamination')
								.append(
										'<tr><td>'
												+ exam.eId
												+ '</td><td>'
												+ exam.eName
												+ '</td><td>'
												+ exam.eTime
												+ '</td><td>'
												+ exam.eAssignedGroup
												+ '</td><td>'
												+ exam.eAssignedTest
												+ '</td><td width="20px">'
												// +exam.tType+'</td><td>'
												+ '<a class='
												+ "alert"
												+ ' href="#'
												+ exam.tId
												+ '" actionToBeDone="view" data-id="'
												+ exam.tId
												+ '" >'
												+ '<i class="fa fa-eye" style="color:#00D000"></i></a>'
												+ '<a class='
												+ "alert"
												+ ' href="#" actionToBeDone="updateQ" data-id="'
												+ exam.tId
												+ '" >'
												+ '<i style="color:rgba(189, 189, 41, 0.94);"class="fa fa-pencil-square-o"></i></a>'
												+ '<a class='
												+ "alert"
												+ ' href="#'

												+ exam.tId
												+ '" actionToBeDone="delete" data-id="'
												+ exam.tId
												+ '" >'
												+ '<i class="fa fa-trash-o" style="color:red"></i>'
												+ '</a>' + '</td></tr>')
					})

}

