

$(document).ready(function () {



	$(document).on('click', '.editPost', function () {
		let postUpdateInputImg;
		let postUpdateImg;

		let currentPost = $(this).closest('.post__box');
		let postId = currentPost.find('.postId').val();


		let uploadImgFront = `
		<div class='post__fotosUploadImg'> 
			<input type='file' accept='image/png, image/jpeg' id='postImageUpdate' hidden> 
			<h2 class='postTittleUpdate' >Upload new Image</h2> 
			<img class='uploadIcon' src='../../../fotos/profile/upload.png'  alt=''> 
		
		</div>  `;

		currentPost.find(".post__fotos").append(uploadImgFront);

		currentPost.find(".post__fotos").hover(function () {

			currentPost.find(".post__fotosUploadImg").css("opacity", "1");
			currentPost.find(".post__fotos__img").css("opacity", "0.3");
		}, function () {

			currentPost.find(".post__fotosUploadImg").css("opacity", "0");
			currentPost.find(".post__fotos__img").css("opacity", "1");
		});




		console.log(postId);
		currentPost.find(".post__text").html("");
		currentPost.find(".post__text").append('<textarea class="form-control" id="postText"></textarea>');
		currentPost.find(".post__text").append('<button class="btn btn-primary" id="savePost">Save</button>');



		$("#savePost").click(function () {

			//Post Text update
			let postText = currentPost.find('#postText').val();
			currentPost.find(".post__text").append("<h5>" + postText + "</h5>");




			$("#postText").remove();
			$("#savePost").remove();
		});


		currentPost.find(".post__fotos .post__fotos__img").on("click", function () {
			postUpdateInputImg = null;
		
			$("#postImageUpdate").click();
			console.log("image was clicked");



		});


		currentPost.find("#postImageUpdate").on("change", function (event) {
			postUpdateInputImg = event.target.files[0];
			currentPost.find(".post__fotos ").empty();

			if (postUpdateInputImg) {
				const reader = new FileReader();

				reader.onload = function (e) {
					//TODO make this to end
					//new post Image
					imgUrl = e.target.result

					let img = document.createElement('img');
					$(img).addClass("post__fotos__img");
					img.src = imgUrl;
					$(img).appendTo(currentPost.find(".post__fotos"));
				};

				reader.readAsDataURL(postUpdateInputImg);



				currentPost.find(".post__fotos").append(uploadImgFront);
				// img.onload = function () {
				// 	URL.revokeObjectURL(img.src);
				// };
			}




		});

		

	});
});