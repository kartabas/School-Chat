package com.schoolchat.school.chat.repository.homeRepository;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolchat.school.chat.model.homeModels.SchoolPostsModel;

@Repository
public interface SchoolPostRepository extends JpaRepository<SchoolPostsModel, Integer> {


	Optional<SchoolPostsModel> findBySchoolPostId(Integer schoolPostId);
}
