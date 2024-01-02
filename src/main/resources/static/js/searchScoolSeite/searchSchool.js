//output objekts all school id
//console.log(arrayID);

let countClick = 0;
$(document).ready(function () {


	$("#searchButton").click(function (event) {

		let searchText = $("#searchSchool").val();

		countClick = countClick + 1;
		$(".search_school").css({
			display: "block"
		});


		if (countClick < 2) {
			if ( (searchText == "") || (arrayID.length == 0) ) {

				event.preventDefault();
				$(".json__object").remove();
				$(".search-wrapper").addClass("error"); 
				$(".noFound").html("<span>Your school not found...</span>");
				console.log("Your school not found...");
                console.log("countClick: "+countClick)

			}
		} else {

			$(".noFound span").remove();
			$(".search-wrapper").removeClass("error");
			countClick = 0;
		}



	});



});





