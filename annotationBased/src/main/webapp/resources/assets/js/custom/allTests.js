
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
			tName : test.name
		}
		$('#showTest').append('<tr><td>'+test.id+
				'</td><td>'+test.name+'</td><td>'
				 +test.fullmark+'</td><td>' 
				 +test.passmark+'</td><td>'
				 +test.testDate+'</td><td>'
				 +test.duration+'</td><td>'
				 +test.testType+'</td><td>'+
				
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
$(document).ready(function (){
	$(document).on(	"click",".alert",function(e) {
				var todo = $(this).attr("actionToBeDone"); 
				var testId = $(this).attr("data-id"); 
				alert(testId);
				if(todo=="view"){
					showTest(testId);
					
				}
				else if(todo=="updateQ"){
					
				}
				else if(todo=="delete"){
					
				}
				else if(todo=="viewAllQ"){
	
}
	});
	$('#createTestPanel').hide();
	$('#createTestButton').on('click', function() {
		$('#createTestPanel').show();
		clearTestFormValues();
	})
});

function showTest(testId) {
	$('#createTestPanel').show();
	
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
	
}
function clearTestFormValues(){
	$('#id').val(0);
	$('#name').val("");
	$('#fullmark').val("");
	$('#passmark').val("");
	$('#testDate').val("");
	$('#duration').val("");
	
}
function createOrEditTest(){
	alert('must be hero');
	
}
 
