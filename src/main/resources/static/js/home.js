$(document).ready(function () {
	$(".nav-link").hover(
		function () {
			$(this).addClass("active");
		},
		function () {
			$(this).removeClass("active");
		}
	);




	$(".triggerIdButton").hover(
		function () {
			$(this).addClass("btn-primary");
		},
		function () {
			$(this).removeClass("btn-primary");
		}
	);




	let clickCountLike = 0;

	$(".defaultLike",this).click(function () {
		
		if (clickCountLike < 1) {
			$(".defaultLikeImg", this).attr("src", "../static/fotos/profile/SelectedLike.png");
			console.log("Click",clickCountLike);

			clickCountLike++;
		} else {
			$(".defaultLikeImg", this).attr("src", "../static/fotos/profile/defaultLike.png");
			console.log("Click ",clickCountLike);

			clickCountLike = 0;
		}
	});

	$(".defaultLike").each(function () {
		
		$(this).data("clickCountLike", 0);
  });

  $(".defaultLike").click(function () {
		
		let clickCountLike = $(this).data("clickCountLike");
		console.log($(this).data("clickCountLike"));
		
		if (clickCountLike < 1) {
			$(".defaultLikeImg", this).attr("src", "../static/fotos/profile/SelectedLike.png");
			 clickCountLike = 1;
		} else {
			$(".defaultLikeImg", this).attr("src", "../static/fotos/profile/defaultLike.png");
			 clickCountLike = 0;
		}

		$(this).data("clickCountLike", clickCountLike);
  });

});