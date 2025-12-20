const likeCountAPI = "/profile/like/";



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
				url: likeCountAPI + postIdData,
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
			$likeImg.attr("src", "../../../fotos/profile/DefaultLike.png");
			$likeCount.text(function (i, text) {
				return parseInt(text) - 1;
			});

			$.ajax({
				url: likeCountAPI + postIdData,
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









});