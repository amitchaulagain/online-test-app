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
	$('label.tree-toggler').click(function () {
		$(this).parent().children('ul.tree').toggle(300);
	});
	
	$('#roption-box ').hide();
	
$('#registerContainer').hide();
		

	//$(".date-picker").datepicker();

/*	$(".date-picker").on("change", function () {
	    var id = $(this).attr("id");
	    var val = $("label[for='" + id + "']").text();
	    $("#msg").text(val + " changed");
	});
*/
	
	
	
});	
