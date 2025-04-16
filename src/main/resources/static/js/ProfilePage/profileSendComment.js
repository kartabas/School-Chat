const allCommentsUnderPost = "http://localhost:8080/profile/comment/postcomments/"; // Must post ID be added to the end of the URL

const saveCommentUnderPost = "http://localhost:8080/profile/comment/savecomment";
const profileAccountInfo = "http://localhost:8080/profile/"; // Must user ID be added to the end of the URL

const deleteCemmentUnderPost = "http://localhost:8080/profile/comment/delete/"; // Must comment ID be added to the end of the URL

let clickCommentPanelCount = 0;
let commentCount;



$(document).ready(function () {

	$(document).on("click", ".showCommentsBtn", function () {
		let postId = $(this).closest(".post__box").find(".postId").val();
		let postBox = $(this).closest(".post__box");

		//commentCount = $(this).closest(".post__box").find(".comment__box .container .comment-section .comments-list ").find(".comment-box").length;
		//$(this).closest(".post__box").find(".commentCount").text(commentCount);


		let clickCount = postBox.data("clickCommentPanelCount") || 0; // Initialize clickCount if not already set
		clickCount++;

		if (clickCount > 1) {
			postBox.find(".comment__box").css("display", "none");
			postBox.find(".comments-list").empty();
			clickCount = 0;
		} else {

			fetch(allCommentsUnderPost + postId)
				.then(response => response.json())
				.then(data => {
					console.log(data);
					if (data.length == 0) {
						postBox.find(".comments-list").append('<h1 class="text-center" style=" color: white;">No comments yet</h1>');
					}

					$(data).each(comment => {
						//console.log(data[comment].profileModel.profileImage);
						postBox.find(".comments-list").append(`
							<div class="comment-box" id="${data[comment].commentId}">
								<div class="d-flex gap-3">
									<img src="${data[comment].profileModel.profileImage}" alt="User Avatar" class="user-avatar">
									<div class="flex-grow-1">
											<div class="d-flex justify-content-between align-items-center mb-2">
												<h6 class="mb-0">${data[comment].profileModel.usersModel.login}</h6>
												<span class="comment-time">${data[comment].commentTime}</span>
											</div>
											<p class="mb-2 comment__message" >${data[comment].commentMessage}</p>
									</div>
								</div>
							</div>`);
					})
				})
				.catch(error => console.error('Error fetching data:', error));


			postBox.find(".comment__box").css("display", "block");


		}


		postBox.data("clickCommentPanelCount", clickCount);

		console.log("Post ID:", postBox.find(".postId").val(), "Click Count:", clickCount);
	});






	$(document).on("click", ".comment__box__button", function () {
		$(this).closest(".comment__box").find(".comments-list").removeClass(".text-center");
		let commentArray = [];
		let postBox = $(this).closest(".post__box");
		let postId = postBox.find(".postId").val();

		let commentText = $(this).closest(".comment__box").find(".comment__box__input__text").val();
		console.log("commentText: " + commentText);

		commentArray.push(postId);
		
		fetch(profileAccountInfo + usersModel.id)
			.then(response => response.json())
			.then(async data => {

				commentArray.push(data);


				commentArray.push(commentText);
				let today = new Date();
				let formattedDate = `${today.getDate().toString().padStart(2, '0')}.${(today.getMonth() + 1).toString().padStart(2, '0')}.${today.getFullYear()}`;
				commentArray.push(formattedDate);

				if (commentText == " " || commentText.length == 0 || commentText == null) {

					$(this).closest(".comment__box").find(".comment__box__input__text").attr("placeholder", "Please enter a comment...");


				} else {
					const dataToSaveComment = {
						postId: commentArray[0],
						profileModel: commentArray[1],
						commentMessage: commentArray[2],
						commentTime: commentArray[3],
						userId: commentArray[1].usersModel.id
					};

					fetch(saveCommentUnderPost, {
						method: 'POST',
						headers: {
							'Content-Type': 'application/json',
						},
						body: JSON.stringify(dataToSaveComment),
					})
						.then(response => response.text()) // Read response as text first
						.then(data => {
							console.log('Success:', data);
						})
						.catch((error) => {
							console.error('Error:', error);
						});



					postId = $(this).closest(".post__box").find(".postId").val();


					$(this).closest(".post__box").find(".comments-list").append(`
						<div class="comment-box">
							<div class="d-flex gap-3">
								<img src="${commentArray[1].profileImage}" alt="User Avatar" class="user-avatar">
								<div class="flex-grow-1">
										<div class="d-flex justify-content-between align-items-center mb-2">
											<h6 class="mb-0">${commentArray[1].usersModel.login}</h6>
											<span class="comment-time">${commentArray[3]}</span>
										</div>
										<p class="mb-2 comment__message" >${commentArray[2]}</p>
								</div>
							</div>
						</div>
						
					  `);
					
					$(this).closest(".post__box").find("#changeCommentCount").html($(this).closest(".comment__box").find(".container .comment-section .comments-list ").find(".comment-box").length);
					$(this).closest(".comment__box").find(".comment__box__input__text").val(" ");

				}

				console.log(commentArray);
			})
			.catch(error => console.error('Error fetching data:', error));


	});



});



