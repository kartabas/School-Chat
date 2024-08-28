package com.schoolchat.school.chat.model.homeModels;

import com.schoolchat.school.chat.model.SchoolModel;
import com.schoolchat.school.chat.model.UsersModel;

import jakarta.persistence.*;


@Entity
@Table(name = "posts_table")
public class PostModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer post_id ;



	
	@Column(name = "fk_school_id")
	private String school_id;

	@ManyToOne()
	@JoinColumn(name = "fk_user_id")
	UsersModel usersModel;



	@ManyToOne
	@JoinColumn(name = "fk_profile_id")
	ProfileModel profileModel;
	


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
