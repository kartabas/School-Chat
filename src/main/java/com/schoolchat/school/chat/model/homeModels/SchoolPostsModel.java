package com.schoolchat.school.chat.model.homeModels;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.schoolchat.school.chat.model.UsersModel;

import jakarta.persistence.*;

@Entity
@Table(name = "school_posts_table")
public class SchoolPostsModel {

	@Column(name = "school_post_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	Integer schoolPostId ;

	


	@Column(name = "school_id" )
	String schoolId;



	@ManyToOne
	@JoinColumn(name = "fk_post_id")
	PostModel postModel;

	@ManyToOne
	@JoinColumn(name = "fk_user_id")
	UsersModel usersModel;



	public Integer getSchool_post_id() {
		return schoolPostId;
	}
	public void setSchool_post_id(Integer schoolPostId) {
		this.schoolPostId = schoolPostId;
	}


	public Integer getPostModel() {
		return postModel.getPostId();
	}
	public void setPostModel(PostModel postModel) {
		this.postModel = postModel;
	}

	public Integer getUsersModel() {
		return usersModel.getId();

	}
	public void setUsersModel(UsersModel usersModel) {
		this.usersModel = usersModel;
	}


	public String toString() {
		return "SchoolPostsModel [school_id=" + schoolPostId + ", postModel=" + postModel + ", usersModel=" + usersModel
				+ "]";
	}




	
}
