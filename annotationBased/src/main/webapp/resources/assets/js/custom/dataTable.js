/**
 * New node file
 */
var aaData = [];
var index = 0;
function searchQuestions() {
	var parameter = $('#search').val();

	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/search/"+parameter,
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
	var index =$('#pagination').pagination('getCurrentPage');
	$('tbody tr').remove();
	pagi()
	showLimitedQuestion(show, index,searchedData);
}
function findAllQuestions() {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/allQuestions",
		dataType : "json",

		success : renderQuestions
	});

	show = $("#show option:selected").text();
	
}

function deleteQuestion(id) {

	$.ajax({
		type : 'DELETE',
		url : "http://localhost:8085/annotationBased/admin/deleteQuestion/"
				+ id,
		dataType : "json",

		success : function(result) {
			$("#msg").html(
					'<h5>' + "question- " + result.name + "deleted" + '</h5>')
					.fadeIn(500).delay(500).fadeOut(500);
			/*
			 * $('#msg').append('<h4>'+"question"+result.name+"::::deleted"+'</h4>')
			 * 
			 * $("#msg h4").delay(100000).fadeOut('slow');
			 */
		},
		complete : function() {
			$('tbody tr').remove();
			while (aaData.length > 0) {
				aaData.pop();
			}
			findAllQuestions();
		},
		error : function(msg) {
			alert("Error deleting !!!");
		}
	});

}
function pagi() {
	var show = $("#show option:selected").text();
	var totalData = aaData.length;
	$(function() {
		$('#pagination').pagination({
			items : totalData,
			itemsOnPage : show,
			cssStyle : 'light-theme'
		});
	});
}

function renderQuestions(questions) {

	$.each(questions, function(idx, question) {
		var oneQuestion = {
			qId : question.id,
			qName : question.name,
			qType : question.questionType,
			qDate : question.createdDate
		}
		aaData.push(oneQuestion);

	})
	pagi();
	var show = $("#show option:selected").text();
	var index = $('#pagination').pagination('getCurrentPage');
	var allData = aaData;
	showLimitedQuestion(show, index,allData);
	 
}

function showLimitedQuestion(show, index,datas) {
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
	findAllQuestions()
	$('.side-nav').hide()
	$('#show').on('change', function() {
		$('tbody tr').remove();

		var show = $('#show option:selected').val();
		var index = $('#pagination').pagination('getCurrentPage');

		showLimitedQuestion(show, index,aaData);
		pagi();
	});

	/*$('#search').on('input', function() {
		//alert("ohooo")
		var parameter = $('#search').val();
		searchQuestions(parameter)
	})*/
	
});