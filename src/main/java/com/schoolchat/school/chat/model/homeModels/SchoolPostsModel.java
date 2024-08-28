package com.schoolchat.school.chat.model.homeModels;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.schoolchat.school.chat.model.UsersModel;

import jakarta.persistence.*;

@Entity
@Table(name = "school_posts_table")
public class SchoolPostsModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer school_post_id ;

	


	@Column(name = "school_id" )
	private String school_id;



	@ManyToOne
	@JoinColumn(name = "fk_post_id")
	PostModel postModel;

	@OneToOne
	@JoinColumn(name = "fk_user_id")
	UsersModel usersModel;



	public Integer getSchool_post_id() {
		return school_post_id;
	}
	public void setSchool_post_id(Integer school_post_id) {
		this.school_post_id = school_post_id;
	}


	public PostModel getPostModel() {
		return postModel;
	}
	public void setPostModel(PostModel postModel) {
		this.postModel = postModel;
	}

	public UsersModel getUsersModel() {
		return usersModel;

	}
	public void setUsersModel(UsersModel usersModel) {
		this.usersModel = usersModel;
	}


	public String toString() {
		return "SchoolPostsModel [school_id=" + school_post_id + ", postModel=" + postModel + ", usersModel=" + usersModel
				+ "]";
	}




	
}
