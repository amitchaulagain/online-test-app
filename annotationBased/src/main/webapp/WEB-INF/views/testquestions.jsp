<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript"
	src="<c:url value="/resources/js/custom/hero.js"/>"></script>
<!-- <script type="text/javascript" -->
<%-- 	src="<c:url value="/resources/js/custom/hero.js"/>"></script> --%>
<!-- 	<script type="text/javascript" -->
<%-- 	src="<c:url value="/resources/js/library/bootbox.js"/>"></script> --%>
<div class="row">

	<div class="col-lg-10">
		<div style="float: right">
			<button id="createSection" class="btn btn-primary btn-sm">Create
				Section</button>
		</div>
		<div class="col-lg-10" id="createSectionPanel">

			<div  class="form-group ">
				<label for="sectionName">Section Name</label> <input id="sectionName" type="text"
					class="form-control input-sm" />
			</div>
			<div class="form-group ">
				<button id="submitSection" class="btn btn-primary btn-sm" >Create
				</button>
			</div>
		</div>
	</div>


</div>
<div class="row">
<div id="allSectionsPanel"></div>

</div>