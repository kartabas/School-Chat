package com.schoolchat.school.chat.repository.likesRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolchat.school.chat.model.LikesModels.LikesModel;

@Repository
public interface LikeRepository extends JpaRepository<LikesModel, Long> {

	LikesModel findByPostIdAndUserId(Integer postId, Integer userId);

	List<LikesModel> findByPostId(Integer postId);

	Long countByPostId(Integer postId);

	void deleteByLikeId(Long likeId);

	Optional<LikesModel> findByLikeId(Long likeId);

}
