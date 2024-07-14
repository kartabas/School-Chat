$(document).ready(function () {
	$(".nav-link").hover(
		function () {
			$(this).addClass("active");
		},
		function () {
			$(this).removeClass("active");
		}
	);


});