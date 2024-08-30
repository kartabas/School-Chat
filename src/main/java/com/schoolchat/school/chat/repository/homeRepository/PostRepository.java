package com.schoolchat.school.chat.repository.homeRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolchat.school.chat.model.homeModels.PostModel;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Integer> {


	// Optional<PostModel> findAllByPost_id(Integer post_id);

	// Optional<PostModel> findAllBySchool_id(String school_id);

	Optional<PostModel> findByPostId(Integer postId);
	
	
}
