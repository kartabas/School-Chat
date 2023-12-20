

$(document).ready(function () {
	let countClick = 0;
	$("#searchButton").click(function () {

		let searchText = $("#searchSchool").val();

		countClick = countClick + 1;
		if (countClick < 2) {
			$("#searchResult").css({
				display: "block"
			});

			if (searchText == "" | searchText == null) {
				$("#searchResult").append("<div class='error'>Your school not found...</div>");
			}
		} else {
			$(".error").remove();
			countClick = 0;
		}



	});



});







