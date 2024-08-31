package com.schoolchat.school.chat.model.homeModels;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.model.schoolModels.SchoolModel;

import jakarta.persistence.*;


@Entity
@Table(name = "posts_table")
public class PostModel {


	@Column(name = "post_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer postId;



	
	@Column(name = "fk_school_id")
	String schoolId;

	@ManyToOne
	@JoinColumn(name = "fk_user_id")
	UsersModel usersModel;



	// @ManyToOne
	// @JoinColumn(name = "fk_profile_id")
	// ProfileModel profileModel;
	@Column(name = "meassage")
	String meassage;
	
	@Column(name = "send_time")
	String sendTime;

	@Column(name = "post_images")
	String postImage ;

	




	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public Integer getUsersModel() {
		return usersModel.getId();
	}
	public void setUsersModel(UsersModel usersModel) {
		this.usersModel = usersModel;
	}

	public String getMeassage() {
		return meassage;
	}
	public void setMeassage(String meassage) {
		this.meassage = meassage;
	}
	public String getSendTime() {
		return sendTime;
	}	
	public void setTime(String time) {
		this.sendTime = time;
	}		


	public String getPostImage() {
		return postImage;
	}
	public void setPostImage(String postImage) {
		this.postImage = postImage;
	}



	@Override
		public String toString() {
		return "PostModel [postId=" + postId + ", schoolModel=" + ", usersModel=" 
				+ ", meassage=" + meassage + ", sendTime=" + sendTime + ", postModel="  + "]";
	}

	
}
