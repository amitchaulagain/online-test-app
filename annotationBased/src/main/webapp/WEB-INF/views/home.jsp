 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link href="<c:url value="/resources/css/library/bootstrap.min.css" />"/>

<link rel="stylesheet"
	href="<c:url value="/resources/css/custom/agency.css"/>" type='text/css'>

<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/resources/js/library/jquery.js" />"></script>
<script src="<c:url value="/resources/js/library/bootstrap.min.js" />"></script>





<!-- Custom Fonts -->
<link href="font-awesome-4.1.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Kaushan+Script'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700'
	rel='stylesheet' type='text/css'>
<title>Insert title here</title>
</head>
<body>

	<div class="container">

		<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header page-scroll">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand page-scroll" href="#page-top">Experimental
				</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="hidden"><a href="#page-top"></a></li>
					<li><a class="page-scroll" href="#About-Us">About Us</a></li>
					<li><a class="page-scroll" href="#portfolio">Portfolio</a></li>
					<li><a class="page-scroll" href="#about">About</a></li>
					<li><a class="page-scroll" href="#team">Team</a></li>
					<li><a class="page-scroll" href="#contact">Contact</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid --> </nav>


		<!-- ---------------head ------------------ -->

		<header>

		<div id="loginContainer" class="row ">
			<div class="col-sm-4" id="login-box">
				<div id="login-error">${error}</div>


				<!-- -----------------------login form ------------------------ -->


				<div class="intro-heading">login</div>
				<form action="/annotationBased/j_spring_security_check"
					method="post">

					<div class="input-group input-group  panel-body">
						<span class="input-group-addon"> <label for="j_username">Username</label>
						</span><input id="j_username" class="form-control" name="j_username"
							type="text" />

					</div>


					<div class="input-group input-group  panel-body">
						<span class="input-group-addon"> <label for="j_password">Password</label></span><input
							id="j_password" class="form-control" name="j_password"
							type="password" />
					</div>


					<div class="input-group input-group panel-body">
						<input type="submit" class="btn btn-primary btn-block "
							value="Login" id="login-btn" />
						<p>
							<a id=registerHere onclick="showRegisterUserView();" href="#">Not
								a user yet !!!!! register here</a>
					</div>
				</form>


			</div>
			<div class="col-sm-7">

				<div class="intro-text">
					<div class="intro-lead-in">Welcome To My App!</div>
					<div class="intro-heading">Online Test Application</div>
					<a href="#About-Us" class="page-scroll btn btn-xl">Tell Me More</a>
				</div>



			</div>


		</div>
		<div id="registerContainer" class="row " style="display: none;">
			<div class="col-sm-4" id="register-box">
				<div id="login-error">${error}</div>


				<!-- -----------------------login form ------------------------ -->


				<div class="intro-heading">Register User</div>
				<form action="/annotationBased/common/create"
					method="post">

					<div class="input-group input-group  panel-body">
						<span class="input-group-addon"> <label for="firstName">First Name :</label>
						</span><input class="form-control" name="firstName" type="text" />

					</div>
					<div class="input-group input-group  panel-body">
						<span class="input-group-addon"> <label for="Name">Last Name :</label>
						</span><input class="form-control" name="lastName" type="text" />

					</div>
					<div class="input-group input-group  panel-body">
						<span class="input-group-addon"> <label for="dob">Date
								Of Birth</label>
						</span><input class="form-control" name="dob" type="text" />

					</div>
					<div class="input-group input-group  panel-body">
						<span class="input-group-addon"> <label for="phoneNumber">Phone
								Number :</label>
						</span><input class="form-control" name="phoneNumber" type="text" />

					</div>
					<div class="input-group input-group  panel-body">
						<span class="input-group-addon"> <label for="email">Email
								:</label>
						</span><input class="form-control" name="email" type="email" />

					</div>
					<div class="input-group input-group  panel-body">
						<span class="input-group-addon"> <label for="address">Address
								:</label>
						</span><input class="form-control" name="address" type="text" />

					</div>

					<div class="input-group input-group  panel-body">
						<span class="input-group-addon"> <label for="country">Country
								:</label>
						</span><input class="form-control" name="country" type="text" />

					</div>
					<div class="input-group input-group  panel-body">
						<span class="input-group-addon"> <label for="username">Enter
								Username :</label>
						</span><input class="form-control" name="username" type="text" />

					</div>
					<div class="input-group input-group  panel-body">
						<span class="input-group-addon"> <label for="password">Enter
								password :</label>
						</span><input class="form-control" name="password" type="text" />

					</div>

					<div class="input-group input-group panel-body">
						<input type="submit" class="btn btn-primary btn-block "
							value="Register" id="register-btn" /> 
							<a href="#" id=signIn onclick="showSigninView()"> Click here to sign in !</a>
					</div>

				</form>
			</div>
			<div class="col-sm-7">

				<div class="intro-text">
					<div class="intro-lead-in">Welcome To My App!</div>
					<div class="intro-heading">Online Test Application</div>
					<a href="#About-Us" class="page-scroll btn btn-xl">Tell Me More</a>
				</div>



			</div>


		</div>
	</div>

	<!-- Services Section -->
	<section id="About-Us">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading">About Us</h2>
				<h3 class="section-subheading text-muted">Lorem ipsum dolor sit
					amet consectetur.</h3>
			</div>
		</div>
		<div class="row text-center">
			<div class="col-md-4">
				<span class="fa-stack fa-4x"> <i
					class="fa fa-circle fa-stack-2x text-primary"></i> <i
					class="fa fa-shopping-cart fa-stack-1x fa-inverse"></i>
				</span>
				<h4 class="service-heading">E-Commerce</h4>
				<p class="text-muted">Lorem ipsum dolor sit amet, consectetur
					adipisicing elit. Minima maxime quam architecto quo inventore harum
					ex magni, dicta impedit.</p>
			</div>
			<div class="col-md-4">
				<span class="fa-stack fa-4x"> <i
					class="fa fa-circle fa-stack-2x text-primary"></i> <i
					class="fa fa-laptop fa-stack-1x fa-inverse"></i>
				</span>
				<h4 class="service-heading">Responsive Design</h4>
				<p class="text-muted">Lorem ipsum dolor sit amet, consectetur
					adipisicing elit. Minima maxime quam architecto quo inventore harum
					ex magni, dicta impedit.</p>
			</div>
			<div class="col-md-4">
				<span class="fa-stack fa-4x"> <i
					class="fa fa-circle fa-stack-2x text-primary"></i> <i
					class="fa fa-lock fa-stack-1x fa-inverse"></i>
				</span>
				<h4 class="service-heading">Web Security</h4>
				<p class="text-muted">Lorem ipsum dolor sit amet, consectetur
					adipisicing elit. Minima maxime quam architecto quo inventore harum
					ex magni, dicta impedit.</p>
			</div>
		</div>
	</div>
	</section>





	<div class="row "></div>



	<script src="<c:url value="/resources/js/library/jquery.js" />"></script>

	<!-- Plugin JavaScript -->
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<!-- custum js -->
	<script src="<c:url value="/resources/js/custom/animate.js" />"></script>

</body>



</html>
