package com.schoolchat.school.chat.model.homeModels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "post_img_table")
public class PostImgModel {

	@Column(name = "img_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer imgId;


	@OneToOne
	@JoinColumn(name = "fk_post_id")
	PostModel postModel;



	@Column(name = "post_images")
	String postImage;



	public Integer getImgId() {
		return imgId;
	}



	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}



	public PostModel getPostModel() {
		return postModel;
	}



	public void setPostModel(PostModel postModel) {
		this.postModel = postModel;
	}



	public String getPostImage() {
		return postImage;
	}



	public void setPostImage(String postImage) {
		this.postImage = postImage;
	}


	public String toString() {
		return "PostImgModel [imgId=" + imgId + ", postModel=" + postModel + ", postImage=" + postImage + "]";
	}
	

	
}
