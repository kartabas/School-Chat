
// console.log("userNickname2: " + userNickname);
const postsApiUrl = 'https://localhost:8080/profile/usersposts';
const usersApiUrl = 'https://localhost:8080/profile/userinfo/';
const getCountComments = 'https://localhost:8080/profile/comment/postcountcomments/';
const getLikeCountPerPosts = 'https://localhost:8080/profile/like/get/';
const isLikedPostAPI = "https://localhost:8080/profile/like/liked";


async function getUserNickname(userId) {
	try {
		const response = await fetch(usersApiUrl + userId);
		const dataUserLogin = await response.text();
		console.log(dataUserLogin);
		return dataUserLogin;
	} catch (error) {
		console.error('Error fetching data:', error);
		// Handle the error appropriately, maybe return a default value or throw an error
		return null;
	}
}

function getLikedPost(postId, userId, imgElement) {
	$.ajax({
		url: isLikedPostAPI,
		type: 'GET',
		data: {
			postId: postId,
			userId: userId
		},
		success: function (data) {
			console.log(postId + " → returned image URL:", data);
			if (data == "../../../fotos/profile/SelectedLike.png") {
				$(imgElement).closest("#defaultLike").attr("data-liked", "true");
			} else {
				$(imgElement).closest("#defaultLike").attr("data-liked", "false");
			}



			$(imgElement).attr("src", data);
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.error('Error fetching like icon:', textStatus, errorThrown);
		}
	});
}



$(document).ready(function () {


	async function createPostBlock(post, dataProfileImg) {
		let meassage = post.meassage;
		let postImage = post.postImage;
		let sendTime = post.sendTime;
		let userNickname = await getUserNickname(post.usersModel);
		let commentCount = post.commentCount;

		let newPostBlock = `
		<div class="post__box">
			  <input type="hidden" value="${post.postId}" class="postId">
			  <div class="post__header__container">
					 <div class="post__avatar">
							 <img class="avatarImg" src="${dataProfileImg.profileImage}" style="width: 49px; height: 49px; border-radius: 50%;" alt="">
					 </div>
					 <div class="post__nickname">
							 <h6  >${userNickname}</h6>
					 </div>
					 	<div class="post__ellipses  ">

							<div class="dropdown open">
								<button class="triggerIdButton btn btn-sm  " type="button" id="triggerId"
									data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
									style="border: none;  ">
									<img src="../../../fotos/profile/ellipsis.png" alt="">
								</button>
								<div class="dropdown-menu" aria-labelledby="triggerId">
									<span class="deletePost dropdown-item">Delete</span>
									<span class="editPost dropdown-item" >Edit</span>
									<div class="dropdown-divider"></div>
								</div>
							</div>
						</div>
			  </div><!-- post__header__container -->
	
			  <div class="post__text">
					 <h5>${meassage}</h5>
			  </div>
			  
			  <div class="post__fotos">
					<img class="post__fotos__img" src="${postImage}">
			  </div>
			  
			  <div class="post__time">
					 <p class="post__time__text">${sendTime}</p>
			  </div>
			  
			  <div class="under__posts__elements">
					 <ul class="nav justify-content-let">
							 <li class="nav-item me-4">
									<button class="nav-link btn  showCommentsBtn"  ><img src="../../../fotos/profile/Comment.png" alt="icon"><span class="commentCount" id="changeCommentCount">${await fetch(getCountComments + post.postId)
				.then(response => response.json())
				.then(data => {
					//console.log(data);
					return data;
				})}</span></button>
										</li>
							 <li class="nav-item me-4">
									<button class="nav-link" id="defaultLike"><img   alt="icon" class="defaultLikeImg" data-postid="${post.postId}" data-userid="${post.usersModel}"><span class="likeCount">${await fetch(getLikeCountPerPosts + post.postId)
				.then(response => response.json())
				.then(data => {
					//console.log(data);
					return data;
				})}</span></button>
									
							 </li>
							 <li class="nav-item me-6">
									<button class="nav-link" ><img src="../../../fotos/profile/Share.png" alt="icon"></button>
							 </li>
					 </ul>
			  </div><!-- under__posts__elements -->
			  	<div class="comment__box">

						<div class="container">
							<div class="comment-section">


									<!-- Comments List -->
									<div class="comments-list">

												</div>

												<!-- New Comment Form -->
												<div class="mb-4">
														<div class="d-flex gap-3">
															<img src="${dataProfileImg.profileImage}" alt="User Avatar" class="user-avatar">
															<div class="flex-grow-1">
																<textarea class="form-control comment-input comment__box__input__text" rows="1" placeholder="Write a comment..."></textarea>
																<div class="mt-3 text-end">
																		<button class="btn btn-comment comment__box__button text-white">Post Comment</button>
																</div>
															</div>
														</div>
												</div>
												
									</div>
							</div>
						</div> 

		</div><!-- post__box -->
		`;
		$(".posts__container").prepend(newPostBlock);
		const lastInsertedImg = $(".posts__container .defaultLikeImg").first();
		const postId = lastInsertedImg.data('postid');
		const userId = lastInsertedImg.data('userid');
		getLikedPost(postId, userId, lastInsertedImg);

	}



	fetch(postsApiUrl)
		.then(response => response.json())
		.then(data => {
			console.log(data);
			// console.log(data.length);

			if (data.length == 0) {

				$(".posts__container").prepend('<h1 class="text-center">No posts yet</h1>');
			}

			data.forEach(post => {
				fetch(getProfileDataAvatar + post.usersModel)
					.then(response => response.json())
					.then(dataProfileImg => {
						createPostBlock(post, dataProfileImg)
						console.log(post);

					})
					.catch(error => console.error('Error fetching data:', error));
			});




		})
		.catch(error => console.error('Error fetching data:', error));







});


