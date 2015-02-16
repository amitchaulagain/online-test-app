var aaTest = [];
var testType;
var globalTestId;
var allQuestions = [];
function findAllTests() {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/allTests",
		dataType : "json",

		success : renderAllTests
	});

}
// ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::RENDER
// ALL TEST
function renderAllTests(tests) {

	$.each(tests, function(idx, test) {
		var oneTest = {
			tId : test.id,
			tName : test.name,
			tDate : test.createdDate,
			tPassmark : test.passmark,
			tFullmark : test.fullmark,
			tTestDate : test.testDate,
			tType : test.testType,
			tDuration : test.duration

		}
		aaTest.push(oneTest);

	})
	aaTest.reverse();
	pagi()
	var show = $("#show option:selected").text();
	var index = $('#pagination').pagination('getCurrentPage');

	showTest(show, index, aaTest)
}

// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::DOCUMENT>READY>FUNCTION
$(document).ready(function() {
	$('div .form-group .datepicker').datepicker({
	    format: 'mm/dd/yyyy',
	    startDate: '-3d'
	})
	// findAllQuestionsToAddOnTest();
	$('#createDOption').hide();
	$('#questions').css('display', 'none');
	allQuestions = findAllQuestionsToAddOnTest();

	$(document).on("click", "#createTest", function(e) {
		// TODO
		var step = $(this).attr("data-step");
		alert(step)
		var testType = $('#testPanel').attr('testType');
		if (step == "step1") {
			
			if (testType == "testTypeTwo" || testType == "testTypeFour") {
				$(this).attr("data-step", "step2");
				showCreateSetPanel();
			}

			else if (testType == "testTypeOne") {
			//	createOrEditTest();
				
				
			}
			else if (testType == "testTypeThree") {
				alert("To do")
				
			}
		} else if (step == "step2") {
			//createOrEditTest()
		} else if (todo == "step3") {
		//	createOrEditTest()
		} else if (todo == "viewAllQ") {

		}
	});

	
	
	
	
	$(document).on('click', '#cancle', function() {
		showDefaultView()

		
		
		
	})
	$('#show').on('change', function() {

		var show = $('#show option:selected').val();
		var index = $('#pagination').pagination('getCurrentPage');

		showTest(show, index, aaTest);
		pagi();
	});
	$(document).on("click", ".testTypes", function(e) {
		 $("#testDate").datepicker();
		testType = $(this).attr("id")
		$('#createTestPanel').show();
		$('#chooseTestOption').hide();
		if (testType == "testTypeOne") {
			$('#createTestPanel').load("createTestForm", function() {
				$('#drop-container').hide();
				$('#createDOption').hide();
				$('#addSet').hide();
				$('#drop-Header').hide();
				$('#addSections').hide();
				$('#testPanel').attr('testType', testType)

			});
		} else if (testType == "testTypeTwo") {
			$('#createTestPanel').load("createTestForm", function() {
				$('#drop-container').hide();
				$('#drop-Header').hide();
				$('#createDOption').hide();
				$('#addSections').hide();
				$('#testPanel').attr('testType', testType)
			});
		}

		else if (testType == "testTypeThree") {
			$('#createTestPanel').load("createTestForm", function() {
				$('#drop-container').hide();
				$('#drop-Header').hide();
				$('#createDOption').hide();
				$('#addSet').hide();
				$('#testPanel').attr('testType', testType)
			});
		} else if (testType == "testTypeFour") {
			$('#createTestPanel').load("createTestForm", function() {
				$('#drop-container').hide();
				$('#drop-Header').hide();
				$('#createDOption').hide();
				$('#testPanel').attr('testType', testType)
			});
		}
	});

	$(Document).on('click', '#addSet', function() {
		showCreateSetPanel();

	})
	$(Document).on('click', '#create-set-btn', function() {
		var setName = $('#createSetTxt').val();
		// alert(setName)
		drawSetTab(setName)

	})

	

	$(Document).on('click', '#closeTab', function() {
		var setName = $(this).attr('set-id');
		$('#tab' + setName).remove();
		$('#' + setName).remove();

	});

	$(Document).on('click', '#viewAllQuestionInTab', function() {

		showQuestionsToSelectForTest()
	});

});

function showSingleTest(testId) {
	testType = "";
	$('#createTestPanel').show();
	$('#createTestButton').html('Edit');
	$('#createTestButton').css("background-color", "green");

	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/viewTest/" + testId,
		dataType : "json",

		success : putTestValues
	});

}
function putTestValues(test) {
	$('#id').val(test.id);

	$('#name').val(test.name);
	$('#fullmark').val(test.fullmark);
	$('#passmark').val(test.passmark);
	$('#testDate').val(test.testDate);
	$('#duration').val(test.duration);

	$('#id').prop('disabled', true);
	$('#name').prop('disabled', true);
	$('#fullmark').prop('disabled', true);
	$('#passmark').prop('disabled', true);
	$('#duration').prop('disabled', true);
	$('#testDate').prop('disabled', true);
	$('#submit').prop('disabled', true);

}
function clearTestFormValues() {
	$('#id').val(0);
	$('#name').val("");
	$('#fullmark').val("");
	$('#passmark').val("");
	$('#testDate').val("");
	$('#duration').val("");

}
// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::: PAGINATION
// FUNCTION
function pagi() {
	var show = $("#show option:selected").text();
	var totalData = aaTest.length;
	// $(function() {
	$('#pagination').pagination({
		items : totalData,
		itemsOnPage : show,
		cssStyle : 'light-theme',
		onPageClick : function(pageNumber, event) {
			var show = $("#show option:selected").text();
			var index = $('#pagination').pagination('getCurrentPage');
			showTest(show, index, aaTest);
			// Callback triggered when a page is clicked
			// Page number is given as an optional parameter
		}
	});
	// });
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
		limitedTest = aaTest.slice(initial, finale);
	}
	$('#showTest tr td').remove();
	$
			.each(
					limitedTest,
					function(idx, test) {
						$('#showTest')
								.append(
										'<tr><td>'
												+ test.tId
												+ '</td><td>'
												+ test.tName
												+ '</td><td>'
												+ test.tFullmark
												+ '</td><td>'
												+ test.tPassmark
												+ '</td><td>'
												+ test.tTestDate
												+ '</td><td>'
												+ test.tDuration
												+ '</td><td width="20px">'
												// +test.tType+'</td><td>'

												+ '<a class='
												+ "alert"
												+ ' href="#'

												+ test.tId
												+ '" actionToBeDone="view" data-id="'
												+ test.tId
												+ '" >'
												+ '<i class="fa fa-eye" style="color:#00D000"></i></a>'
												+ '<a class='
												+ "alert"
												+ ' href="#" actionToBeDone="updateQ" data-id="'
												+ test.tId
												+ '" >'
												+ '<i style="color:rgba(189, 189, 41, 0.94);"class="fa fa-pencil-square-o"></i></a>'
												+ '<a class='
												+ "alert"
												+ ' href="#'

												+ test.tId
												+ '" actionToBeDone="delete" data-id="'
												+ test.tId
												+ '" >'
												+ '<i class="fa fa-trash-o" style="color:red"></i>'
												+ '</a>'
												+ '<a class='
												+ "alert"
												+ ' href="http://localhost:8085/annotationBased/admin/viewTest/'

												+ test.tId
												+ '" actionToBeDone="viewAllQ">'
												+ 'View All Q in test</a>'
												+ '</td></tr>')
					})

}

function showDefaultView() {
	$('#tabPanelPage').hide();
	$('#questions').css('display', 'none');
	$('#createTestPanel').hide();
	$('#chooseTestOption').show();
}

function hideAll() {
	$('#createTestPanel').hide();
	$('#chooseTestOption').hide();
}
function deleteTest(testId) {
	$.ajax({
		type : 'DELETE',
		url : "http://localhost:8085/annotationBased/admin/deleteTest/"
				+ testId,
		dataType : "json",

		success : function(result) {
			alert(result);
			findAllTests();
		},
		complete : function() {

		},
		error : function(msg) {
			alert("Error while deleting test !!!");
		}
	});
}
function hideTestPageAndLoadTestQuestionsPage() {
	$("#hero").load("testquestions");
	$('#testPage').hide();
	getTestTypeAndShowViewAccordingly(globalTestId);
}
function getTestTypeAndShowViewAccordingly(testId) {
	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/viewTest/" + testId,
		dataType : "json",

		success : showViewByTestType
	});
}
function showViewByTestType(data) {
	if (data.testType == "WITHOUT_SET_AND_SECTION") {
		alert("WITHOUT_SET_AND_SECTION");
	} else if (data.testType == "WITH_SET_AND_SECTION") {
		alert("WITH_SET_AND_SECTION");
	} else if (data.testType == "WITH_SET_ONLY") {
		$('#sectionName').hide();
	} else if (data.testType == "WITH_SECTION_ONLY") {
		$('#createSectionPanel').hide();
	}
}
function drawSetTab(setName) {

	var tab = '<li role="presentation" id="tab' + setName
			+ '"class="active"><a href="#' + setName
			+ '"aria-controls="home" role="tab" data-toggle="tab">' + setName
			+ '<i id="closeTab" set-id="' + setName
			+ '" class="fa fa-times"></i>' + '</a></li>';
	$('#myTab').append(tab);

	$('.active').removeClass('active');
	var tabPanel = '<div  role="tabpanel" class="tab-pane fade in active " id="'+setName+'" >'/*
			
			 */
			+ '</div>';
	$('.tab-content').append(tabPanel);
	$('.tab-pane').append('<ul id="tab-option" class="nav navbar-nav"></ul>');
	$('#tab-option').after(
	'<section id=selectedQuestionList ></section>')

	$('.tab-pane #tab-option')
			.append(
					'<li><a id=viewAllQuestionInTab>add Question from question bank<i class="icon icon-paste"></i></a>'
							+ '</li>'
							+ '<li><a >add your own Question</a>'
							+ '</li>');
	
}

function questionPagi() {

	$('#questionPagination').pagination({
		items : allQuestions.length,
		itemsOnPage : $("#showQuestion option:selected").text(),
		cssStyle : 'light-theme',
		onPageClick : function(pageNumber, event) {
			$('#allQuestionContianer > div').remove();
			var show = $("#showQuestion option:selected").text();
			var index = $('#questionPagination').pagination('getCurrentPage');
			drawQuestionList(show, index, allQuestions)

		}
	});

}

function showCreateSetPanel() {
	$('#createTestForm').hide();
	$('tabPanelPage').show();
	$('#createSetPanel').load('createSetTab');

}

function showQuestionsToSelectForTest() {
	$('.row #questions').css('display', 'block');

	questionPagi();
	var totalData = allQuestions;

	var show = $("#showQuestion option:selected").text();
	var index = $('#questionPagination').pagination('getCurrentPage');
	drawQuestionList(show, index, totalData)

}