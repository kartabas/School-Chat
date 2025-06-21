package com.schoolchat.school.chat.service.homeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.model.CommentModels.CommentModel;
import com.schoolchat.school.chat.model.homeModels.PostModel;
import com.schoolchat.school.chat.repository.homeRepository.PostRepository;
import com.schoolchat.school.chat.service.commentService.CommentService;
import com.schoolchat.school.chat.service.likesService.LikeService;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentService commentService;

	@Autowired
	private LikeService likeService;

	public PostService() {
		this.postRepository = postRepository;
	}

	public PostModel findById(Integer id) {
		return (PostModel) postRepository.findAll();
	}

	public PostModel getPost(Integer id) {
		return postRepository.findById(id).orElse(null);
	}

	// Save Post Servise
	public PostModel saveUserPost(String schoolId, UsersModel userId, String message, String sendTime,
			String postImage) {
		if (schoolId == null || userId == null) {
			return null;
		}

		try {
			PostModel postModel = new PostModel();

			postModel.setSchoolId(schoolId);
			postModel.setUsersModel(userId);
			postModel.setMeassage(message);
			postModel.setSendTime(sendTime);
			postModel.setPostImage(postImage);

			return postRepository.save(postModel);
		} catch (DataIntegrityViolationException e) {

			System.err.println("Error in create new post: " + e.getMessage());
			return null;
		} catch (Exception e) {

			System.err.println("Error: " + e.getMessage());
			return null;
		}
	}

	public PostModel getLastPostModel(UsersModel userId) {

		return (PostModel) postRepository.findByUsersModel(userId);
	}

	// Show all post of user
	public List<PostModel> getPostUserList(UsersModel userId) {
		return postRepository.findByUsersModel(userId);

	}

	// Show all post
	public List<PostModel> getAllPosts() {
		return (List<PostModel>) postRepository.findAll();
	}

	// Show all post of school
	public List<PostModel> getAllSchoolPosts(String schoolId) {
		return postRepository.findBySchoolId(schoolId);
	}

	// Update Post
	public PostModel updatePost(Integer id, String message, String sendTime, String postImage) {

		PostModel postModel = postRepository.findById(id).orElse(null);
		postModel.setMeassage(message);
		postModel.setSendTime(sendTime);
		postModel.setPostImage(postImage);
		return postRepository.save(postModel);
	}

	// Delete Post
	public void deleteByPostId(Integer postId) {
		postRepository.deleteByPostId(postId);
	}

	public PostModel updatePostModel(PostModel postModel) {
		return postRepository.save(postModel);

	}

	// Get the count of comments under a post
	public int getCountCommentUnderPost(Integer postId) {

		List<CommentModel> commentList = commentService.getListCommentsUnderPost(postId);
		if (commentList == null) {
			return 0;
		}
		return commentList.size();
	}

	// Like Post
	public boolean likePost(Integer postId , Integer userId) {
		try {
			PostModel post = postRepository.findById(postId).orElse(null);
			if (post != null) {
				
				likeService.saveLikeUnderPost(postId, userId);
				post.setLikeCount(post.getLikeCount() + 1);
				postRepository.save(post);
				return true;
			} else {
				System.err.println("Post not found with ID: " + postId);
				return false;

			}

		} catch (Exception e) {
			System.err.println("Error liking post: " + e.getMessage());
			return false;

		}

	}

	// Unlike Post
	public boolean unlikePost(Integer postId , Integer userId) {
		try {
			PostModel post = postRepository.findById(postId).orElse(null);
			if (post != null) {
				if (post.getLikeCount() != null && post.getLikeCount() > 0) {
					likeService.deleteLikeUnderPost(postId, userId);
					post.setLikeCount(post.getLikeCount() - 1);
				} else {
					System.err.println("Cannot unlike post, like count is already zero.");
					return false;
				}
				postRepository.save(post);
				return true;
			} else {
				System.err.println("Post not found with ID: " + postId);
				return false;
			}
		} catch (Exception e) {
			System.err.println("Error unliking post: " + e.getMessage());
			return false;
		}
	}

	public Long getLikeCount(Integer postId) {
		PostModel post = postRepository.findById(postId).orElse(null);
		if (post != null) {
			return (long) (post.getLikeCount() != null ? post.getLikeCount() : 0);
		} else {
			System.err.println("Post not found with ID: " + postId);
			return 0L;
		}
	}

	public boolean isPostLiked(Integer postId, Integer userId) {
		try {
			return likeService.isPostLikedByUser(postId, userId);
		} catch (Exception e) {
			System.err.println("Error checking if post is liked: " + e.getMessage());
			return false;
		}
	}

}
