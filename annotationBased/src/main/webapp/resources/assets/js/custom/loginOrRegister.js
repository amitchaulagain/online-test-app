jQuery(document).ready(function($){
	$('#registerContainer').hide();
});

function showRegisterUserView(){
	$('#registerContainer').show();
	$('#loginContainer').hide();
}
function showSigninView(){
	$('#registerContainer').hide();
	$('#loginContainer').show();
}