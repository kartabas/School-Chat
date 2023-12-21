
let countClick = 0;
$(document).ready(function () {

	$("#searchButton").click(function () {

		let searchText = $("#searchSchool").val();

		countClick = countClick + 1;
		$(".search_school").css({
			display:"block"
		});

		if (countClick < 2) {


			if (searchText == "" ) {
				$(".search-wrapper").addClass("error");
				
				$(".noFound").append("<span>Your school not found...</span>");
				console.log("Your school not found...");
			}
            
		}else {
             $(".noFound span").remove();
				 $(".search-wrapper").removeClass("error");


				 $(".search_school").css({
					display:"none"
				});
		
            countClick = 0;
        }


	});



});







