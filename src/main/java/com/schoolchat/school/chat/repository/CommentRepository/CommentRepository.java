package com.schoolchat.school.chat.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.model.CommentModels.CommentModel;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel, Integer> {

	CommentModel findByCommentId(Long commentId);

	Optional<CommentModel> findByCommentIdAndUsersModel(Integer commentId, UsersModel usersModel);

	List<CommentModel> findByPostId(Integer postId);

	CommentModel deleteByCommentId(Long commentId);


}
