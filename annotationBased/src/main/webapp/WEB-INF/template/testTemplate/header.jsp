<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<link
	href="<c:url value="/resources/css/library/simplePagination.css" />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/library/bootstrap.min.css'/>"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="<c:url value='/resources/css/custom/sb-admin.css'/>"
	rel="stylesheet">
<link href="<c:url value='/resources/css/custom/agency.css'/>"
	rel="stylesheet">
<!-- Morris Charts CSS -->
<link href="<c:url value='/resources/css/library/plugins/morris.css'/>"
	rel="stylesheet">
<link
	href="<c:url value='/resources/css/library/plugins/datepicker.css'/>"
	rel="stylesheet">
<link
	href="<c:url value='/resources/font-awesome-4.1.0/css/font-awesome.min.css'/>"
	rel="stylesheet">
<script type="text/javascript"
	src="<c:url value="/resources/js/library/jquery.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/library/bootstrap.min.js"/>"></script>


<div class="header">
	<nav id="top-navigation" class="navbar navbar-inverse navbar-fixed-top"
		role="navigation"> <!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-ex1-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="index.html">Admin Dashboard</a>
	</div>
	<!-- Top Menu Items -->
	<ul class="nav navbar-right top-nav">
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"><i class="fa fa-envelope"></i> <b
				class="caret"></b></a>
			<ul class="dropdown-menu message-dropdown">
				<li class="message-preview"><a href="#">
						<div class="media">
							<span class="pull-left"> <img class="media-object"
								src="http://placehold.it/50x50" alt="">
							</span>
							<div class="media-body">
								<h5 class="media-heading">
									<strong>John Smith</strong>
								</h5>
								<p class="small text-muted">
									<i class="fa fa-clock-o"></i> Yesterday at 4:32 PM
								</p>
								<p>Lorem ipsum dolor sit amet, consectetur...</p>
							</div>
						</div>
				</a></li>
				<li class="message-preview"><a href="#">
						<div class="media">
							<span class="pull-left"> <img class="media-object"
								src="http://placehold.it/50x50" alt="">
							</span>
							<div class="media-body">
								<h5 class="media-heading">
									<strong>John Smith</strong>
								</h5>
								<p class="small text-muted">
									<i class="fa fa-clock-o"></i> Yesterday at 4:32 PM
								</p>
								<p>Lorem ipsum dolor sit amet, consectetur...</p>
							</div>
						</div>
				</a></li>
				<li class="message-preview"><a href="#">
						<div class="media">
							<span class="pull-left"> <img class="media-object"
								src="http://placehold.it/50x50" alt="">
							</span>
							<div class="media-body">
								<h5 class="media-heading">
									<strong>John Smith</strong>
								</h5>
								<p class="small text-muted">
									<i class="fa fa-clock-o"></i> Yesterday at 4:32 PM
								</p>
								<p>Lorem ipsum dolor sit amet, consectetur...</p>
							</div>
						</div>
				</a></li>
				<li class="message-footer"><a href="#">Read All New
						Messages</a></li>
			</ul></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"><i class="fa fa-bell"></i> <b
				class="caret"></b></a>
			<ul class="dropdown-menu alert-dropdown">
				<li><a href="#">Alert Name <span
						class="label label-default">Alert Badge</span></a></li>
				<li><a href="#">Alert Name <span
						class="label label-primary">Alert Badge</span></a></li>
				<li><a href="#">Alert Name <span
						class="label label-success">Alert Badge</span></a></li>
				<li><a href="#">Alert Name <span class="label label-info">Alert
							Badge</span></a></li>
				<li><a href="#">Alert Name <span
						class="label label-warning">Alert Badge</span></a></li>
				<li><a href="#">Alert Name <span class="label label-danger">Alert
							Badge</span></a></li>
				<li class="divider"></li>
				<li><a href="#">View All</a></li>
			</ul></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"><i class="fa fa-user"></i> John Smith <b
				class="caret"></b></a>
			<ul class="dropdown-menu">
				<li><a href="#"><i class="fa fa-fw fa-user"></i> Profile</a></li>
				<li><a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
				</li>
				<li><a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a></li>
				<li class="divider"></li>
				<li><a href="#"><i class="fa fa-fw fa-power-off"></i> Log
						Out</a></li>
			</ul></li>
	</ul>
	</nav>


</div>