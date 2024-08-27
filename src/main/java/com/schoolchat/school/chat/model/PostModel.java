package com.schoolchat.school.chat.model;

import com.schoolchat.school.chat.Schools.SchoolModel;

import jakarta.persistence.*;


@Entity
@Table(name = "posts_table")
public class PostModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer post_id ;


	// @ManyToOne
	@Column(name =  "school_Id")
	private String school_Id;

	@ManyToOne()
	@JoinColumn(name = "fk_user_id")
	UsersModel usersModel;

	// @ManyToOne
	@Column(name = "fk_profile_id")
	private Integer profile_id;


	private String meassage;
	
	private String sendTime;


	@Column(name = "post_images")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] postImage;

	


	public PostModel() {
		
	}


	public Integer getPost_id() {
		return post_id;
	}
	public void setPost_id(Integer post_id) {
		this.post_id = post_id;
	}


	// public UsersModel getUsersModel() {
	// 	return usersModel;
	// }
	// public void setUsersModel(UsersModel usersModel) {
	// 	this.usersModel = usersModel;
	//}
	// public SchoolModel getSchoolModel() {
	// 	return schoolModel;
	// }
	// public void setSchoolModel(SchoolModel schoolModel) {
	// 	this.schoolModel = schoolModel;
	// }
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


	public byte[] getPostImage() {
		return postImage;
	}
	public void setPostImage(byte[] postImage) {
		this.postImage = postImage;
	}

	// public String toString() {
	// 	return "PostModel [post_id=" + post_id + ", schoolModel=" + schoolModel + ", usersModel=" + usersModel
	// 			+ ", meassage=" + meassage + ", sendTime=" + sendTime + ", postModel=" + postModel + "]";
	// }

	@Override
		public String toString() {
		return "PostModel [post_id=" + post_id + ", schoolModel=" + ", usersModel=" 
				+ ", meassage=" + meassage + ", sendTime=" + sendTime + ", postModel="  + "]";
	}
	
}
