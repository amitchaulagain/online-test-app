<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		/* $("#a").click(function(){
		    $("#panel").slideUp("slow");
		}); */
		var test = false;
		$("#flip").click(function() {
			test = !test;
			if (test)
				$("#panel").slideDown("slow");
			else
				$("#panel").slideUp("slow");
		});

	});
</script>

<style>
#panel, #flip {
	padding: 5px;
	text-align: center;
	background-color: #e5eecc;
	border: solid 1px #c3c3c3;
}

#panel {
	padding: 50px;
	display: none;
}
</style>
</head>
<body>

	<div id="flip">
		<h1>Click here to see the answers you got correct</h1>
	</div>
	<a href="#" id="a">ddddd</a>
	
	<div id="panel">Hello world!</div>
		<h1 style="color: green"> You are hero !!!!!!!!!!!!!!!!!!!</h1>
	
	
	<h1 style="color: green">Score : ${totalRightAnswers} time taken =${totaltime}m.s</h1>
</body>
</html>
