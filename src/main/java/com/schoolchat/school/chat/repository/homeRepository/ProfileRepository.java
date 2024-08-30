package com.schoolchat.school.chat.repository.homeRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolchat.school.chat.model.homeModels.ProfileModel;
@Repository
public interface ProfileRepository extends JpaRepository<ProfileModel, Integer> {
	


	Optional<ProfileModel> findByProfileId(Integer profileId);


}
