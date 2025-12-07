const getProfileDataAvatar = 'https://www.studentchat.me/profile/';




$(document).ready(function () {

	fetch(getProfileDataAvatar + usersModel.id)
		.then(response => response.json())
		.then(dataProfileImg => {
			getProfileImg(dataProfileImg);
			
		})
		.catch(error => console.error('Error fetching data:', error));



});


function getProfileImg(dataProfileImg) {
	
	$(".avatar").attr("src", dataProfileImg.profileImage);

}




