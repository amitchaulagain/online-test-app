 
var aaGroup=[];
$(document).ready(	function() {
	findAllGroups();
	 
	 });
 
 function findAllGroups() {

		$.ajax({
			type : 'GET',
			url : "http://localhost:8085/annotationBased/admin/viewAllGroups",
			dataType : "json",

			success : renderGroup
		});

	}
 function renderGroup(groups) {

		$.each(groups, function(idx, group) {
			 
			aaGroup.push(group);

		})
		aaGroup.reverse();
		pagi()
		var show = $("#showGroupPerPage option:selected").text();
		var index = $('#groupPagination').pagination('getCurrentPage');

		showGroup(show, index, aaGroup)
	}
 function pagi() {
		var show = $("#showGroupPerPage option:selected").text();
		var totalData = aaGroup.length;
		$(function() {
			$('#groupPagination').pagination({
				items : totalData,
				itemsOnPage : show,
				cssStyle : 'light-theme'
			});
		});
	}
 function showGroup(show, index, groups) {
		var initial = 0;
		var finale = show;

		if (index > 0) {

			initial = (index - 1) * show;
			finale = index * show;
			var limitedGroup = groups.slice(initial, finale);

		}

		else {
			limitedGroup = groups.slice(initial, finale);
		}
		$('#showGroup tr td').remove();
		$.each(limitedGroup,function(idx, group) {
							$('#showGroup').append(
											'<tr><td>'
													+ group.id
													+'</td><td>'
													+ group.name
													+ '</td><td width="20px">'
													// +test.tType+'</td><td>'
													+ '<a class='
													+ "alert"
													+ ' href="#'
													+ group.id
													+ '" actionToBeDone="view" data-id="'
													+ group.id
													+ '" >'
													+ '<i class="fa fa-eye" style="color:#00D000"></i></a>'
													+ '<a class='
													+ "alert"
													+ ' href="#" actionToBeDone="updateQ" data-id="'
													+ group.id
													+ '" >'
													+ '<i style="color:rgba(189, 189, 41, 0.94);"class="fa fa-pencil-square-o"></i></a>'
													+ '<a class='
													+ "alert"
													+ ' href="#'

													+ group.id
													+ '" actionToBeDone="delete" data-id="'
													+ group.id
													+ '" >'
													+ '<i class="fa fa-trash-o" style="color:red"></i>'
													+ '</a>'
													+ '</td></tr>')
						})

	}


