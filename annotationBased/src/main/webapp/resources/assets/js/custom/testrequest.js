
var testRequestId ;
var buttonStatus="PENDING";
var aaData=[];
jQuery(document)
		.ready(
				function($) {
					
					$(function() {
						$('#pagination').pagination({
							items : 2,
							itemsOnPage : show,
							cssStyle : 'light-theme'
						});
					});
					$('#show').on('change', function() {
						$('tbody tr').remove();

						var show = $('#show option:selected').val();
						var index = $('#pagination').pagination('getCurrentPage');

						showLimitedTestRequests(show, index,aaData);
						 
					});
					
					$('#submit').click(function() {
						   buttonStatus=$(this).children().attr("status");
						   findAllTestRequests(buttonStatus);
					});
					$('#confirm').click(function() {
						confirmOrRejectTestRequest(this);
					});
					$('#reject').click(function() {
						bootbox.prompt("Rejected Reason", function(reason) {                
							  
							confirmOrRejectTestRequest(this,reason);                         
							 
							});
					});

					$('form').submit(function() {
						alert($(this["options"]).val());
						return false;
					});

					$(document).on(	"click",".alert",
									function(e) {
										var link = $(this).attr("href");  
										e.preventDefault();
										bootbox	.confirm("Are you sure you would like to request for the test?",
														function(result) {
															if (result) {
																$.ajax({
																			type : 'GET',
																			url : link,
																			dataType : "json",

																			success : function(
																					msg) {
																				alert(msg);
																			},
																			error : function(
																					msg) {
																			}
																		});
															}
														});
									});

					$(document)
							.on(
									"click",
									".tRequest",
									function(e) {
										  testRequestId = $(this).attr(
												"data-id");
										$
												.ajax({
													type : 'GET',
													url : "http://localhost:8085/annotationBased/admin/testRequest/"
															+ testRequestId,
													dataType : "json",

													success : viewSingleTestRequest
												});
									});
				});

function findAllTestRequests(buttonStatus) {

	$
			.ajax({
				type : 'GET',
				url : "http://localhost:8085/annotationBased/admin/testRequests/"+buttonStatus,
				dataType : "json",

				success : renderAllTestRequests
			});

}
function renderAllTestRequests(testRequests) {
	
	
	
	$('#testRequestDetailPanel').hide();
	$('#showTest tr').remove();
	var show = $("#show option:selected").text();
	var index = $('#pagination').pagination('getCurrentPage');
	while (aaData.length > 0) {
		aaData.pop();
	}
	$.each(testRequests, function(idx, testRequest) {
		var oneTestRequest =testRequest;
		aaData.push(oneTestRequest);
		
		
	})
	var allData = aaData;
	showLimitedTestRequests(show,index,allData);
}

function viewSingleTestRequest(testRequest) {
	 $('.buttonPanel').show();
//	x.style.display = "visible	";
	$('#testRequestDetailPanel').show();
	$('#testRequestDetailPanel tr').remove();

	addValuesToRow("Test Request Id :", testRequest.id, "Test Name :",
			testRequest.testName);
	addValuesToRow("Initiated By :", testRequest.initiatedBy,
			"Test Requested Date :", testRequest.requestedDate);
	addValuesToRow("Requested Test :", testRequest.testName,
			"", "");
	
	if(testRequest.status=="REJECTED"){
		addValuesToRow("Rejected By :", testRequest.verifiedBy,
				"Rejected Reason :", testRequest.rejectedReason);
		addValuesToRow("Rejected Date :", testRequest.verifiedDate,
				" ", "");
		 $('.buttonPanel').hide();
	}
	else if(testRequest.status=="COMPLETED"){
	addValuesToRow("Completed By :", testRequest.verifiedBy,
			"Completed Date :", testRequest.verifiedDate);
	$('.buttonPanel').hide();
	}

}
var id = 0;
function addValuesToRow(firstColumnKeyName, firstColumnValue,
		secondColumnKeyName, secondColumnValue) {

	$('#testRequestContentPanel').append('<tr class="row' + id + '"></tr>');
	$('.row' + id).append(
			'<td ><div class="theKey">' + firstColumnKeyName
					+ '</div><div class="theValue">' + firstColumnValue
					+ '</div></td>');
	$('.row' + id).append(
			'<td ><div class="theKey">' + secondColumnKeyName
					+ '</div><div class="theValue">' + secondColumnValue
					+ '</div></td>');
	id++;
}
function confirmOrRejectTestRequest(element,reason) {

	
				$.ajax({
				type : 'POST',
				url : "http://localhost:8085/annotationBased/admin/testRequests/setStatus",
				dataType : "json",
				contentType : "application/json",
				data : testRequestToJSON(element,reason),
				success : function(msg) {
					alert(msg);
				},
				error : function(msg) {
					alert("error while saving question");
				}
			});

}
function testRequestToJSON(element,reason) {

	 
	
	var setStatusTo = $(element).attr("id");
	if(setStatusTo=="confirm"){
		setStatusTo="COMPLETED";
	}
	else {
		setStatusTo="REJECTED";
	}
	return JSON.stringify({
		
        "testRequestId": testRequestId ,
        "status": setStatusTo,
        "rejectedReason": reason
    });
	}
function showLimitedTestRequests(show, index,datas) {
	var initial = 0;
	var finale = show;

	if (index > 0) {

		initial = (index - 1) * show;
		finale = index * show;
		var limitedTestRequests = datas.slice(initial, finale);

	}

	else {
		var limitedTestRequests = datas.slice(initial, finale);
	}
	$('tbody tr').remove();
	$.each(limitedTestRequests,function(index,testRequest){
		
		$('#showTest').append(
				'<tr><td>' + testRequest.id + '</td><td>'
				+ testRequest.testName + '</td><td>'
				+ testRequest.initiatedBy + '</td><td>'
				+ testRequest.requestedDate + '</td><td>'
				+ '<a class="tRequest" data-id="' + testRequest.id
				+ '"' + ' href="#">'
				+ 'View Test Request</a></td></tr>')
	})
	pagi();
	 
	}
function pagi() {
	var show = $("#show option:selected").text();
	var totalData = aaData.length;
	$(function() {
		$('#pagination').pagination({
			items : totalData,
			itemsOnPage : show,
			cssStyle : 'light-theme'
		});
	});
}
	
