$(document).ready(function () {
	$(".nav-link").hover(
		function () {
			$(this).addClass("active");
		},
		function () {
			$(this).removeClass("active");
		}
	);



	$("#profile_pic").change(function () {
		var file = this.files[0];
		var url = URL.createObjectURL(file);

		alert(url);
		$(".main__background_container").css("background", "url(" + url + ") no-repeat");

	});


});