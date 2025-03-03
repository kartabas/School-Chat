
let postId;
let commentText;



$(document).ready(function () {





	$(document).on("click", "#sendCommentBtn", function (e) {
		e.preventDefault();
		console.log("commentBtn clicked");
		postId = $(this).closest(".post__box").find(".postId").val();
		console.log("postId: " + postId);


		$(this).closest(".post__box").find(".comment__box").toggle();

		// $(".comment__box__input__btn").click(function (e) {
		// 	e.preventDefault();
		// 	console.log("commentBtn clicked");
		// 	postId = $(this).closest(".post__box").find(".postId").val();
		// 	console.log("postId: " + postId);
		// 	let commentText = $(this).closest(".comment__box").find(".comment__box__input__text").val();
		// 	console.log("commentText: " + commentText);


		// });

		let commentCount = $(this).closest(".post__box").find(".comment__box .container .comment-section .comments-list ").find(".comment-box").length;
		$(this).find(".commentCount").text(commentCount);

		$(".comment__box__button").click(function () {
			console.log("commentBtn clicked");
			
			commentText = $(this).closest(".comment__box").find(".comment__box__input__text").val();
			console.log("commentText: " + commentText);	


			$(".comments-list").append(`
					<div class="comment-box">
						<div class="d-flex gap-3">
							<img src="https://randomuser.me/api/portraits/men/9.jpg" alt="User Avatar" class="user-avatar">
							<div class="flex-grow-1">
									<div class="d-flex justify-content-between align-items-center mb-2">
										<h6 class="mb-0">Mike Johnson</h6>
										<span class="comment-time">3 hours ago</span>
									</div>
									<p class="mb-2 comment__meassage" >${commentText}</p>
							</div>
						</div>
					</div>

				`);

				//TODO BAG FIX коли виходиш с коментів і знову заходиш коментарі потім дублюється
				commentText = $(this).closest(".comment__box").find(".comment__box__input__text").val("");

		});

	});





});



