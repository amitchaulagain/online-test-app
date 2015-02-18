/**
 * 
 */
var allQuestions = [];
var contianer;
function findAllQuestions(questionContianer,paginationContianer) {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/allQuestions",
		dataType : "json",

		success : renderQuestions,
	});
	return allQuestions;
}
function renderQuestions(questions,questionContianer,paginationContianer) {

	$.each(questions, function(idx, q) {
		var answers=[];
		$.each(q.answers, function(idx, ans){
			var answer = {
					ansId : ans.id,
					ansName:ans.options
					
			}
			answers.push(answer)
		})
		var options = [];
		$.each(q.options, function(idx, o) {
			var option = {
				id : o.Id,
				name : o.name
			}
			options.push(option)
		})
		var oneQuestion = {
			qId : q.mainquestion.id,
			qName : q.mainquestion.name,
			qType : q.mainquestion.questionType,
			qDate : q.mainquestion.createdDate,
			qOptions : options
		}
		allQuestions.push(oneQuestion);
		
		
		
	})
loadAllQuestions(questionContianer,paginationContianer)
}
function loadAllQuestions() {
	contianer = "allQuestionContianer";
	var questions = allQuestions;
		
		
		pagi(questions);
	
	var show = $("#show option:selected").text();
	var index = $('#pagination').pagination('getCurrentPage');
	drawQuestionList(show, index, questions,contianer);

}
function drawQuestionList(show, index, questions,contianer) {
	var count = 0;
	var questionToDraw = questions;
	var limitedQuestion;
	var initial = 0;
	var finale = show;

	if (index > 0) {

		initial = (index - 1) * show;
		finale = index * show;
		limitedQuestion = questionToDraw.slice(initial, finale);

	}

	else {
		limitedQuestion = questionToDraw.slice(initial, finale);
	}
	$
			.each(
					limitedQuestion,
					function(idx, aQuestion) {
						count = count + 1;
						var qHtml = ('<div class="panel panel-default">'
								+ '<div class="panel-heading" role="tab" id="q'
								+ aQuestion.qId
								+ '" >'
								+ '<div class="row">'
								+ '<div class="col-lg-1">'
								+ count
								+ '</div>'
								+ '<div class="col-lg-9">'
								+ '<h4 class="panel-title" >'
								+ '<a data-toggle="collapse" class="question" data-parent="#accordion"href="#collapse" question-id="'
								+ aQuestion.qId
								+ '" aria-expanded="true"aria-controls="collapseOne">'
								+ aQuestion.qName
								+ ' </a>'
								+ '</h4>'
								+ '</div>'
								+ '<div class="col-lg-2">'
								+ '<a class='
								+ "updateQuestion"
								+ ' href="#" actionToBeDone="updateQ" data-id="'
								+ aQuestion.qId
								+ '" style="float:right;" >'
								+ '<i style="color:rgba(189, 189, 41, 0.94);"class="fa fa-pencil-square-o"></i></a>'
								+ '<a class='
								+ "deleteQuestion"
								+ ' href="#'

								+ aQuestion.qId
								+ '" actionToBeDone="delete" data-id="'
								+ aQuestion.qId
								+ '" style="float:right;" >'
								+ '<i class="fa fa-trash-o" style="color:red"></i>'
								+ '</a>' + '</div>' + '</div>' + '</div>' + '</div>')
						$('#'+contianer).append(qHtml)

						var qBody = ('<div class= "panel panel-body test-container" id="o'
								+ aQuestion.qId + '" ><div class="row"><div class="clo-md-5">' + '<ol></ol></div>'+'<div class="col-md-7"></div></div></div>')
						$('#q' + aQuestion.qId).after(qBody)
						$('#o' + aQuestion.qId + ' > li').remove();
						$.each(aQuestion.qOptions, function(idx, option) {
							// alert(option.oName)
							$('.test-container#o' + aQuestion.qId + ' ol')
									.append('<li>' + option.name + '</li>')

						})
						$('#o' + aQuestion.qId).hide();
						
					})

}

function createQuestion() {
	var count = 0;
	$.ajax({
		type : 'POST',
		url : "http://localhost:8085/annotationBased/admin/saveQuestion",
		dataType : "json",
		contentType : "application/json",
		data : questionToJSON(),
		success : function() {
			$('tbody tr').remove();
			var index = $('#pagination').pagination('getCurrentPage');
			var show = $("#show option:selected").text();
			while (aaData.length > 0) {
				aaData.pop();
			}
			findAllQuestions()

		},
		error : function(msg) {
			alert("error while saving question");
		},
		
	});

}

jQuery(document)
		.ready(
				function($) {
					findAllOptionTypes();
						
						findAllQuestions();
					
					$('.side-nav').hide()
					
					$("#qType").on("change", function() {

						var qType = $('#qType option:selected').val();
						$('#options .row').hide();
						$("#" + qType).show();

					})

					$('#show-allquestion-table').click(function() {
						$('#question-table').show();
					})
					$('#add-option')
							.click(
									function() {
										var n = $('.added-option').length + 2;
										var box_to_be_added = $('<li class="list-group-item added-option">'
												+ '<div class=" col-md-2">'
												+ 'Option:'
												+ '<span class="box-number">'
												+ n
												+ '</span>'
												+ '</div>'
												+ '<div class=" col-md-8">'
												+ '<div class="input-group">'
												+ '<span class="input-group-addon">'
												+ '<input type="checkbox" name="listofanswers" class="rdo" aria-label="...">'
												+ '</span>'
												+ ' <input type="text" name="listofoptions" class="form-control txt" aria-label="...">'
												+ '</div>'
												+ '</div>'
												+ '<div class=" col-md-2">'
												+ '<button type="button" class="btn btn-primary" id="remove-option" >'
												+ 'remove'
												+ '</button>'
												+ '</div>' + '</li>' + '</ul>');
										if (8 < n) {
											alert('Stop it! you cannot give more then 8 option for a question');
											return false;
										}
										// box_to_be_added.hide();
										$('#option-box .list-group-item:last')
												.after(box_to_be_added);
										box_to_be_added.fadeIn('slow');
										return false;

									});
					$('.list-group').on(
							'click',
							'#remove-option',
							function() {
								$(this).parent().parent().css(
										'background-color', '#FF6C6C');
								// $(this).parent().fadeOut("slow", function() {

								$(this).parent().parent().remove();
								$('.box-number').each(function(index) {
									$(this).text(index + 2);
								});
								// });
								return false;
							});
					/* able---------------------------------- */

					$(Document).on('click', '.question', function() {
						var qId = $(this).attr('question-id');

						$('#o' + qId).toggle('fast')

					})
 $(Document).on('click','.deleteQuestion',function(){
	 var qId  = $(this).attr('data-id')
	 deleteQuestion(qId);
 })
  $(Document).on('click','.updateQuestion',function(){
	 var qId  = $(this).attr('data-id')
	 drawQuestionToUpdate(qId);
 })
				});

function questionToJSON() {

	var listOfAnswers = [];
	var listOfOptions = [];
	var options = $('.txt').toArray()
	var Answers = $('.rdo').toArray()

	$.each(options, function(idx, option) {

		listOfOptions.push(option.value)

	})

	$.each(Answers, function(idx, answer) {

		if (answer.checked == true) {
			listOfAnswers.push(idx + 1)

		}

	})
	var optionTpe=$('#oType option:selected').val();
	return JSON.stringify({
	
		"mainquestion":{
		"name" : $('#question').val(),
		"optionType" : optionTpe,
		
		},
	"listOfAnswers" : listOfAnswers,
	"listOfOptions" : listOfOptions
	});

}



function pagi(questions) {

	var show = $("#show option:selected").text();
	var totalData = questions.length;

	$('#pagination').pagination({
		items : totalData,
		itemsOnPage : show,
		cssStyle : 'light-theme',
		onPageClick : function(pageNumber, event) {
			var  contianer="allQuestionContianer";
			$('#'+contianer ).children().remove();
			var show = $("#show option:selected").text();
			drawQuestionList(show, pageNumber, questions, contianer);

			// Callback triggered when a page is clicked
			// Page number is given as an optional parameter
		}
	});
}
function findAllOptionTypes() {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/allOptionTypes",
		dataType : "json",

		success : renderOptionType,
	});
}

function renderOptionType(optionTypes){
	$.each(optionTypes,function(idx,optionType){
		$('#oType').append('<option>'+optionType+'</option>')
		
		
	})
	
	
}
function deleteQuestion(id) {

	$.ajax({
		type : 'DELETE',
		url : "http://localhost:8085/annotationBased/admin/deleteQuestion/"
				+ id,
		dataType : "json",

		success : function(result) {
		},
		complete : function() {
			$('#allQuestionContianer > div').remove();
			while (allQuestions.length > 0) {
				allQuestions.pop();
			}
			findAllQuestions();
		},
		error : function(msg) {
			alert("Error deleting !!!");
		}
	});

}
function drawQuestionToUpdate(id){
	
	var matches = $.grep(allQuestions, function(n, i) {

		return (n.qId == id)
	})
	var  question = matches[0] 
	$('#question').val(question.qName)
}