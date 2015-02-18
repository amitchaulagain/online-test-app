/**
 * New node file
 *//*
var aaData = [];
var index = 0;
function searchQuestions() {
	var parameter = $('#search').val();

	$
			.ajax({
				type : 'GET',
				url : "http://localhost:8085/annotationBased/admin/search/"
						+ parameter,
				dataType : "json",

				success : renderSearchQuestion
			});

}
function renderSearchQuestion(data) {
	var searchedData = [];
	$.each(data, function(idx, question) {
		var oneQuestion = {
			qId : question.id,
			qName : question.name,
			qType : question.questionType
		}
		searchedData.push(oneQuestion);

	})

	var show = $("#show option:selected").text();
	var index = $('#pagination').pagination('getCurrentPage');
	$('tbody tr').remove();
	pagi()
	showLimitedQuestion(show, index, searchedData);
}






	


function showLimitedQuestion(show, index, datas) {
	var initial = 0;
	var finale = show;

	if (index > 0) {

		initial = (index - 1) * show;
		finale = index * show;
		var limitedQuestions = datas.slice(initial, finale);

	}

	else {
		var limitedQuestions = datas.slice(initial, finale);
	}

	$
			.each(
					limitedQuestions,
					function(idx, aQuestion) {

						$('#dataTable')
								.append(
										'<tr onclick="opt('
												+ aQuestion.qId
												+ ')">'
												+ '<td>'
												+ aQuestion.qId
												+ '</td>'
												+ '<td F>'
												+ '<a href="#'
												+ aQuestion.qIdx
												+ '" class="one-question">'
												+ aQuestion.qName
												+ '</a>'
												+ '</td>'
												+ '<td>'
												+ aQuestion.qType

												+ '</td>'
												+ '<td>'
												+ aQuestion.qDate

												+ '</td>'
												+ '<td>'
												+ '<button type="submit" class="btn  btn-sm btn-danger" id="del" onclick="deleteQuestion('
												+ aQuestion.qId
												+ ')" style="margin: 0">del</button>'
												+ '<button type="submit" class="btn  btn-sm btn-warning" id="update"  style="margin: 0">Update</button>'
												+ '</td>' + '</tr>'
								// onclick="updateQuestion('+aQuestion.qId+')"
								)

					});

}

$(document).ready(function() {
	findAllQuestions();
	
	
	$('#show').on('change', function() {
		$('tbody tr').remove();

		var show = $('#show option:selected').val();
		var index = $('#pagination').pagination('getCurrentPage');

		showLimitedQuestion(show, index, aaData);
		pagi();
	});

	
	 * $('#search').on('input', function() { //alert("ohooo") var parameter =
	 * $('#search').val(); searchQuestions(parameter) })
	 

});*/