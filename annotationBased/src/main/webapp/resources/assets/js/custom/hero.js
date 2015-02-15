jQuery(document).ready(function($){
	
	$('#hero').on(	"click","#createSection",function(e) {
		$('#createSectionPanel').show();
//		  createSection(0,"ttttttt");
	});
	$(document).on(	"click","#submitSection",function(e) {
		var sectionName= $('#sectionName').val();
		  createSection(0,sectionName);
	});

			findAllSections();
		});
 
function createSection(sectionId,sectionName){
	
	$.ajax({
		type : 'POST',
		url : "http://localhost:8085/annotationBased/admin/createSection",
		dataType : "json",
		contentType : "application/json",
		data : sectionNameToJSON(sectionId,sectionName),
		success : function(msg) {
			findAllSections();
			$('#createSectionPanel').hide();
			alert(msg);
		},
		error : function(msg) {
			alert("error while saving section");
		}
	});
}

function sectionNameToJSON(sectionId,sectionName) {
	 
	return JSON.stringify({
		"sectionId": sectionId,
        "sectionName": sectionName ,
        "testId": globalTestId
    });
	}
function findAllSections() {
	$.ajax({
		type : 'GET',
		url : "http://localhost:8085/annotationBased/admin/viewSection/"+globalTestId,
		dataType : "json",

		success : showAllSections
	});
	 
	}
function showAllSections(sections) {
	$('#allSectionsPanel div').remove();
	$.each(sections, function(idx, section) {
	$('#allSectionsPanel').append(
			'<div class="' + 'col-sm-3">' + '<div class="'
					+ 'panel panel-green">' + '<div class="'
					+ 'panel-heading">' + '<h3 class="'
					+ 'panel-title">' + section.name+'..'+'<span style="margin-left:50px;"></span><i class="fa fa-pencil fa-fw btn default"></i><span style="margin-left:30px;"></span><i class="fa fa-trash-o fa btn btn-danger"></i></h3>'
					+ ' </div>   </div>   </div>');
	});
	}


