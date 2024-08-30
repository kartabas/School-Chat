package com.schoolchat.school.chat.service.homeService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.model.homeModels.PostModel;
import com.schoolchat.school.chat.repository.homeRepository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public PostService() {
		this.postRepository = postRepository;
	}

	public PostModel findById(Integer id) {
		return (PostModel) postRepository.findAll();
  }
  
  
    public PostModel getPost(Integer id) {
        return postRepository.findById(id).orElse(null);
    }


}
