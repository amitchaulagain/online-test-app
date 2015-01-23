var aaData = [];

var allTestDatas=[];
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
	var finale = 5;

	var limitedQuestions = aaData.slice(initial, finale);

	var i = 0;
	$.each(	limitedQuestions,function(idx, aQuestion) {
						i++;
						$('#coll')
								.append(
										'<div class="panel-heading" role="tab" id="headingOne">'
												+ '<h4 data-toggle="collapse" data-parent="#accordion" href="#collapse'
												+ i
												+ '" aria-expanded="true" aria-controls="collapseOne" onclick="opt('
												+ aQuestion.qId,i
												+ ')">'
												+ aQuestion.qId
												+ '<span style="display:inline-block; width:20px;"></span>'
												+ aQuestion.qName + '</h4>'
												+ '</div>'

								)

					});

}
function opt(id, i) {
	alert(id + "===" + i)
}
function getOptionOfQuestion(id) {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8080/annotationBased/admin/questionOption/"
				+ id,
		dataType : "json",

		success : renderOptionsOfQuestion,
		error : function(msg) {
		}
	});
}
function renderOptionsOfQuestion(options) {
	var question;
	$('#ques li ').remove();
	$('#showquestion li').remove();
	$
			.each(
					options,
					function(idx, option) {

						question = option.question.name
						$('#showquestion')
								.append(
										'<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">'
												+ '<div class="panel-body">'
												+ '<li>'
												+ '<h4>'
												+ option.name
												+ '</h4>' + '</li>')
					})

	$('#ques').append('<li>' + '<h2>' + question + '</h2>' + '</li>')

}

jQuery(document).ready(function($) {
	  
     $(document).on("click", ".alert", function(e) {
    	 var link = $(this).attr("href"); // "get" the intended link in a var
         e.preventDefault();    
         bootbox.confirm("Are you sure you would like to request for the test?", function(result) {    
             if (result) {
            	 $.ajax({
            			type : 'GET',
            			url : link,
            			dataType : "json",

            			success : function(msg){
            				alert("Test Request sent successfully !! Be prepared for the test. Thank you .");
            			},
            			error : function(msg) {
            			}
            		});      
             }    
         });
     });
	var show = $("#show option:selected").text();
	var totalData = aaData.length;
	$(function() {
		$('#pagination').pagination({
			items : totalData,
			itemsOnPage : show,
			cssStyle : 'light-theme'
		});
	});
});



function findAllTests() {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/student/allTests",
		dataType : "json",

		success : renderAllTests
	});

}
function renderAllTests(tests) {
	$.each(tests, function(idx, test) {
		var oneTest = {
			tId : test.id,
			tName : test.name
		}
		$('#showTest').append('<tr><td>'+test.id+'</td>'+'<td>'+test.name+'</td><td>'+'<a class='+"alert"+' href="http://localhost:8085/annotationBased/student/send/testRequest/'
				
				+test.id+'">'+'Send Test Request</a></td></tr>') 

	})
	
	

}
