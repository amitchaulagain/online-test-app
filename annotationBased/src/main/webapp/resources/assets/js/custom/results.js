var aaExam = [];

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
$(document).ready(function() {
	findAllExaminations();
	 $('a, button').click(function() {
	        $(this).toggleClass('active');
	    });

	$('#showExaminationPerPage').on('change', function() {

		var show = $("#showExaminationPerPage option:selected").text();
		var index = $('#examinationPagination').pagination('getCurrentPage');

		showExam(show, index, aaExam);
		examPagi();
	});
	$(document).on("click", ".alert", function(e) {
		var examId=$(this).attr("data-id");
		alert("u r hero");
		localStorage.setItem('hero',examId);
		window.location.href = "http://localhost:8085/annotationBased/admin/results";
		
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
												+ 'disabled data-id="'
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
												+ '<i class="fa fa-eye" style="color:#00D000"></i>'
												+'<i class="fa fa-spinner fa-pulse"></i></a>'
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
