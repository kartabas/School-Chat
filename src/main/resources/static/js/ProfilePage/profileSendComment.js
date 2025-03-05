
let postId;
let clickCommentPanelCount = 0;
let commentCount;



$(document).ready(function () {

	$(document).on("click", ".showCommentsBtn", function () {

		let postBox = $(this).closest(".post__box");
		commentCount = $(this).closest(".post__box").find(".comment__box .container .comment-section .comments-list ").find(".comment-box").length;
		$(this).closest(".post__box").find(".commentCount").text(commentCount);

		let clickCount = postBox.data("clickCommentPanelCount") || 0;
		clickCount++;

		if (clickCount > 1) {
			postBox.find(".comment__box").css("display", "none");
			clickCount = 0;
		} else {
			postBox.find(".comment__box").css("display", "block");


		}


		postBox.data("clickCommentPanelCount", clickCount);

		console.log("Post ID:", postBox.find(".postId").val(), "Click Count:", clickCount);
	});






	$(document).on("click", ".comment__box__button", function () {
		let commentText = $(this).closest(".comment__box").find(".comment__box__input__text").val();
		console.log("commentText: " + commentText);



		if (commentText == " " || commentText.length == 0 || commentText == null) {

			$(this).closest(".comment__box").find(".comment__box__input__text").attr("placeholder", "Please enter a comment");


		} else {

			$(this).closest(".post__box").find(".commentCount").text(++commentCount);

			let postId = $(this).closest(".post__box").find(".postId").val();
			console.log("postId: " + postId);
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
			$(this).closest(".comment__box").find(".comment__box__input__text").val(" ");
		}
	});



});



