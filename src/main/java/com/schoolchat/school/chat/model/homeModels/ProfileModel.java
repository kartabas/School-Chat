package com.schoolchat.school.chat.model.homeModels;

import com.schoolchat.school.chat.model.UsersModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

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

	@Column(name = "profile_image", columnDefinition = "TEXT")
	String profileImage;

	@Column(name = "profile_background", columnDefinition = "TEXT")
	String profileBackground;

	@Column(name = "profile_biography", columnDefinition = "TEXT")
	String profileBiography;

	public ProfileModel() {

	}

	public ProfileModel(String profileImage, String profileBackground,
			String profileBiography, UsersModel usersModel) {
		this.profileImage = profileImage;
		this.profileBackground = profileBackground;
		this.profileBiography = profileBiography;
		this.usersModel = usersModel;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	public String getProfileBackground() {
		return profileBackground;
	}

	public void setProfileBackground(String profileBackground) {
		this.profileBackground = profileBackground;
	}

	public String getProfileBiography() {
		return profileBiography;
	}

	public void setProfileBiography(String profileBiography) {
		this.profileBiography = profileBiography;
	}

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfile_Id(Integer profileId) {
		this.profileId = profileId;
	}

	public UsersModel getUsersModel() {
		return usersModel;
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
		return "ProfileModel [profileId=" + profileId + ", usersModel=" + usersModel + ", profileBiography="
				+ profileBiography + ", profileImage=" + profileImage
				+ ", profileBackground=" + profileBackground + "]";
	}

}
