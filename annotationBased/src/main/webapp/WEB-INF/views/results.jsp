<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/admin/exam/result" var="resultUrl"/>
<c:url value="/users/downloadResult" var="downloadUrl"/>
<c:url value="/users/download/token" var="downloadTokenUrl"/>
<c:url value="/users/download/progress" var="downloadProgressUrl"/>
<c:url value="/users/create" var="addUrl"/>
<c:url value="/users/update" var="editUrl"/>
<c:url value="/users/delete" var="deleteUrl"/>
<c:url value="/users/downloadStudentResult" var="downloadStudentPdfResultUrl"/>

downloadStudentPdfResultUrl

<html>
<head>
	<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/resources/css/jquery-ui/pepper-grinder/jquery-ui-1.8.16.custom.css"/>'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/resources/css/ui.jqgrid-4.3.1.css"/>'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/resources/css/style.css"/>'/>
	
	<script type='text/javascript' src='<c:url value="/resources/js/jquery-1.6.4.min.js"/>'></script>
	<script type='text/javascript' src='<c:url value="/resources/js/jquery-ui-1.8.16.custom.min.js"/>'></script>
	<script type='text/javascript' src='<c:url value="/resources/js/grid.locale-en-4.3.1.js"/>'></script>
	<script type='text/javascript' src='<c:url value="/resources/js/jquery.jqGrid.min.4.3.1.js"/>'></script>
	<script type='text/javascript' src='<c:url value="/resources/js/custom.js"/>'></script>
	
	<title>User Records</title>
	
	<script type='text/javascript'>
	$(function() {
		
		var examKoId=(localStorage.getItem("hero"));
// 		alert(examKoId);
// Retrieve the object from storage
// 		var retrievedObject = localStorage.getItem('testObject');

// 		var datas= JSON.parse(retrievedObject);
		
// 		$.each(datas, function(key, value){
// 			    alert(key + ' = ' + value);
// 			});

		
		$("#grid").jqGrid({
		   	url:'${resultUrl}',
			datatype: 'json',
			mtype: 'GET',
		   	colNames:['Id', 'Name', 'obtained Score', 'Status','Remarks', 'Position','Action'],
		   	colModel:[
		   		{name:'id',index:'id', width:55, editable:false, editoptions:{readonly:true, size:10}, hidden:true},
		   		{name:'name',index:'name', width:100, editable:true, editrules:{required:true}, editoptions:{size:10}},
		   		{name:'obtainedScore',index:'email', width:100, editable:true, editrules:{required:false}, editoptions:{size:10}, edittype:'password', hidden:false},
		   		{name:'status',index:'username', width:100, editable:true, editrules:{required:true}, editoptions:{size:10}},
		   		{name:'remarks',index:'seatNumber', width:100, editable:true, editrules:{required:true}, editoptions:{size:10}},
		   		{name:'position',index:'assignedSet', width:100, editable:true, editrules:{required:true}, editoptions:{size:10}},
		   		{ name: 'action', align: 'center', width: 50, sortable: false
                    , title: false, fixed: true, search: false
                    , formatter: function (cellvalue, options, rowObject) {
                     var studentResultInfoId=rowObject.studentResultInfoId;
                     var stImageLinks = '<a href="#" onclick="downloadThis('+studentResultInfoId+')">Download</a> ';  
                     return stImageLinks;
                       }
                                  
}
		   		
// 		   		{name:'role',index:'role', width:50, editable:true, editrules:{required:true}, 
// 		   			edittype:"select", formatter:'select', stype: 'select', 
// 		   			editoptions:{value:"1:Admin;2:Regular"},
// 		   			formatoptions:{value:"1:Admin;2:Regular"}, 
// 		   			searchoptions: {sopt:['eq'], value:":;1:Admin;2:Regular"}}
		   	],
		   	postData: {examId:examKoId},
			rowNum:10,
		   	rowList:[10,20,40,60],
		   	height: 240,
		   	autowidth: true,
			rownumbers: true,
		   	pager: '#pager',
		   	sortname: 'id',
		    viewrecords: true,
		    sortorder: "asc",
		    caption:"Records",
		    emptyrecords: "Empty records",
		    loadonce: false,
		    loadComplete: function() {},
		    jsonReader : {
		        root: "rows",
		        page: "page",
		        total: "total",
		        records: "records",
		        repeatitems: false,
		        cell: "cell",
		        id: "id"
		    },
		    onCellSelect: function(rowid, index, contents, event)   {    
		    	   var cm = $("#grid").jqGrid('getGridParam','colModel');                          
		    	   if(cm[index].name == "action")
		    	   {
		    		   alert("jkjkjk");
// 		    		   $(this).html('<span>u r herooo</span>');
// 		    		   alert( fnName(rowid));
		    	      
		    	   }
		    	}
		});

		$("#grid").jqGrid('navGrid','#pager',
				{edit:false, add:false, del:false, search:false} 
		);
	 
 
		
		$("#grid").navButtonAdd('#pager',
				{ 	caption:"Pdf", 
					buttonicon:"ui-icon-arrowreturn-1-s", 
					onClickButton: downloadPdf,
					position: "last", 
					title:"", 
					cursor: "pointer"
				} 
			);
		
		$("#grid").navButtonAdd('#pager',
				{ 	caption:"Excel", 
					buttonicon:"ui-icon-arrowreturn-1-s", 
					onClickButton: downloadXls,
					position: "last", 
					title:"", 
					cursor: "pointer"
				} 
			);
		
	});

	function downloadXls() {download('xls');}
	
	function downloadPdf() {download('pdf');}
	
	function download(type) {
		// Retrieve download token
		// When token is received, proceed with download
		$.get('${downloadTokenUrl}', function(response) {
			// Store token
			var token = response.message[0];
			
			// Show progress dialog
			$('#msgbox').text('Processing download...');
			$('#msgbox').dialog( 
					{	title: 'Download',
						modal: true,
						buttons: {"Close": function()  {
							$(this).dialog("close");} 
						}
					});
			
			// Start download
			var examKoId=(localStorage.getItem("hero"));
			window.location = '${downloadUrl}'+'?token='+token+'&type='+type+'&examId='+examKoId;

			// Check periodically if download has started
			var frequency = 1000;
			var timer = setInterval(function() {
				$.get('${downloadProgressUrl}', {token: token}, 
						function(response) {
							// If token is not returned, download has started
							// Close progress dialog if started
							if (response.message[0] != token) {
								$('#msgbox').dialog('close');
								clearInterval(timer);
							}
					});
			}, frequency);
			
		});
	}
	
	function addRow() {
   		$("#grid").jqGrid('setColProp', 'username', {editoptions:{readonly:false, size:10}});
   		$("#grid").jqGrid('setColProp', 'password', {hidden: false});
   		$("#grid").jqGrid('setColProp', 'password', {editrules:{required:true}});
   		
		// Get the currently selected row
		$('#grid').jqGrid('editGridRow','new',
	    		{ 	url: '${addUrl}', 
					editData: {},
	                serializeEditData: function(data){ 
	                    data.id = 0; 
	                    return $.param(data);
	                },
				    recreateForm: true,
				    beforeShowForm: function(form) {
			            $('#pData').hide();  
			            $('#nData').hide();
			            $('#password',form).addClass('ui-widget-content').addClass('ui-corner-all');
				    },
					beforeInitData: function(form) {},
					closeAfterAdd: true,
					reloadAfterSubmit:true,
					afterSubmit : function(response, postdata) 
					{ 
				        var result = eval('(' + response.responseText + ')');
						var errors = "";
						
				        if (result.success == false) {
							for (var i = 0; i < result.message.length; i++) {
								errors +=  result.message[i] + "<br/>";
							}
				        }  else {
				        	$('#msgbox').text('Entry has been added successfully');
							$('#msgbox').dialog( 
									{	title: 'Success',
										modal: true,
										buttons: {"Ok": function()  {
											$(this).dialog("close");} 
										}
									});
		                }
				    	// only used for adding new records
				    	var newId = null;
				    	
						return [result.success, errors, newId];
					}
	    		});

   		$("#grid").jqGrid('setColProp', 'password', {hidden: true});
	} // end of addRow


	function editRow() {
   		$("#grid").jqGrid('setColProp', 'username', {editoptions:{readonly:true, size:10}});
   		$("#grid").jqGrid('setColProp', 'password', {hidden: true});
   		$("#grid").jqGrid('setColProp', 'password', {editrules:{required:false}});
   		
		// Get the currently selected row
		var row = $('#grid').jqGrid('getGridParam','selrow');
		
		if( row != null ) {
		
			$('#grid').jqGrid('editGridRow', row,
				{	url: '${editUrl}', 
					editData: {},
			        recreateForm: true,
			        beforeShowForm: function(form) {
			            $('#pData').hide();  
			            $('#nData').hide();
			        },
					beforeInitData: function(form) {},
					closeAfterEdit: true,
					reloadAfterSubmit:true,
					afterSubmit : function(response, postdata) 
					{ 
			            var result = eval('(' + response.responseText + ')');
						var errors = "";
						
			            if (result.success == false) {
							for (var i = 0; i < result.message.length; i++) {
								errors +=  result.message[i] + "<br/>";
							}
			            }  else {
			            	$('#msgbox').text('Entry has been edited successfully');
							$('#msgbox').dialog( 
									{	title: 'Success',
										modal: true,
										buttons: {"Ok": function()  {
											$(this).dialog("close");} 
										}
									});
		                }
				    	// only used for adding new records
				    	var newId = null;
			        	
						return [result.success, errors, newId];
					}
				});
		} else {
			$('#msgbox').text('You must select a record first!');
			$('#msgbox').dialog( 
					{	title: 'Error',
						modal: true,
						buttons: {"Ok": function()  {
							$(this).dialog("close");} 
						}
					});
		}
	}
	
	function downloadThis(studentResultInfoId){
		$.get('${downloadTokenUrl}', function(response) {
			// Store token
			var token = response.message[0];
			
			// Show progress dialog
			$('#msgbox').text('Processing download...');
			$('#msgbox').dialog( 
					{	title: 'Download',
						modal: true,
						buttons: {"Close": function()  {
							$(this).dialog("close");} 
						}
					});
			
			var DOWNLOAD_TYPE='pdf';
			// Start download
			var examKoId=(localStorage.getItem("hero"));
			window.location = '${downloadStudentPdfResultUrl}'+'?token='+token+'&type='+DOWNLOAD_TYPE+'&studentResultInfoId='+studentResultInfoId;

			// Check periodically if download has started
			var frequency = 1000;
			var timer = setInterval(function() {
				$.get('${downloadProgressUrl}', {token: token}, 
						function(response) {
							// If token is not returned, download has started
							// Close progress dialog if started
							if (response.message[0] != token) {
								$('#msgbox').dialog('close');
								clearInterval(timer);
							}
					});
			}, frequency);
			
		});

	}
	
	
	function deleteRow() {
		// Get the currently selected row
	    var row = $('#grid').jqGrid('getGridParam','selrow');

	    // A pop-up dialog will appear to confirm the selected action
		if( row != null ) 
			$('#grid').jqGrid( 'delGridRow', row,
	          	{	url:'${deleteUrl}', 
					recreateForm: true,
				    beforeShowForm: function(form) {
				    	//Change title
				        $(".delmsg").replaceWith('<span style="white-space: pre;">' +
				        		'Delete selected record?' + '</span>');
		            	//hide arrows
				        $('#pData').hide();  
				        $('#nData').hide();
				    },
	          		reloadAfterSubmit:true,
	          		closeAfterDelete: true,
	          		serializeDelData: function (postdata) {
		          	      var rowdata = $('#grid').getRowData(postdata.id);
		          	      // append postdata with any information 
		          	      return {id: postdata.id, oper: postdata.oper, username: rowdata.username};
		          	},
	          		afterSubmit : function(response, postdata) 
					{ 
			            var result = eval('(' + response.responseText + ')');
						var errors = "";
						
			            if (result.success == false) {
							for (var i = 0; i < result.message.length; i++) {
								errors +=  result.message[i] + "<br/>";
							}
			            }  else {
			            	$('#msgbox').text('Entry has been deleted successfully');
							$('#msgbox').dialog( 
									{	title: 'Success',
										modal: true,
										buttons: {"Ok": function()  {
											$(this).dialog("close");} 
										}
									});
		                }
				    	// only used for adding new records
				    	var newId = null;
			        	
						return [result.success, errors, newId];
					}
	          	});
		else {
			$('#msgbox').text('You must select a record first!');
			$('#msgbox').dialog( 
					{	title: 'Error',
						modal: true,
						buttons: {"Ok": function()  {
							$(this).dialog("close");} 
						}
					});
		}
	}
	
	
	
	</script>
</head>

<body>
	<h1 id='banner'>System Records</h1>
	
	<div id='jqgrid' style="width: 100%; height: 800px">
		<table id='grid'></table>
		<div id='pager'></div>
	</div>
	
	<div id='msgbox' title='' style='display:none'></div>
</body>
</html>