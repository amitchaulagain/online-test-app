var allQuestions = [];
var aaTest = []



// save-test save-test save-test save-test save-test save-test save-test
function findAllQuestionsToAddOnTest() {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/allQuestions",
		dataType : "json",

		success : renderQuestionsforTest
	});

}
function renderQuestionsforTest(questions) {

	$.each(questions, function(idx, question) {
		var oneQuestion = {
			qId : question.id,
			qName : question.name,
			qType : question.questionType,
			qDate : question.createdDate
		}
		allQuestions.push(oneQuestion);

	})
	drawQuestionList()

}

function createTest() {
	 $.ajax({      
	        type: 'POST',
	        url: "http://localhost:8085/annotationBased/admin/saveTest",
	        dataType: "json",
	        contentType:"application/json",
	        accept:"application/json",
	        data: testToJSON(),        
	        success:function(msg){
	        	findAllTEst();
	        	
	        },
	        error:function(msg){
	        	alert("error while saving question");
	        }
	    });

}

function testToJSON() {
	
	var name=$('#testname').val();
	var fullmark=$('#fullmark').val();
	var passmark=$('#passmark').val();
	var id=1;
	var testType="dffd";
	var duration="dffd";
	var testDate="dffd";
//	listOfQuestionForTest = questionToAdd;
	return JSON.stringify({
		"id":1,
		"name" :  name,
		"fullmark" : fullmark,
		"passmark" :  passmark,
		"testType" : testType,
		"duration":duration,
		"testDate":testDate
	})

}
// RETRIVE ALL TEST RETRIVE ALL TEST RETRIVE ALL TEST RETRIVE ALL TEST

function findAllTEst() {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/alltest",
		dataType : "json",

		success : renderTest
	});

}


var aaTrst = []

function renderTest(tests) {
	
	$.each(tests, function(idx, test) {
		var oneTest = {
			tId : test.id,
			tName : test.name,
			tDate : test.createdDate,
			tPassmark : test.passmark,
			tFullmark : test.fullmark,
			tTestDate :test.testDate,
			tType : test.testType,
			tDuration : test.duration
			
		}
		aaTest.push(oneTest);

	})
aaTest.reverse();
	pagi()
	var show = $("#show option:selected").text();
	var index = $('#pagination').pagination('getCurrentPage');
	
	showTest(show,index,aaTest)
}

function pagi() {
	var show = $("#show option:selected").text();
var totalData = aaTest.length;
	$(function() {
		$('#pagination').pagination({
			items : totalData,
			itemsOnPage : show,
			cssStyle : 'light-theme',
			onPageClick: function(pageNumber, event) {
					$('#showTest tr td').remove()
					var show = $("#show option:selected").text();
					var index = $('#pagination').pagination('getCurrentPage');
					showTest(show,index,aaTest);
					// Callback triggered when a page is clicked
					// Page number is given as an optional parameter
				}
		});
	});
}



function showTest(show,index,aTest) {
	var tInfo = []
	
	var initial = 0;
	var finale = show;

	if (index > 0) {

		initial = (index - 1) * show;
		finale = index * show;
		var limitedTest = aTest.slice(initial, finale);

	}

	else {
		var limitedTest= aaTest.slice(initial, finale);
	}
	$.each(limitedTest, function(idx, test) {
		var a = test
//		var qBody = ('<div class= "panel panel-body test-container" id="b' + idx + '" >'
//				+ '<div class="row">' + '<div class="col-lg-3">' + "pass:"
//				+ Test.tPassmark + '</div>' + '<div class="col-lg-3">'
//				+ "full:" + Test.tFullmark + '</div>' + '</div>' + '</div>'
//				+ '</div>' + '</div>')
//		tInfo.push(qBody)
//		var testbox = ('<div class="panel panel-default" >'
//				+ '<div class="panel-heading" role="tab" id="t'
//				+ idx
//				+ '">'
//				+ '<div class="row">'
//				+ '<div class="col-md-10">'
//				+ '<h4 class="panel-title">'
//				+ '<a class="testname" id="'
//				+ idx
//				+ '"data-toggle="collapse" data-parent="#accordion"href="#test'
//				+ Test.tId
//				+ '" aria-expanded="true"aria-controls="test'
//				+ Test.tId
//				+ '">'
//				+ Test.tName
//				+ ' </a>'
//				+ '</h4>'
//				+ '</div>'
//				+ '<div class="col-md-2">'
//				+ '<a class="update" id="'
//				+ Test.tId + '">' + "update" + '</a>' + '</div>' + '</div>')
		
		
		$('#showTest').append('<tr><td>'+test.tId+
				'</td><td>'+test.tName+'</td><td>'
				 +test.tFullmark+'</td><td>' 
				 +test.tPassmark+'</td><td>'
				 +test.tTestDate+'</td><td>'
				 +test.tDuration+'</td><td>'
				 +test.tType+'</td><td>'+
				
				'<a class='+"alert"+' href="#'
				
				+test.id+'" actionToBeDone="view" data-id="'+test.id+'" >'+'View</a>'+'</td><td>'+
				'<a class='+"alert"+' href="http://localhost:8085/annotationBased/admin/viewTest/'
				
				+test.id+'" actionToBeDone="updateQ">'+'Update Q</a>'+'</td><td>'+
				'<a class='+"alert"+' href="http://localhost:8085/annotationBased/admin/deleteTest/'
				
				+test.id+'" actionToBeDone="delete">'+'Delete</a>'+'</td><td>'+
				'<a class='+"alert"+' href="http://localhost:8085/annotationBased/admin/viewTest/'
				
				+test.id+'" actionToBeDone="viewAllQ">'+'View All Q in test</a>'+'</td><td>'+
				
				'</td></tr>') 
		

	})

}

function drawTestInfoContainer(id) {

	var outerBox = (' <div id="test'
			+ id
			+ '" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">'
			+ '<div class="panel-body">' + "sasasas" + '</div>' + '</div>')

	$("#t" + id).append(outerBox)
}
function drawQuestionList() {
	var count = 0;
	var questionToDraw = allQuestions
	$
			.each(
					questionToDraw,
					function(idx, aQuestion) {
						count = count + 1;
						var qHtml = ('<div class="panel panel-default">'
								+ '<div class="panel-heading" role="tab" >'
								+ '<div class="row">'
								+ '<div class="col-lg-1">'
								+ count
								+ '</div>'
								+ '<div class="col-lg-10">'
								+ '<h4 class="panel-title">'
								+ '<a data-toggle="collapse" data-parent="#accordion"href="#collapse" aria-expanded="true"aria-controls="collapseOne">'
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
						$('#panelContainer').append(qHtml)// .slideDown(500)
					})

}
function updateTest(id) {
	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/viewTest/" + id,
		dataType : "json",

		success : renderTestToUpdate

	});

}

var gTest;
function renderTestToUpdate(data) {
	var questions = [];
	$.each(data.questions, function(n, squestion) {
		var question = {
			id : squestion.id,
			name : squestion.name,
		}
		questions.push(question)
	})
	var a
	var test = {

		id : data.testSet.id,
		name : data.testSet.name,
		fullMark : data.testSet.fullmark,
		passMark : data.testSet.passmark,
		questions : questions

	}
	gTest = test;
	$
			.each(
					allQuestions,
					function(idx, question) {

						$
								.each(
										test.questions,
										function(idx, aQuestion) {

											var qHtml = ('<div class="panel panel-default">'
													+ '<div class="panel-heading" role="tab" >'
													+ '<div class="row">'
													+ '<div class="col-lg-1">'
													+ aQuestion.id
													+ '</div>'
													+ '<div class="col-lg-10">'
													+ '<h4 class="panel-title">'
													+ '<a data-toggle="collapse" data-parent="#accordion"href="#collapse" aria-expanded="true"aria-controls="collapseOne">'
													+ aQuestion.name
													+ ' </a>'
													+ '</h4>'
													+ '</div>'
													+ '<div class="col-lg-1">'
													+ '<a id="'
													+ idx
													+ '" class="del" style="color:rgb(179, 58, 58)">'

													+ '<i class="fa fa-minus-circle" style="color:red">del</i>'
													+ ' </a>'
													+ '</div>'
													+ '</div>' + '</div>' + '</div>')

											if (question.qId == aQuestion.id) {
												$('#questionsInTest').append(
														qHtml)
												a = aQuestion.id
												$('#selectedQuestion').show()
												 
												 questionToAdd.push(aQuestion.id)
												 
											}// .slideDown(500)
											// allquestions.pop(question[idx])
											
										})
						if (question.qId == a) {

							return true
						}
						var qhtml = ('<div class="panel panel-default">'
								+ '<div class="panel-heading" role="tab" >'
								+ '<div class="row">'
								+ '<div class="col-lg-1">'
								+ question.qId
								+ '</div>'
								+ '<div class="col-lg-10">'
								+ '<h4 class="panel-title">'
								+ '<a data-toggle="collapse" data-parent="#accordion"href="#collapse" aria-expanded="true"aria-controls="collapseOne">'
								+ question.qName
								+ ' </a>'
								+ '</h4>'
								+ '</div>'
								+ '<div class="col-lg-1">'
								+ '<a id="'
								+ question.qId
								+ '" class="addtotest">'
								+ '<i class="fa fa-plus-circle" style="color:rgb(34, 241, 34)"></i>'
								+ ' </a>' + '</div>' + '</div>' + '</div>' + '</div>')
						$('#questionsInTest').append(qhtml)// .slideDown(500)

					})
	$('#testname').val(test.name)
	$('#passmark').val(test.passMark)
	$('#fullmark').val(test.fullMark)
drawSelectedQuestion(questionToAdd)
}
function addQuestionToTest(id) {

	$.ajax({
		type : 'PUT',
		url : "http://localhost:8085/annotationBased/admin/updateTest/" + id,
		dataType : "json",
		contentType : "application/json",
		data : testToJSON(),
		success : function() {

		},
		error : function(msg) {
			alert("error while saving question");
		}
	});

}
function drawSelectedQuestion(questionToAdd) {
	var questionToDraw = allQuestions
	$.each(questionToAdd, function(idx, q) {
	var matches = $.grep(allQuestions, function(n,i){
			
			
			return(n.qId==q)
		})
		var na =matches[0]
		
		// $(this).replaceChild('i')
alert(na.qName)
		
			$('#selectedQuestion h1').after(
					'<li>' + '<h5 style="color:blue">' + na.qName + '</h5>'
							+ '</li>')

		
	})
}

var questionToAdd = []
function getTestInfo(id) {
	var tests = aaTest
	$.each(tests, function(idx, t) {

		if (q.qId == id) {
			$('#selectedQuestion').append(
					'<li>' + '<h4 style="color:blue">' + q.qName + '</h4>'
							+ '</li>')

		}
	})/*
		 * 
		 */

}

$(document).ready(function() {
	$('.side-nav').hide()
	$('.body').css({
		"margin" : "0px -18px 0px 0px"
		
	})
	$('#accordion').on('click', '.update', function() {
		$('#updateInput').show();
		var testIdToUpdate = $(this).attr('id')
		$('#chooseTestOption').hide()

		$('#formToCreateTest').show()
		updateTest(testIdToUpdate)
		
		$('#questionsInTest .panel-default').remove()
		// $('#updateInput').fadeOut("slow");
		//alert(testIdToUpdate)
		$('#selectedQuestion button').attr('id',testIdToUpdate)

	})

	$('#updateInput').hide();
	$('#selectedQuestion').hide();

	$('#panelContainer').hide();
	$('#formToCreateTest').hide();

	$('#panelContainer').on("click", ".addtotest", function() {

		var id = $(this).attr('id')
		// addQuestionToTest(id)
		

		questionToAdd.push(id)
		
		$('#selectedQuestion').show();
		drawSelectedQuestion(questionToAdd)

	})

	$('#questionsInTest').on("click", ".addtotest", function() {
		var id = parseInt($(this).attr('id'))
$('#selectedQuestion li').remove();
		$('#selectedQuestion').show();
		 questionToAdd.push(id)
		 drawSelectedQuestion(questionToAdd)
		 $(this).children("i").remove();
		 $(this).append('<i class="fa fa-minus-circle" style="color:red"></i>');
	})
$('#questionsInTest').on("click", ".del", function() {
		var id = $(this).attr('id')
		questionToAdd.pop(id)
	
alert(questionToAdd)
	})
	findAllQuestionsToAddOnTest()

	findAllTEst();
	
	
	$('#addQuestionInTest').on("click", function() {
		$("#accordion").hide();
		$(this).hide();
		
		$('#questionsInTest .panel-default').remove()
		$('#panelContainer').show();

	})
	$('#testTypeOne').on("click", function() {

		$('#chooseTestOption').hide()
		$('#formToCreateTest').show();
	})

	$('#accordion').on('click', '.testname', function() {

		var id = $(this).attr('id')
		$('#b'+id).toggle('fast')

	})
	$('#show').on('change', function() {
		$('#showTest tr td').remove();

		var show = $('#show option:selected').val();
		var index = $('#pagination').pagination('getCurrentPage');

		showTest(show, index,aaTest);
		pagi();
	});
	
	$('#selectedQuestion button').on('click',function(){
		var id=$(this).attr('id')
		addQuestionToTest(id)
		alert(id)
		
	})
})