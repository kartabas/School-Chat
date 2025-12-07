const likeCountAPIonHomePage = "https://localhost:8080/home/like/";




$(document).ready(function () {
	$(".nav-link").hover(
		function () {
			$(this).addClass("active");
		},
		function () {
			$(this).removeClass("active");
		}
	);





	$("#send_post_elements_img_fileInput").on("change", function () {
		let file = this.files[0];
		if (file && file.type.startsWith("video/")) {

			// If the file is a video, show an alert and remove the image element
			alert("Error: Video files are not allowed. Please select an image.");
			//alert("Error: Video files are not allowed. Please select an image.");
			file = null; // Reset the file input
			$(this).val(""); // Clear the file input
		}

		if ($(".send_post_elements_images").find("img").length >= 1) {
			alert("You can only upload one image at a time.");
			// Reset the file input if an image already exists
			$(this).val("");
			return; // Exit the function if an image already exists


		}

	});




	$(".triggerIdButton").hover(
		function () {
			$(this).addClass("btn-primary");
		},
		function () {
			$(this).removeClass("btn-primary");
		}
	);





	//-------------------------------Like-----------------------------
	// $(".defaultLikeImg", this).attr("src", "../../../fotos/profile/SelectedLike.png");
	// $(".defaultLikeImg", this).attr("src", "../../../fotos/profile/defaultLike.png");

	$(document).on("click", "#defaultLike", function () {
		const $likeBtn = $(this);
		const $likeImg = $(".defaultLikeImg", this);
		const $likeCount = $(".likeCount", this);
		const postIdData = $likeBtn.closest(".post__box").find(".postId").val();
		let isLiked = $likeBtn.attr("data-liked") === "true";

		if (!isLiked) {
			$likeImg.attr("src", "../../../fotos/profile/SelectedLike.png");
			$likeCount.text(function (i, text) {
				return parseInt(text) + 1;
			});




			$.ajax({
				url: likeCountAPIonHomePage + postIdData,
				type: 'POST',
				data: {
					postId: postIdData
				},
				success: function (response) {
					console.log("Liked successfully:", response);
					$likeCount.text(response);
				},
				error: function (xhr, status, error) {
					console.error("Error liking post:", error);
				}
			});

			$likeBtn.attr("data-liked", "true");

		} else {
			$likeImg.attr("src", "../../../fotos/profile/defaultLike.png");
			$likeCount.text(function (i, text) {
				return parseInt(text) - 1;
			});

			$.ajax({
				url: likeCountAPIonHomePage + postIdData,
				type: 'DELETE',
				data: {
					postId: postIdData
				},
				success: function (response) {
					console.log("Unliked successfully:", response);
					$likeCount.text(response);
				},
				error: function (xhr, status, error) {
					console.error("Error unliking post:", error);
				}
			});

			$likeBtn.attr("data-liked", "false");
		}
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


	$("#send_post_meassageInput").each(function () {
		this.style.height = this.scrollHeight + "px";
		this.style.overflowY = "hidden";
	}).on("input", function () {
		this.style.height = "auto";
		this.style.height = this.scrollHeight + "px";
	});

});