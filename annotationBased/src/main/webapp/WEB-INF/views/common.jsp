<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link href="<c:url value='/resources/css/library/bootstrap.min.css'/>" rel="stylesheet"/>

<title>Welcome to common page</title>
</head>
<body>
	<h1>This is common page</h1>

<div class="container">

<div class="row-fluid">
	<div class="span12">
		Fluid 12
		<div class="row-fluid">
			<div class="span6">
				Fluid aaa
				<div class="row-fluid">
					<div class="span6"><a href="../student/viewTests${tests.id}" >students url    </a></div>
					<div class="span6">Fluid 6ggggg</div>
				</div>
			</div>
			<div class="span6">Fluid gggggg6</div>
		</div>
	</div>
</div>

</div>
${mm}
</body>
</html>