var allQuestions = [];
var aaTest = [];
// Delete delete
function deleteTest(id) {

	$.ajax({
		type : 'DELETE',
		url : "http://localhost:8085/annotationBased/admin/deleteTest/"+id,
		dataType : "json",

		success : function(){
		while(aaTest.length>0){
			aaTest.pop()
			
		}
			$('#accordion .panel').remove();
			findAllTEst()}
	});

}

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
		type : 'POST',
		url : "http://localhost:8085/annotationBased/admin/createTest",
		dataType : "json",
		accept:"application/json",
		contentType : "application/json",
		data : testToJSON(),
		success : function() {
			alert("success")
		},
		complete : function(){
			findAllTEst()
			alert("OK")
			
		},	
	  error:function(){
		  
		 
	  }
	});

}

function testToJSON() {
	var dynamicField = $('.dynamic-option').toArray();
	var dynamicOptions = [];
$.each(dynamicField,function(idx,field){
	var aField = { 
			 dynamicOptionValue : $(this).val(),
			 dynamicOptionKey :$(this).attr('data-key')
	}
	dynamicOptions.push(aField)
})
	listOfQuestionForTest = questionToAdd;
	return JSON.stringify({

	 	"testSet" : {"name" : $('#testname').val(),
			"fullmark" : $('#fullmark').val(),
			"passmark" : $('#passmark').val()},
		"questionsInTest" : listOfQuestionForTest,
		"dynamicOptions" :  dynamicOptions
	
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
			tId : test.testSet.id,
			tName : test.testSet.name,
			tDate : test.testSet.createdDate,
			tPassmark : test.testSet.passmark,
			tFullmark : test.testSet.fullmark
		}
		aaTest.push(oneTest);

	})
	aaTest.reverse();
	pagi()
	var show = $("#show option:selected").text();
	var index = $('#pagination').pagination('getCurrentPage');

	showTest(show, index, aaTest)
}

function pagi() {
	var show = $("#show option:selected").text();
	var totalData = aaTest.length;
	$(function() {
		$('#pagination').pagination({
			items : totalData,
			itemsOnPage : show,
			cssStyle : 'light-theme',
			onPageClick : function(pageNumber, event) {
				$('#accordion .panel').remove()
				var show = $("#show option:selected").text();
				var index = $('#pagination').pagination('getCurrentPage');
				showTest(show, index, aaTest);
				// Callback triggered when a page is clicked
				// Page number is given as an optional parameter
			}
		});
	});
}

function showTest(show, index, aTest) {
	var tInfo = []

	var initial = 0;
	var finale = show;

	if (index > 0) {

		initial = (index - 1) * show;
		finale = index * show;
		var limitedTest = aTest.slice(initial, finale);

	}

	else {
		var limitedTest = aaTest.slice(initial, finale);
	}
	$.each(limitedTest, function(idx, Test) {
		var a = Test
		var qBody = ('<div class= "panel panel-body test-container" id="b'
				+ idx + '" >' + '<div class="row">' + '<div class="col-lg-3">'
				+ "pass:" + Test.tPassmark + '</div>'
				+ '<div class="col-lg-3">' + "full:" + Test.tFullmark
				+ '</div>' + '</div>' + '</div>' + '</div>' + '</div>')
		tInfo.push(qBody)
		var testbox = ('<div class="panel panel-default" >'
				+ '<div class="panel-heading" role="tab" id="t'
				+ idx
				+ '">'
				+ '<div class="row">'
				+ '<div class="col-md-8">'
				+ '<h4 class="panel-title">'
				+ '<a class="testname" id="'
				+ idx
				+ '"data-toggle="collapse" data-parent="#accordion"href="#test'
				+ Test.tId
				+ '" aria-expanded="true"aria-controls="test'
				+ Test.tId
				+ '">'
				+ Test.tName
				+ ' </a>'
				+ '</h4>'
				+ '</div>'
				+ '<div class="col-md-2">'
				+ '<a class="update" id="'
				+ Test.tId
				+ '">'
				+ "update"
				+ '</a>'
				+ '</div>'
				+ '<div class="col-lg-2">'
				+ '<buttob class="delete-test btn btn-warning" id="'
				+ Test.tId
				+ '" style="color:red">delete</button>'
				+ '</div>' + '</div>')

		$('#accordion').append(testbox)
		$('#t' + idx).after(tInfo[idx])
		$('#b' + idx).hide()
		// $('#accordion div').attr("id", "test" + idx)
		/*
		 * $('#test' + idx + ' .panel-heading a h4 ').html( Test.name)
		 */
		// $(tInfo[idx]).hide()
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
		url : "http://localhost:8085/annotationBased/admin/viewTest/"+id,
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

													+ '<i class="fa fa-times" id="'+aQuestion.id+'" data-testid="'+test.id+'" style="color:red"></i>'
													+ ' </a>'
													+ '</div>'
													+ '</div>' + '</div>' + '</div>')

											if (question.qId == aQuestion.id) {
												$('#questionsInTest').append(
														qHtml)
												a = aQuestion.id
												$('#selectedQuestion').show()
												var oneQuestion = {
													id : parseInt(aQuestion.id)
												}
												questionToAdd.push(oneQuestion)

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
								+ '<i class=" fa fa-plus-circle" id="'+question.qId+'" data-testid="'+test.id+'" style="color:rgb(34, 241, 34)"></i>'
								+ ' </a>' + '</div>' + '</div>' + '</div>' + '</div>')
						$('#questionsInTest').append(qhtml)// .slideDown(500)

					})
	$('#testname').val(test.name)
	$('#passmark').val(test.passMark)
	$('#fullmark').val(test.fullMark)
	drawSelectedQuestion(questionToAdd)
}
function addQuestionToTest(tId,qId) {

	$.ajax({
		type : 'PUT',
		url : "http://localhost:8085/annotationBased/admin/updateTest/"+tId+"/"+qId,
		dataType : "json",
		contentType : "application/json",
		data : testToJSON(),
		success : function(msg) {
			alert(msg)

		},
		error : function(msg) {
			alert("error while saving question");
		}
	});

}
function drawSelectedQuestion(questionToAdd) {
	var questionToDraw = allQuestions
	$('#selectedQuestion li').remove()
	$.each(questionToAdd, function(idx, q) {
	
		var matches = $.grep(allQuestions, function(n, i) {

			return (n.qId == q.id	)
		})
		var na = matches[0]
		
		// $(this).replaceChild('i')

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

function deleteQuestionfromTest(tId,qId) {

	$.ajax({
		type : 'DELETE',
		url : "http://localhost:8085/annotationBased/admin/deleteQuestionFromTest/"+tId+"/"+qId,
		dataType : "json",
		success : function(msg) {
			

		},
		error : function(msg) {
			
		}
	});

}

$(document).ready(function() {
	$('.side-nav').hide()
	$('#updateInput').hide();
	
	$('#accordion').on('click', '.update', function() {
		$('#updateInput').show();
		var testIdToUpdate = $(this).attr('id')
		$('#chooseTestOption').hide()
	$('#questionsInTest .panel-default').remove()
		$('#formToCreateTest').show()
		updateTest(testIdToUpdate)
    $('dynamic-option').parent().hide()
	
		// $('#updateInput').fadeOut("slow");
		// alert(testIdToUpdate)
		$('#selectedQuestion button').attr('id', testIdToUpdate)

	})

	$('#selectedQuestion').hide();

	$('#panelContainer').hide();
	$('#formToCreateTest').hide();
	$('.body').css({
		"margin" : "0px -18px 0px 0px"

	})

	$('#panelContainer').on("click", ".addtotest", function() {

		var oneQuestion = {

			id : $(this).attr('id')
		// addQuestionToTest(id)
		}

		questionToAdd.push(oneQuestion)

		$('#selectedQuestion').show();
		drawSelectedQuestion(questionToAdd)

	})

	$('#questionsInTest').on("click", ".addtotest", function() {
		var qId = parseInt($(this).children('i').attr('id'))
		var tId = parseInt($(this).children('i').attr('data-testid'))
		$('#selectedQuestion li').remove();
		$('#selectedQuestion').show();
		var q={
				id:qId
			}
	     questionToAdd.push(q)
		drawSelectedQuestion(questionToAdd)
		$(this).children("i").remove();
		$(this).append('<i class="fa fa-times" id="'+qId+'" data-testid="'+tId+'" style="color:red"></i>');
		$(this).attr('class','del')
		addQuestionToTest(tId,qId)
		alert(qId+","+tId)
	})
	$('#questionsInTest').on("click", ".del", function() {
		var qId = parseInt($(this).children('i').attr('id'))
		var tId = parseInt($(this).children('i').attr('data-testid'))
		var matches = $.grep(questionToAdd, function(n, i) {
			return (n.id == qId	)
		})
		var na = matches[0]
		var d = questionToAdd.indexOf(na)
	  questionToAdd.splice(d,1);
	  drawSelectedQuestion(questionToAdd)
       deleteQuestionfromTest(tId,qId)
       $(this).children('i').remove();
		$(this).append('<i class=" fa fa-plus-circle" id="'+qId+'" data-testid="'+tId+'" style="color:rgb(34, 241, 34)"></i>')
		$(this).attr('class','addtotest')
		alert(qId+","+tId)
	})
	findAllQuestionsToAddOnTest()

	findAllTEst();

	$('#addQuestionInTest').on("click", function() {
		 $('#dynamic-option').parent().hide()
		$("#accordion").hide();
		$(this).hide();

		$('#questionsInTest .panel-default').remove()
		$('#panelContainer').show();

	})
	$('#testTypeOne').on("click", function() {
		$('#create-form').show	();
		$('#chooseTestOption').hide()
		$('#formToCreateTest').show();
	})

	$('#accordion').on('click', '.testname', function() {

		var id = $(this).attr('id')
		$('#b' + id).toggle('fast')

	})
	$('#show').on('change', function() {
		$('#accordion .panel').remove();

		var show = $('#show option:selected').val();
		var index = $('#pagination').pagination('getCurrentPage');

		showTest(show, index, aaTest);
		pagi();
	});

	$('#selectedQuestion button').on('click', function() {
		var id = $(this).attr('id')
		var q={
			id:id
		}
		addQuestionToTest(q)
		alert(id)

	})
	$('#accordion').on('click', '.delete-test', function() {
alert()
		var id = $(this).attr('id')
		 deleteTest(id);
	})
	
	
})