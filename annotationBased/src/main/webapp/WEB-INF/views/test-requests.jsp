<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">



		<div class="body">
			<div class="container">
				<h1>Create Test Requests</h1>
				<form role="form" action='../admin/createTest' method="post">
					<div class="form-group ">
						<label for="name">Name</label> <input type="text" name="name"
							class="form-control input-sm" />
					</div>
					<div class="form-group ">
						<label for="fullmark">Full Mark</label> <input type="text"
							name="fullmark" class="form-control input-sm" />
					</div>
					<div class="form-group ">
						<label for="passmark">Pass Mark</label> <input type="text"
							name="passmark" class="form-control input-sm" />
					</div>
					<div class="form-group ">
						<label for="testDate">Test Date</label> <input type="text"
							name="testDate" class="form-control input-sm" />
					</div>
					<div class="form-group ">

						<p>
							Is Equal Marking <input type="checkbox" name="equalMarking"
								value="true">
						</p>

					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary btn-sm">Submit</button>

					</div>
				</form>
				<script src="http://code.jquery.com/jquery-latest.min.js"></script>
				<script type="text/javascript" src="jquery.tabulate.js"></script>


			</div>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>
