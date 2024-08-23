$(document).ready(function () {
	$("#sent_post_elements_block_button").click(function () {
		let meassageArray = [];


		let meassage = $("#sent_post_meassageInput").val();
		let nickname = $("#username_sidebar").val();

		meassageArray.push(meassage);
		meassageArray.push(nickname);
		meassageArray.push(mySentImages);
		console.log(meassageArray);
	});


	let mySentImages = [];




	$('.sent_post_elements_img').on('click', function () {
		$('#sent_post_elements_img_fileInput').click();

	});



	$('#sent_post_elements_img_fileInput').on("change", function (event) {

		$(".sent_post_elements_images").empty();


		let file = event.target.files[0];
		console.log(file);

		if (file) {
			mySentImages.push(file);

			mySentImages.forEach((i) => {
				const img = document.createElement('img');
				img.src = URL.createObjectURL(i);
				$(img).appendTo(".sent_post_elements_images");
			});
		}
	});


	console.log(mySentImages);



});