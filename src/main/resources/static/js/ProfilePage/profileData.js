const getProfileUserData = 'https://www.studentchat.me/profile/' ;


fetch(getProfileUserData+usersModel.id)
	.then(response => response.json())
	.then(data => {
		console.log(data);
		getProfileDataFunc(data);

	})
	.catch(error => console.error('Error fetching data:', error));



function getProfileDataFunc(profileData) {
	let profileBackImg = profileData.profileBackground;
	let profileAvatarImg = profileData.profileImage;
	let profileBio = profileData.profileBiography;

	if(profileBackImg != ' ' ){
		
		$(".main__background_container img").attr("src",profileBackImg);
	}
	if(profileAvatarImg != ' '){
		$(".main_profile_avatar img").attr("src", profileAvatarImg);
	}


	$(".profile__content__bio").text(profileBio);


}

