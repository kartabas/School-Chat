const postsApiUrl = 'http://localhost:8080/home/allpostsperschool';
const usersApiUrl = 'http://localhost:8080/home/alluserposts/';
const getProfileData = 'http://localhost:8080/home/profileinfo/';
const getCountCommentsHome = 'http://localhost:8080/home/comment/postcountcomments/';

async function getUserNickname(userId) {
	try {
		const response = await fetch(usersApiUrl + userId);
		const dataUserLogin = await response.text();
		// console.log(dataUserLogin);
		return dataUserLogin;
	} catch (error) {
		console.error('Error fetching data:', error);
		// Handle the error appropriately, maybe return a default value or throw an error
		return null;
	}
}

async function getProfileAvatar(userId) {

	try {
		const response = await fetch(getProfileData + userId);
		const dataProfileImg = await response.text();

		if (dataProfileImg === " " || dataProfileImg == "" || dataProfileImg == null) {
			// console.log("dataProfileImg: " + null);
			return null;

		} else {
			// console.log("dataProfileImg: " + dataProfileImg);
			return dataProfileImg;
		}


	} catch (error) {
		console.error('Error fetching data:', error);
		// Handle the error appropriately, maybe return a default value or throw an error
		return null;
	}

}




$(document).ready(function () {


	async function createPostBlock(post, dataProfileImg) {
		let meassage = post.meassage;
		let postImage = post.postImage;
		let sendTime = post.sendTime;
		let userNickname = await getUserNickname(post.usersModel);
		let userAvatar = await getProfileAvatar(usersModel.id);

		let newPostBlock = `
		<div class="post__box" >
			 <input type="hidden" value="${post.postId}" class="postId">
			  <div class="post__header__container">
					 <div class="post__avatar">
							 <img src="${dataProfileImg}" style="width: 49px; height: 49px; border-radius: 50%;" alt="">
					 </div>
					 <div class="post__nickname">
							 <h6  >${userNickname}</h6>
					 </div>
					 <div class="post__ellipses">
							 <div class="dropdown open">
									<button class="triggerIdButton btn btn-sm" type="button" id="triggerId" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="border: none;">
										  <img src="../../fotos/profile/ellipsis.png" alt="">
									</button>
									<div class="dropdown-menu" aria-labelledby="triggerId">
										  <a class="dropdown-item" href="#">Report</a>
										  <div class="dropdown-divider"></div>
									</div>
							 </div>
					 </div>
			  </div><!-- post__header__container -->
	
			  <div class="post__text">
					 <h5>${meassage}</h5>
			  </div>
			  
			  <div class="post__fotos">
					<img src="${postImage}">
			  </div>
			  
			  <div class="post__time">
					 <p class="post__time__text">${sendTime}</p>
			  </div>
			  
			  <div class="under__posts__elements">
					 <ul class="nav justify-content-let">
							 <li class="nav-item me-4">
									<button class="nav-link btn  showCommentsBtn"  ><img src="../../../fotos/profile/Comment.png" alt="icon"><span class="commentCount" id="changeCommentCount">${await fetch(getCountCommentsHome + post.postId)
									.then(response => response.json())
									.then(data => {
										//console.log(data);
										return data;
									})}</span></button>
							 </li>
							 <li class="nav-item me-4">
									<button class="nav-link" id="defaultLike"><img src="../../fotos/profile/DefaultLike.png" alt="icon" class="defaultLikeImg"><span>0</span></button>
							 </li>
							 <li class="nav-item me-6">
									<button class="nav-link" ><img src="../../fotos/profile/Share.png" alt="icon"></button>
							 </li>
					 </ul>
			  </div><!-- under__posts__elements -->
			  
			  	<div class="comment__box">

						<div class="container">
							<div class="comment-section">
									<!-- Comments List -->
									<div class="comments-list"></div>

										<!-- New Comment Form -->
										<div class="mb-4">
												<div class="d-flex gap-3">
													<img src="${userAvatar}" alt="User Avatar" class="user-avatar">
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
			  	</div>  <!-- comment__box -->
		</div><!-- post__box -->
		`;


		// $(".main__posts__container").prepend(newPostBlock);
		$(".main__posts__container").append(newPostBlock);


	}



	fetch(postsApiUrl)
		.then(response => {
			if (!response.ok) {
				throw new Error(`HTTP error! Status: ${response.status}`);
			}
			return response.json();
		})
		.then(data => {
			data.sort((a, b) => {
				let [timeA, dateA] = a.sendTime.split(" ");
				let [timeB, dateB] = b.sendTime.split(" ");

				dateA = dateA.split('.').reverse().join('-');
				dateB = dateB.split('.').reverse().join('-');

				let fullDateA = new Date(`${dateA}T${timeA}`);
				let fullDateB = new Date(`${dateB}T${timeB}`);

				return fullDateA - fullDateB;
			});

			console.log(data);
			// data.forEach(post => {
			// 	console.log(post.sendTime);
			// });


			data.forEach(post => {
				let dataProfileImg = getProfileAvatar(post.usersModel);
				let defaultLinkAvatar = '../../fotos/profile/userIcon.png';
				dataProfileImg.then(dataProfileImg => {

					if (dataProfileImg == null || dataProfileImg == " " || dataProfileImg == "") {

						createPostBlock(post, defaultLinkAvatar);

					} else {
						createPostBlock(post, dataProfileImg);
					}

				});
				// createPostBlock(post, defaultLinkAvatar);
			});
		})
		.catch(error => console.error('Error fetching data:', error));





});


