const getHomeData = '/home/profileinfo/';




$(document).ready(function () {

	fetch(getHomeData + usersModel.id)
		.then(response => response.text())
		.then(dataHomeImg => {


			getHomeImg(dataHomeImg);



		})
		.catch(error => console.error('Error fetching data:', error));



});


function getHomeImg(dataHomeImg) {
	if (dataHomeImg === " " || dataHomeImg == "" || dataHomeImg == null) {
		dataHomeImg = "../../fotos/profile/userIcon.png"
		$(".avatar").attr("src", dataHomeImg);
	} else {
		$(".avatar").attr("src", dataHomeImg);
	}


}