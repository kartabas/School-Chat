const updatePost = 'https://www.studentchat.me/profile/updatepost/';

$(document).ready(function () {
	let isClicked = false;




	$(document).on('click', '.editPost', function (e) {
		e.preventDefault();
		let isClicked = false;
		let oldTextPost = $(this).closest('.post__box').find('.post__text').text();
		let currentPostImg = $(this).closest('.post__box').find('.post__fotos__img').attr("src");

		let postUpdateInputImg;
		let postUpdateData = [];

		let currentPost = $(this).closest('.post__box');
		let postId = currentPost.find('.postId').val();


		let uploadImgFront = `
		<div class='post__fotosUploadImg'> 
			<input type='file' accept='image/png, image/jpeg' id='postImageUpdate' hidden> 
			<h2 class='postTittleUpdate' >Upload new Image but only one time </h2> 
			<img class='uploadIcon' src='../../../fotos/profile/upload.png'  alt=''> 
		
		</div>  `;

		currentPost.find(".post__fotos").append(uploadImgFront);




		currentPost.find(".post__fotos").hover(function () {

			if (!isClicked) {
				currentPost.find(".post__fotosUploadImg").css("opacity", "1");
				currentPost.find(".post__fotos__img").css("opacity", "0.3");
			}
		}, function () {

			if (!isClicked) {
				currentPost.find(".post__fotosUploadImg").css("opacity", "0");
				currentPost.find(".post__fotos__img").css("opacity", "1");
			}
		})




		console.log(postId);
		currentPost.find(".post__text").html("");
		currentPost.find(".post__text").append('<textarea class="form-control" id="postText" cols="auto" rows="auto" placeholder="Change your post text...." maxlength="1000" style="color: rgba(var(--bs-tertiary-bg-rgb), var(--bs-bg-opacity)) !important;" autofocus ></textarea>');
		currentPost.find(".under__posts__elements").css("display", "none");
		currentPost.append('<button class="btn btn-primary" id="savePost">Save</button>');
		currentPost.append('<button class="btn btn-secondary" id="cancelUpdatePost">Cancel update post</button>');






		currentPost.find(".post__fotos img").on("click", function (e) {
			e.preventDefault();

			$("#postImageUpdate").click();

		});



		currentPost.on("change", "#postImageUpdate", function (event) {

			postUpdateInputImg = event.target.files[0];
			currentPost.find(".post__fotos ").empty();

			if (postUpdateInputImg) {
				const reader = new FileReader();

				reader.onload = function (e) {


					//new post Image
					imgUrl = e.target.result
					postUpdateData.push(imgUrl);

					currentPost.find(".post__fotos").html(`
						<img class="post__fotos__img" src="${imgUrl}" alt="Post Image">
					`).append(uploadImgFront);
				};

				reader.readAsDataURL(postUpdateInputImg);



			}
		});




		$("#postText").each(function () {
			this.style.height = this.scrollHeight + "px";
			this.style.overflowY = "hidden";
		}).on("input", function () {
			this.style.height = "auto";
			this.style.height = this.scrollHeight + "px";
		});

		$("#cancelUpdatePost").click(function () {
			$(".post__text").html("<h5>" + oldTextPost + "</h5>");


			$("#postText").remove();
			$(".post__fotosUploadImg").remove();
			currentPost.find(".post__fotos").hover(function () {
				currentPost.find(".post__fotosUploadImg").css("opacity", "unset");
				currentPost.find(".post__fotos__img").css("opacity", "unset");
			})
			currentPost.find(".under__posts__elements").css("display", "block");
			$("#savePost").remove();
			$("#cancelUpdatePost").remove();
		});



		$("#savePost").click(function () {
			if (postUpdateData[0] == " " || postUpdateData[0] == null) {
				postUpdateData[0] = currentPostImg;

			}

			// alert("postUpdateData[0]: " + postUpdateData[0]);


			//Post Text update
			let postText = currentPost.find('#postText').val();
			currentPost.find(".post__text").append("<h5>" + postText + "</h5>");


			if (postText == "") {
				postUpdateData[1] = oldTextPost;
				currentPost.find(".post__text").append("<h5>" + oldTextPost + "</h5>");
			} else {
				postUpdateData.push(postText);
			}
			// alert("postUpdateData[1]: " + postUpdateData[1]);

			let updatePostTime = new Date().toLocaleTimeString() + " " + new Date().toLocaleDateString();
			postUpdateData.push(updatePostTime);

			console.log(postUpdateData);


			$("#postText").remove();
			$(".post__fotosUploadImg").remove();
			currentPost.find(".post__fotos").hover(function () {
				currentPost.find(".post__fotosUploadImg").css("opacity", "unset");
				currentPost.find(".post__fotos__img").css("opacity", "unset");
				// currentPost.find(".post__fotosUploadImg").addClass("no-hover");
				// currentPost.find(".post__fotos__img").addClass("no-hover");
			})



			$(".post__time__text").html(updatePostTime);
			$("#savePost").remove();
			$("#cancelUpdatePost").remove();
			currentPost.find(".under__posts__elements").css("display", "block");



			$(".alert__update__post").fadeIn(500);
			setTimeout(function () {
				$(".alert__update__post").fadeOut(500);
			}, 1000);

		});



		$("#savePost").click(function () {
			const dataUpdatePost = {
				meassage: postUpdateData[1],
				sendTime: postUpdateData[2],
				postImage: postUpdateData[0],
			};

			fetch(updatePost + postId, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify(dataUpdatePost),
			})
				.then(response => response.json())
				.then(data => {
					console.log('Success:', data);
				})
				.catch(error => {
					console.error('Error:', error);
				});
		});


	});


});