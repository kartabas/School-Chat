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





//-------------------------------Like-----------------------------
//TODO: Likes and Dislikes are not working fix this
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
		// console.log("Click count after: " + clickCountLike);
	});

	//-------------------------------Like-----------------------------


	$('#sent_post_meassageInput').on('input', function () {

		$(this).css('height', 'auto');

		$(this).css('height', this.scrollHeight + 'px');
		//console.log("this.scrollHeight "+this.scrollHeight );
	});



	$('textarea').keyup(function () {

		var characterCount = $(this).val().length,
			current = $('#current'),
			maximum = $('#maximum'),
			theCount = $('#the-count');

		current.text(characterCount);



		if (characterCount > 70 && characterCount < 90) {
			current.css('color', 'green');
			current.css('font-weight', 'bold');
		}
		if (characterCount > 90 && characterCount < 100) {
			current.css('color', 'green');
			current.css('font-weight', 'bold');
		}
		if (characterCount > 100 && characterCount < 120) {
			current.css('color', 'green');
			current.css('font-weight', 'bold');
		}
		if (characterCount > 120 && characterCount < 139) {
			current.css('color', 'green');
			current.css('font-weight', 'bold');
		}

		if (characterCount >= 440) {
			maximum.css('color', '#8f0001');
			current.css('color', '#8f0001');
			theCount.css('font-weight', 'bold');
		} else {
			current.css('color', 'green');
			theCount.css('font-weight', 'bold');
		}


	});


});