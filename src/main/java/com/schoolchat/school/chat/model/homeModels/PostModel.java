package com.schoolchat.school.chat.model.homeModels;

import com.schoolchat.school.chat.model.UsersModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

	@Column(name = "post_image", columnDefinition = "TEXT")
	String postImage;

	@Column(name = "like_count")
	Long likeCount;

	public Long getLikeCount() {
		if (likeCount == null) {
			likeCount = 0L; // Initialize to 0 if null
		}
		return likeCount;
	}

	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}

	public String getPostImage() {
		return postImage;
	}

	public void setPostImage(String postImage) {
		this.postImage = postImage;
	}

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

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendTime() {
		return sendTime;
	}

	public String toString() {
		return "PostModel [postId=" + postId + ", schoolId=" + schoolId + ", usersModel=" + usersModel
				+ ", meassage=" + meassage + ", sendTime=" + sendTime + ", postImgModel=" + postImage + "]";
	}

}
