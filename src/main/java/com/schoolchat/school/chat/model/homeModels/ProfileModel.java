package com.schoolchat.school.chat.model.homeModels;

import com.schoolchat.school.chat.model.UsersModel;

import jakarta.persistence.*;


@Entity
@Table(name = "profile_table")
public class ProfileModel {
	
	@Column(name = "profile_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer profileId;

	@OneToOne
	@JoinColumn(name = "fk_user_id")
	UsersModel usersModel;



	@Column(name = "profile_image")
	private String profileImage;



	public ProfileModel() {
		
	}

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfile_Id(Integer profileId) {
		this.profileId = profileId;
	}

	public Integer getUsersModel() {
		return usersModel.getId();
	}

	public void setUsersModel(UsersModel usersModel) {
		this.usersModel = usersModel;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String toString() {
		return "ProfileModel [profile_Id=" + profileId + ", usersModel=" + usersModel + ", profileImage="
				+ new String(profileImage) + "]";
	}

}
