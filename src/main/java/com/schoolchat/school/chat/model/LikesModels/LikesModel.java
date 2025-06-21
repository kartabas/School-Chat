package com.schoolchat.school.chat.model.LikesModels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "likes_table")
public class LikesModel {

	@Column(name = "like_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long likeId;

	@Column(name = "post_id")
	private Integer postId;

	@Column(name = "user_id")
	private Integer userId;

	public LikesModel() {
	}

	public LikesModel(Long likeId, Integer postId, Integer userId) {
		this.likeId = likeId;
		this.postId = postId;
		this.userId = userId;
	}

	public Long getLikeId() {
		return likeId;
	}

	public void setLikeId(Long likeId) {
		this.likeId = likeId;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "LikesModel [likeId=" + likeId + ", postId=" + postId + ", userId=" + userId + "]";
	}

}