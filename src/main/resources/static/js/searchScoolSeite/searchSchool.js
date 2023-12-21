
let countClick = 0;
$(document).ready(function () {

	$("#searchButton").click(function () {

		let searchText = $("#searchSchool").val();

		countClick = countClick + 1;
//		$("#searchResult").css({
//        	display: "block"
//        });

		if (countClick < 2) {


			if (searchText == "" ) {

				$(".error").append("<span>Your school not found...</span>");
			}
            console.log("Your school not found...");
		}else {
             $(".error").remove();

            countClick = 0;
        }


	});



});







