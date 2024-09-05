
console.log("userNickname2: " + userNickname);
$(document).ready(function () {


	function createPostBlock(post,userNickname) {
		let meassage = post.meassage;
		let postImage = post.postImage;
		let sendTime = post.sendTime;
		

		let newPostBlock = `
		<div class="post__box">
			  <div class="post__header__container">
					 <div class="post__avatar">
							 <img src="../../fotos/profile/userIcon.png" style="width: 49px; height: 49px; border-radius: 50%;" alt="">
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
									<a class="dropdown-item" href="#">Delete</a>
									<a class="dropdown-item" href="#">Edit</a>
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
									<button class="nav-link active" href="#"><img src="../../fotos/profile/Comment.png" alt=""> 0</button>
							 </li>
							 <li class="nav-item me-4">
									<button class="defaultLike nav-link" href="#"><img src="../../fotos/profile/DefaultLike.png" alt="" class="defaultLikeImg"> 0</button>
							 </li>
							 <li class="nav-item me-6">
									<button class="nav-link" href="#"><img src="../../fotos/profile/Share.png" alt=""></button>
							 </li>
					 </ul>
			  </div><!-- under__posts__elements -->
		</div><!-- post__box -->
		`;
		$(".posts__container").prepend(newPostBlock);

	}



	fetch('http://localhost:8080/profile/usersposts')
		.then(response => response.json())
		.then(data => {
			console.log(data);

			
			
			data.forEach(post => {
				createPostBlock(post,userNickname)
				console.log(post);
			});
		})
		.catch(error => console.error('Error fetching data:', error));





});


