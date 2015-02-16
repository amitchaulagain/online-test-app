/**
 * 
 */
var allQuestions = [];
function findAllQuestionsToAddOnTest() {
	
	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/allQuestions",
		dataType : "json",

		success : renderQuestionsforTest
	});
	return allQuestions;
}
function renderQuestionsforTest(questions) {
	
	$.each(questions, function(idx, q) {
		var options=[];
		$.each(q.options, function(idx, o){
			var option ={
					id:o.Id,
					name:o.name
			}
			options.push(option)
		})
		var oneQuestion = {
			qId : q.mainquestion.id,
			qName : q.mainquestion.name,
			qType : q.mainquestion.questionType,
			qDate : q.mainquestion.createdDate,
			qOptions:options
		}
		allQuestions.push(oneQuestion);

	})


}
function drawQuestionList(show, index, questions) {
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
	alert(limitedQuestion.length)
	$
			.each(
					limitedQuestion,
					function(idx, aQuestion) {
						count = count + 1;
						var qHtml = ('<div class="panel panel-default">'
								+ '<div class="panel-heading" role="tab" id="q'+aQuestion.qId+'" >'
								+ '<div class="row">'
								+ '<div class="col-lg-1">'
								+ count
								+ '</div>'
								+ '<div class="col-lg-10">'
								+ '<h4 class="panel-title" >'
								+ '<a data-toggle="collapse" class="question" data-parent="#accordion"href="#collapse" question-id="'+aQuestion.qId+'" aria-expanded="true"aria-controls="collapseOne">'
								+ aQuestion.qName
								+ ' </a>'
								+ '</h4>'
								+ '</div>'
								+ '<div class="col-lg-1">'
								+ '<a id="'
								+ aQuestion.qId
								+ '" class="addtotest">'
								+ '<i class="fa fa-plus-circle" style="color:rgb(34, 241, 34)"></i>'
								+ ' </a>' + '</div>' + '</div>' + '</div>' + '</div>')
						$('#allQuestionContianer').append(qHtml)
						
						
						
						var qBody = ('<div class= "panel panel-body test-container" id="o'
								+ aQuestion.qId + '" >'
								 + '<ol></ol></div>')
								 $('#q'+aQuestion.qId).after(qBody)
								 $('#o'+aQuestion.qId+' > li').remove();
						$.each(aQuestion.aOptions,function(idx,option){
							//alert(option.oName)
							$('.test-container#o'+aQuestion.qId+' ol').append('<li>'+option.oName+'</li>')
							
						})
						 $('#o'+aQuestion.qId).hide();
						while(options.length>0){
							options.pop();
							
						}
					})

}

function createQuestion() {
 var count=0;
    $.ajax({      
        type: 'POST',
        url: "http://localhost:8085/annotationBased/admin/saveQuestion",
        dataType: "json",
        contentType:"application/json",
        data: questionToJSON(),        
        success:function(){
        	 $('tbody tr').remove();
        		var index= $('#pagination').pagination('getCurrentPage');
        		var show = $("#show option:selected").text();
        		while(aaData.length>0){
        		aaData.pop();
        		}
        		findAllQuestions()
        	
        },
        error:function(msg){
        	alert("error while saving question");
        }
    });
   
   
	
	
}




	jQuery(document).ready(function($) {
		$('.side-nav').hide()
		 loadAllQuestions();  
		$("#qType").on("change",function(){
			
			var qType=$('#qType option:selected').val();
			$('#options .row').hide();
			$("#"+qType).show();
				
		})
		
		
		
		
					
					$('#show-allquestion-table').click(function() {
						$('#question-table').show();
					})
					$('#add-option').click(function() {
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
										$('#option-box .list-group-item:last').after(
												box_to_be_added);
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
					/*able----------------------------------*/
				


					   $(Document).on('click','.question',function(){
						  var qId= $(this).attr('question-id');
						 
						  $('#o'+qId).toggle('fast')
						 
						   
					   })
					 
				});
	
	
	function questionToJSON() {
		
		var listOfAnswers=[];
		var listOfOptions=[];
		var options=$('.txt').toArray()
	var Answers = $('.rdo').toArray()
	
	$.each(options, function(idx,option){
	 
		
			listOfOptions.push(option.value)
			
		
		
	})
	
	$.each(Answers, function(idx,answer){
		
		if(answer.checked==true){
			listOfAnswers.push(idx+1)
			
		}
		
	})
	
	
	return JSON.stringify({
		
        "questionName": $('#question').val(),
        "questionType": $('#qtype option:selected').val(),
        "listOfAnswers": listOfAnswers,
        "listOfOptions": listOfOptions

    });
	
		
		
	}
	
	
	
	function loadAllQuestions(){
		
		var questions = findAllQuestionsToAddOnTest();
		pagi(questions);
		var show = $("#show option:selected").text();
		var index = $('#pagination').pagination('getCurrentPage');
		drawQuestionList(show, index, questions);
		
	}
		
	function pagi(questions) {
		alert("ewewe")
		var show = $("#show option:selected").text();
		var totalData =questions.length ;
		
			$('#pagination').pagination({
				items : totalData,
				itemsOnPage : show,
				cssStyle : 'light-theme',
					onPageClick: function(pageNumber, event) {
						$('tbody tr').remove();
					
						var show = $("#show option:selected").text();
						drawQuestionList(show, pageNumber,questions);
						
						// Callback triggered when a page is clicked
						// Page number is given as an optional parameter
					}	
		});
	}


