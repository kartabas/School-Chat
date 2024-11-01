const postSendPost = 'http://localhost:8080/home/savepost';


$(document).ready(function () {
	let mysendImages = [];


	async function createPostBlock(meassageArray) {
		$(".send_post_meassage textarea").css("border", "2px solid #ddd");

		let newPostBlock = `
		<div class="post__box">
			  <div class="post__header__container">
					 <div class="post__avatar">
							 <img src="../../fotos/profile/userIcon.png" style="width: 49px; height: 49px; border-radius: 50%;" alt="">
					 </div>
					 <div class="post__nickname">
							 <h6 >${meassageArray[1]}</h6>
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
					 <h5 th:field="*{meassage}">${meassageArray[0]}</h5>
			  </div>
			  
			  <div class="post__fotos">
					<img src="${meassageArray[4]}" alt="Image"  th:field="*{postImage}">
			  </div>
			  
			  <div class="post__time">
					 <p class="post__time__text" th:field="*{sendTime}">${meassageArray[3]}</p>
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

		$(".main__posts__container").prepend(newPostBlock);

		// JavaScript code to send data to the Java backend
		const dataToSend = {
			meassage: meassageArray[0],
			sendTime: meassageArray[3],
			postImage: meassageArray[4],
		};

		fetch(postSendPost, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(dataToSend),
		})
			.then(response => response.json())
			.then(data => {
				console.log('Success:', data);
			})
			.catch(error => {
				console.error('Error:', error);
			});

	}






	$("#send_post_elements_block_button").click(function () {
		let meassageArray = [];


		let meassage = $("#send_post_meassageInput").val();
		let nickname = $(".sidebar__text__nickname ").text();



		let imgUrl = "";
		if (meassage.length != 0 && meassage != " ") {
			let sendTime = new Date().toLocaleTimeString() + " " + new Date().toLocaleDateString();
			meassageArray.push(meassage);
			meassageArray.push(nickname);
			meassageArray.push(mysendImages);
			meassageArray.push(sendTime);

			const file = meassageArray[2][0];

			if (file) {
				const reader = new FileReader();

				reader.onload = function (e) {
					imgUrl = e.target.result
					meassageArray.push(imgUrl);
					createPostBlock(meassageArray);

					// console.log("Meassage:" + meassageArray[4]);
				}

				reader.readAsDataURL(file)

			}



			$(".send_post_meassage textarea").css("border", "2px solid #ddd");


			$("#send_post_meassageInput").val('');
			$(".send_post_elements_images").empty();
			mysendImages = [];

		} else {

			$(".send_post_meassage textarea").css("border", "2px solid red");
			// alert("Meassage is empty!!!");

		}


	});













	$('.send_post_elements_img').on('click', function () {
		$('#send_post_elements_img_fileInput').click();

	});



	$('#send_post_elements_img_fileInput').on("change", function (event) {

		// $(".send_post_elements_images").empty();


		let file = event.target.files[0];
		// console.log(file);

		if (file) {
			mysendImages.push(file);

			mysendImages.forEach((i) => {
				const img = document.createElement('img');
				img.src = URL.createObjectURL(i);
				$(img).appendTo(".send_post_elements_images");
			});
		}
	});



	// console.log(mysendImages);



});