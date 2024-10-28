const deletePost = 'http://localhost:8080/profile/deletepost/';

$(document).ready(function () {



	$(document).on('click', '.deletePost', function () {
		let postId = $(this).closest('.post__box').find('.postId').val();
		console.log(postId);


		$.ajax({
			url: deletePost + postId,
			type: 'DELETE',
			success: function (data) {
				console.log(data);
				window.location.reload();
			}
		});

		$('.post__box').remove();
		$(".posts__container").prepend('<h1 class="text-center">No posts yet</h1>');



	});



});