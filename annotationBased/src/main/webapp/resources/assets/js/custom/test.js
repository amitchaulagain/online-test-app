var aaData = [];
function findAllQuestions() {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8080/annotationBased/admin/allQuestions",
		dataType : "json",

		success : renderQuestions
	});

}
function renderQuestions(questions) {

	$.each(questions, function(idx, question) {
		var oneQuestion = {
			qId : question.id,
			qName : question.name,
			qType : question.questionType
		}
		aaData.push(oneQuestion);

	})

	showLimitedQuestion();
}
function showLimitedQuestion() {
	var initial = 0;
	var finale = 5	;

	
		var limitedQuestions = aaData.slice(initial, finale);
	
	var i = 0;
	$
			.each(
					limitedQuestions,
					function(idx, aQuestion) {
						i++;
						$('#coll')
								.append(

										'<div class="panel-heading" role="tab" id="headingOne">'
	+ '<h4 data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapseOne" onclick="opt('+ aQuestion.qId,i+')">'
												
												+ aQuestion.qId
												+ '<span style="display:inline-block; width:20px;"></span>'
												+ aQuestion.qName + '</h4>'
												+ '</div>'

								)

					});

}
function opt(id,i){
alert(id+"==="+i)
}
function getOptionOfQuestion(id) {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8080/annotationBased/admin/questionOption/"+id,
		dataType : "json",

		success : renderOptionsOfQuestion,
        error:function(msg){
        }
	});
	}
 function renderOptionsOfQuestion(options){
	 var question;
	 $('#ques li ').remove();
		$('#showquestion li').remove();
	$.each(options,function(idx,option){
		
	
		 
		question=option.question.name
		$('#showquestion').append('<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">'
			      +'<div class="panel-body">'
+'<li>'+'<h4>'+option.name+'</h4>'+'</li>')
	})
	 
	 $('#ques').append('<li>'+'<h2>'+question+'</h2>'+'</li>')
	 
	 
 }

jQuery(document).ready(function($) {
var show = $("#show option:selected").text();
var totalData = aaData.length;
$(function() {
    $('#pagination').pagination({
        items: totalData,
        itemsOnPage: show,
        cssStyle: 'light-theme'
    });
});	
})