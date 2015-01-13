/**
 * 
 */

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


function getQuestionType() {
	$.ajax({
		type : 'get',
		url : "http://localhost:8085/annotationBased/admin/questionType",
		dataType : "json",

		success : renderQuestionType
	});

}function getOfQuestion(id) {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/questionAnswer/"+id,
		dataType : "json",

		success : renderOfQuestion,
        error:function(msg){
        	 
        }
	});
	}
function opt(id){
/*	*/
	
	getOptionOfQuestion(id);
 }

function getOptionOfQuestion(id) {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/questionOption/"+id,
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
		$('#showquestion').append('<li>'+'<h4>'+option.name+'</h4>'+'</li>')
	})
	 
	 $('#ques').append('<li>'+'<h2>'+question+'</h2>'+'</li>')
	 
	 
 }


function renderQuestionType(types) {

	$.each(types,function(idx, type) {
						$('#qType').append('<option>'+'<h5>'+type+'</h5>'+'</option>')
					});
}


	jQuery(document).ready(function($) {
		
		
		
		getQuestionType()	
					$('#show-question-console').click(function() {
						$('#tabs').tab();
						$('#tabs a:second').tab('show')
						$('#question-table').hide();
						$('#content').show()
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
	
	var qType=($('#qType :selected').text())
	return JSON.stringify({
		
        "questionType": qType,
        "questionName": $('#question').val(),
        "listOfOptions": listOfOptions,
        "listOfAnswers": listOfAnswers
        
       

    });
	
		
		
	}
	
	
	
		
	      
	


