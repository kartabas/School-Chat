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



	$(document).on('click', '#defaultLike', function () {
		if ($(this).data('clickCountLike') === undefined) {
			$(this).data('clickCountLike', 0);
		}

		let clickCountLike = $(this).data('clickCountLike');

		
		if (clickCountLike < 1) {

			$(".defaultLikeImg", this).attr("src", "../../../fotos/profile/SelectedLike.png");
			clickCountLike = 1;
			$("#defaultLike span",this).html($("#defaultLike span",this).val() + clickCountLike);
		} else {

			$(".defaultLikeImg", this).attr("src", "../../../fotos/profile/defaultLike.png");
			clickCountLike = 0;
			$("#defaultLike span",this).html($("#defaultLike span",this).val() - clickCountLike);
		}


		$(this).data('clickCountLike', clickCountLike);
		console.log("Click count after: " + clickCountLike);
	});





});