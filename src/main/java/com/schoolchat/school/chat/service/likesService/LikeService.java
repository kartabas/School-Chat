package com.schoolchat.school.chat.service.likesService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolchat.school.chat.model.LikesModels.LikesModel;
import com.schoolchat.school.chat.repository.likesRepository.LikeRepository;

@Service
public class LikeService {

	@Autowired
	private LikeRepository likeRepository;

	public LikeService() {
		this.likeRepository = likeRepository;
	}

	public Long getLikeCount(Integer postId) {
		return likeRepository.countByPostId(postId);
	}

	public LikesModel saveLikeUnderPost(Integer postId, Integer userId) {
		LikesModel likesModel = new LikesModel();
		likesModel.setPostId(postId);
		likesModel.setUserId(userId);
		return likeRepository.save(likesModel);
	}

	public LikesModel deleteLikeUnderPost(Integer postId, Integer userId) {
		LikesModel likesModel = likeRepository.findByPostIdAndUserId(postId, userId);
		if (likesModel != null) {
			likeRepository.delete(likesModel);
			return likesModel;
		}
		return null;
	}

	public List<LikesModel> deletePerPostId(Integer postId) {
		List<LikesModel> likesModel = likeRepository.findByPostId(postId);
		if (likesModel != null) {
			likeRepository.deleteAll(likesModel);
			return likesModel;
		}
		return null;
	}

	public boolean isPostLikedByUser(Integer postId, Integer userId) {
		LikesModel likesModel = likeRepository.findByPostIdAndUserId(postId, userId);
		return likesModel != null;
	}

}
