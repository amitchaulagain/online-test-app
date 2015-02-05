
var aaTest=[];
var testType;
var globalTestId;
function findAllTests() {

	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/allTests",
		dataType : "json",

		success : renderAllTests
	});

}
function renderAllTests(tests) {
	
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
$(document).ready(function (){
	$(document).on(	"click",".alert",function(e) {
				var todo = $(this).attr("actionToBeDone"); 
				var testId = $(this).attr("data-id"); 
				if(todo=="view"){
					showSingleTest(testId);
					
				}
				else if(todo=="updateQ"){
					globalTestId=$(this).attr("data-id"); 
					var testId = $(this).attr("data-id"); 
					hideTestPageAndLoadTestQuestionsPage();
						
						
//						$( "#page-wrapper" ).hide();
				}
				else if(todo=="delete"){
					deleteTest(testId);
					
				}
				else if(todo=="viewAllQ"){
	
}
	});
	$('#createTestPanel').hide();
	$('#createTestButton').on('click', function() {
     	$('#chooseTestOption').show();
     	 
     	
     	 if($(this).text().trim()=='Create'){
     		 $(this).html('Close');
     		 $(this).css("background-color","red");
     	 }
     	 else if($(this).text().trim()=='Edit'){
     		$('#id').prop('disabled', false);
     		$('#name').prop('disabled', false);
     		$('#fullmark').prop('disabled', false);
     		$('#passmark').prop('disabled', false);
     		$('#duration').prop('disabled', false);
     		$('#testDate').prop('disabled', false);
     		$('#submit').prop('disabled', false);
     		 $(this).html('Close');
     		 $(this).css("background-color","red");
     		 $('#chooseTestOption').hide();
     	 }
     	 else {
     		 hideAll();
     		 $(this).html('Create');
     		 $(this).css("background-color","#428bca");
     		 clearTestFormValues();
     	 }
     	 
	})
	$('#show').on('change', function() {

		var show = $('#show option:selected').val();
		var index = $('#pagination').pagination('getCurrentPage');

		showTest(show, index,aaTest);
		pagi();
	});
	$(document).on(	"click",".testTypes",function(e) {
		 testType=$(this).attr("id")
		 $('#createTestPanel').show();
		 $('#chooseTestOption').hide();
});
	
});

function showSingleTest(testId) {
	testType="";
	$('#createTestPanel').show();
	 $('#createTestButton').html('Edit');
	 $('#createTestButton').css("background-color","green");
	
	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/viewTest/"+testId,
		dataType : "json",

		success : putTestValues
	});
	
	
}
function putTestValues(test){
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
function clearTestFormValues(){
	$('#id').val(0);
	$('#name').val("");
	$('#fullmark').val("");
	$('#passmark').val("");
	$('#testDate').val("");
	$('#duration').val("");
	
}
function pagi() {
	var show = $("#show option:selected").text();
var totalData = 10;
	$(function() {
		$('#pagination').pagination({
			items : totalData,
			itemsOnPage : show,
			cssStyle : 'light-theme',
			onPageClick: function(pageNumber, event) {
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
		  limitedTest= aaTest.slice(initial, finale);
	}
	$('#showTest tr td').remove();
	$.each(limitedTest, function(idx, test) {
		$('#showTest').append('<tr><td>'+test.tId+
				'</td><td>'+test.tName+'</td><td>'
				 +test.tFullmark+'</td><td>' 
				 +test.tPassmark+'</td><td>'
				 +test.tTestDate+'</td><td>'
				 +test.tDuration+'</td><td>'
				 +test.tType+'</td><td>'+
				
				'<a class='+"alert"+' href="#'
				
				+test.tId+'" actionToBeDone="view" data-id="'+test.tId+'" >'+'View</a>'+'</td><td>'+
				'<a class='+"alert"+' href="#" actionToBeDone="updateQ" data-id="'+test.tId+'" >'+'Update Q</a>'+'</td><td>'+
				'<a class='+"alert"+' href="#'
				
				+test.tId+'" actionToBeDone="delete" data-id="'+test.tId+'" >'+'Delete</a>'+'</td><td>'+
				'<a class='+"alert"+' href="http://localhost:8085/annotationBased/admin/viewTest/'
				
				+test.tId+'" actionToBeDone="viewAllQ">'+'View All Q in test</a>'+'</td><td>'+
				
				'</td></tr>') 
	})

}
function createOrEditTest() {
	 $.ajax({      
	        type: 'POST',
	        url: "http://localhost:8085/annotationBased/admin/saveTest",
	        dataType: "json",
	        contentType:"application/json",
	        accept:"application/json",
	        data: testToJSON(),        
	        success:function(msg){
	        	findAllTests();
	        	showDefaultView();
	        	clearTestFormValues();
	        	
	        },
	        error:function(msg){
	        	alert("error while saving test");
	        }
	    });

}
function testToJSON() {
	var id=$('#id').val();
	var name=$('#name').val();
	var fullmark=$('#fullmark').val();
	var passmark=$('#passmark').val();
	var duration=$('#duration').val();
	var testDate=$('#testDate').val();
	return JSON.stringify({
		"id":id,
		"name" :  name,
		"fullmark" : fullmark,
		"passmark" :  passmark,
		"testType" : testType,
		"duration":duration,
		"testDate":testDate
	})

}

 function showDefaultView(){
	hideAll();
	 $('#createTestButton').html('Create');
		 $('#createTestButton').css("background-color","#428bca");
}
function hideAll(){
	$('#createTestPanel').hide();
	 $('#chooseTestOption').hide();
}
function deleteTest(testId){
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
function getTestTypeAndShowViewAccordingly(testId){
	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/viewTest/"+testId,
		dataType : "json",

		success : showViewByTestType
	});
}
function showViewByTestType(data){
	if(data.testType=="WITHOUT_SET_AND_SECTION"){
		alert("WITHOUT_SET_AND_SECTION");
	}
	else if(data.testType=="WITH_SET_AND_SECTION"){
		alert("WITH_SET_AND_SECTION");
	}
	else if(data.testType=="WITH_SET_ONLY"){
		$('#sectionName').hide();
	}
	else if(data.testType=="WITH_SECTION_ONLY"){
		$('#createSectionPanel').hide();
	}
}
