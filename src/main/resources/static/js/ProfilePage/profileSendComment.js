
let postId;


$(document).ready(function () {





	$(document).on("click", "#sendCommentBtn", function (e) {
		e.preventDefault();
		console.log("commentBtn clicked");
		postId = $(this).closest(".post__box").find(".postId").val();
		console.log("postId: " + postId);


		$(this).closest(".post__box").find(".comment__box").toggle();

		$(".comment__box__input__btn").click(function (e) {
			e.preventDefault();
			console.log("commentBtn clicked");
			postId = $(this).closest(".post__box").find(".postId").val();
			console.log("postId: " + postId);
			let commentText = $(this).closest(".comment__box").find(".comment__box__input__text").val();
			console.log("commentText: " + commentText);



		});

	});


});



