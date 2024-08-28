package com.schoolchat.school.chat.model.homeModels;

import com.schoolchat.school.chat.model.UsersModel;

import jakarta.persistence.*;


@Entity
@Table(name = "profile_table")
public class ProfileModel {
	
	@Column(name = "profile_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer profile_Id;

	@OneToOne
	@JoinColumn(name = "fk_user_id")
	UsersModel usersModel;


	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] profileImage;



	public ProfileModel() {
		
	}

	public Integer getProfile_Id() {
		return profile_Id;
	}

	public void setProfile_Id(Integer profile_Id) {
		this.profile_Id = profile_Id;
	}

	public UsersModel getUsersModel() {
		return usersModel;
	}

	public void setUsersModel(UsersModel usersModel) {
		this.usersModel = usersModel;
	}

	public byte[] getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}

	public String toString() {
		return "ProfileModel [profile_Id=" + profile_Id + ", usersModel=" + usersModel + ", profileImage="
				+ new String(profileImage) + "]";
	}

}
