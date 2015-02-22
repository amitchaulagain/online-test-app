 
var aaStudent=[];
$(document).ready(	function() {
	 findAllStudents();
	 
	 });
 
 function findAllStudents() {

		$.ajax({
			type : 'GET',
			url : "http://localhost:8085/annotationBased/admin/viewAllStudents",
			dataType : "json",

			success : renderStudent
		});

	}
 function renderStudent(students) {

		$.each(students, function(idx, student) {
			var oneStudent = {
				sId : student.id,
				sName : student.firstName+ " " +student.lastName,
				sEmail : student.email,
				sAddress :student.address,
				sCountry :student.country,
				sPhoneNumber :student.phoneNumber,
				sUsername : student.username,
				sPassword :student.password,
				sDateOfBirth :student.dateOfBirth,
			}
			aaStudent.push(oneStudent);

		})
		aaStudent.reverse();
		pagi()
		var show = $("#showStudentPerPage option:selected").text();
		var index = $('#studentPagination').pagination('getCurrentPage');

		showExam(show, index, aaStudent)
	}
 function pagi() {
		var show = $("#showStudentPerPage option:selected").text();
		var totalData = aaStudent.length;
		$(function() {
			$('#studentPagination').pagination({
				items : totalData,
				itemsOnPage : show,
				cssStyle : 'light-theme'
			});
		});
	}
 function showExam(show, index, students) {
		var initial = 0;
		var finale = show;

		if (index > 0) {

			initial = (index - 1) * show;
			finale = index * show;
			var limitedStudent = students.slice(initial, finale);

		}

		else {
			limitedStudent = students.slice(initial, finale);
		}
		$('#showStudent tr td').remove();
		$.each(limitedStudent,	function(idx, student) {
							$('#showStudent').append(
											'<tr><td>'
													+ student.sName
													+ '</td><td>'
													+ student.sUsername
													+ '</td><td>'
													+ student.sPassword
													+ '</td><td>'
													+ student.sEmail
													+ '</td><td>'
													+ student.sPhoneNumber
													+ '</td><td>'
													+ student.sAddress
													+ '</td><td>'
													+ student.sCountry
													+ '</td><td>'
													+ student.sDateOfBirth
													+ '</td><td width="20px">'
													// +test.tType+'</td><td>'
													+ '<a class='
													+ "alert"
													+ ' href="#'
													+ student.sId
													+ '" actionToBeDone="view" data-id="'
													+ student.sId
													+ '" >'
													+ '<i class="fa fa-eye" style="color:#00D000"></i></a>'
													+ '<a class='
													+ "alert"
													+ ' href="#" actionToBeDone="updateQ" data-id="'
													+ student.tId
													+ '" >'
													+ '<i style="color:rgba(189, 189, 41, 0.94);"class="fa fa-pencil-square-o"></i></a>'
													+ '<a class='
													+ "alert"
													+ ' href="#'

													+ student.tId
													+ '" actionToBeDone="delete" data-id="'
													+ student.tId
													+ '" >'
													+ '<i class="fa fa-trash-o" style="color:red"></i>'
													+ '</a>'
													+ '</td></tr>')
						})

	}


