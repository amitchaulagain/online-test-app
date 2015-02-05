/**
 * New node file
 */

$(function() {
	$('a.page-scroll').bind('click', function(event) {
		var $anchor = $(this);
		$('html, body').stop().animate({
			scrollTop : $($anchor.attr('href')).offset().top
		}, 1500, 'easeInOutExpo');
		event.preventDefault();
	});
});



// Closes the Responsive Menu on Menu Item Click
$('.navbar-collapse ul li a').click(function() {
	$('.navbar-toggle:visible').click();
});
$('#registerHere').click(function () {
	
	$('#registerContainer').show();
	
})
// ----------admin page---------------

$(document).ready(function () {
	$('#drop-container').hide();
	$('label.tree-toggler').click(function () {
		$(this).parent().children('ul.tree').toggle(300);
	});
	
	$('#RADIO').hide();
	$('#create-form').hide();
$('#registerContainer').hide();
		

$('#create-form').click(function(){
$('#dynamic-option').load("experiment")
})
	//$(".date-picker").datepicker();

/*	$(".date-picker").on("change", function () {
	    var id = $(this).attr("id");
	    var val = $("label[for='" + id + "']").text();
	    $("#msg").text(val + " changed");
	});
*/
	
$('#dynamic-option').on('click','#new-field',function(){
	var field = $('#new-event').val()
		$('#drop-container').show();
	var count = $('#external-events > button').length;
	$('#external-events').append('<div class="btn btn-info btn-xs dragable" id="count'+count+1+'">'+field+'</div>')
	$(".dragable").draggable({cursor: 'move', containment: 'document',helper:'clone'})
	$('.dropable').droppable({ drop: handleDropEvent});
})
	
});	
function handleDropEvent( event, ui ) {
	  var draggable = ui.draggable;
	 var droppable = $(this).attr('id');
	 
	  alert( 'The square with ID "' + draggable.attr('id') +'' +droppable+'" was dropped onto me!' );
$(this).html('');
$('#'+droppable).removeAttr('class');
$('#'+droppable).attr('class','col-md-4');
$(this).append('<div class="form-group ">'
		+'<label for="field'+draggable.attr('id')+'" style="color: #777">'+draggable.html()+'</label>' 
		+'<input type="text" id="'+draggable.html()+'" data-key="'+draggable.html()+'"  class="form-control input-sm dynamic-option" />'
+'</div>')


}