const deletePost = '/profile/deletepost/';

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

		$(this).closest('.post__box').remove();



		if ($(".posts__container").find(".post__box").length == 0) {
			$(".posts__container").prepend('<h1 class="text-center">No posts yet</h1>');
		}


	});



});