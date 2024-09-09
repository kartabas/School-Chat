const editProfileAPI = 'http://localhost:8080/profile/updateprofile';
const getProfileData = 'http://localhost:8080/profile/';



let updateProfileArray = [];





function editProfile(updateProfileArray, editProfileAPI, updateDatasend) {





	fetch(editProfileAPI, {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(updateDatasend),
	})
		.then(response => response.json())
		.then(data => {
			console.log('Success:', data);
		})
		.catch(error => {
			console.error('Error:', error);
		});
}


$(document).ready(function () {

	let myUpdateBackroundImg;
	let myUpdateAvatarImg;


	//----------------------Background Image----------------------
	$('.update__profile__background_btn').on('click', function () {
		$('#update__profile__background_img_fileInput').click();
	});

	$('#update__profile__background_img_fileInput').on("change", function (event) {

		myUpdateBackroundImg = event.target.files[0];


		console.log("Update Profile Background:" + myUpdateBackroundImg);

		if (myUpdateBackroundImg) {
			const reader = new FileReader();

			reader.onload = function (e) {
				imgUrl = e.target.result

				updateProfileArray[0] = (imgUrl);



			}

			reader.readAsDataURL(myUpdateBackroundImg);


			const img = document.createElement('img');
			img.src = URL.createObjectURL(myUpdateBackroundImg);
			$(img).appendTo(".show_update__profile__background");


		}


	});
	//---------------------------------------------------------------------



	//-------------------------------------------Avatar Image---------------------------
	$('.update__profile__avatar_btn').on('click', function () {
		$('#update__profile__avatar_img_fileInput').click();
	});

	$('#update__profile__avatar_img_fileInput').on("change", function (event) {

		myUpdateAvatarImg = event.target.files[0];

		console.log("Update Profile Background:" + myUpdateAvatarImg);

		if (myUpdateAvatarImg) {
			const reader = new FileReader();

			reader.onload = function (e) {
				const imgUrl = e.target.result;

				// Create an image element to draw on the canvas
				const img = new Image();
				img.src = imgUrl;

				img.onload = function () {
					// Create a canvas element
					const canvas = document.createElement('canvas');
					const ctx = canvas.getContext('2d');

					// Set canvas size to 100x100
					canvas.width = 300;
					canvas.height = 300;

					// Draw the image on the canvas with the new size
					ctx.drawImage(img, 0, 0, 300, 300);

					// Convert the resized image to a data URL
					const resizedImgUrl = canvas.toDataURL('image/png');

					// Save the resized image URL
					updateProfileArray[1] = resizedImgUrl;

					// Create an image element to display the resized image
					const resizedImg = document.createElement('img');
					resizedImg.src = resizedImgUrl;
					$(resizedImg).appendTo(".show_update__profile__avatar");

					console.log("Resized Image URL saved to updateProfileArray[1]:", updateProfileArray[1]);
				};
			};

			reader.readAsDataURL(myUpdateAvatarImg);
		}
	});



	//---------------------------------------------------------------------

	$("#editProfile").click(function () {
		$(".update__profile__panel").css("display", "block");

	});


	$(".update__profile__close").click(function () {
		$(".update__profile__panel").css("display", "none");

	});


	$(".update__profile__send_btn").click(function () {
		let myUpdateBio = $("#update__profile__bio_img_fileInput").val();

		updateProfileArray[2] = (myUpdateBio);
		console.log("Send new data:" + updateProfileArray[2]);


		fetch(getProfileData + usersModel.id)
			.then(response => response.json())
			.then(data => {

				let profileDatatArray = [data.profileBackground, data.profileImage, data.profileBiography];



				if (updateProfileArray[0] == null) {
					updateProfileArray[0] = profileDatatArray[0];

				}


				if (updateProfileArray[1] == null) {
					updateProfileArray[1] = profileDatatArray[1];

				}

				if (updateProfileArray[2].length == 0) {
					updateProfileArray[2] = profileDatatArray[2];
				}


				let updateDatasend = {
					profileBackground: updateProfileArray[0],
					profileImage: updateProfileArray[1],
					profileBiography: updateProfileArray[2],
					usersModel: usersModel,

				}
				console.log(updateProfileArray);
				editProfile(updateProfileArray, editProfileAPI, updateDatasend);
				$(".alert").alert().css("display", "block");
				setTimeout(function () {
					$(".alert").fadeOut(500);
				}, 1500);
			})
			.catch(error => console.error('Error fetching data:', error));


	});


});
