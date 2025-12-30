package com.schoolchat.school.chat.model.CommentModels;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.model.homeModels.ProfileModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments_table")
public class CommentModel {

	@Column(name = "comment_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;

	@Column(name = "post_id")
	Long postId;

	@ManyToOne
	@JoinColumn(name = "fk_user_id")
	UsersModel usersModel;

	@ManyToOne
	@JoinColumn(name = "fk_profile_id")
	ProfileModel profileModel;

	@Column(name = "comment_message", columnDefinition = "TEXT")
	private String commentMessage;

	@Column(name = "comment_time")
	private String commentTime;

	public CommentModel() {
	}

	public CommentModel(Long commentId, UsersModel usersModel, ProfileModel profileModel, String commentMessage,
			String commentTime) {
		this.commentId = commentId;
		this.usersModel = usersModel;
		this.profileModel = profileModel;
		this.commentMessage = commentMessage;
		this.commentTime = commentTime;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public UsersModel getUsersModel() {
		return usersModel;
	}

	public void setUsersModel(UsersModel userId) {
		this.usersModel = userId;
	}

	public ProfileModel getProfileModel() {
		return profileModel;
	}

	public void setProfileModel(ProfileModel profileModel) {
		this.profileModel = profileModel;
	}

	public String getCommentMessage() {
		return commentMessage;
	}

	public void setCommentMessage(String commentMessage) {
		this.commentMessage = commentMessage;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	@Override
	public String toString() {
		return "CommentModel [commentId=" + commentId + ", usersModel=" + usersModel + ", profileModel=" + profileModel
				+ ", commentMessage=" + commentMessage + ", commentTime=" + commentTime + "]";
	}

}
