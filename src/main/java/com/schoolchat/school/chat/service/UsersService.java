package com.schoolchat.school.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolchat.school.chat.model.UsersModel;
import com.schoolchat.school.chat.repository.UsersRepository;
import com.schoolchat.school.chat.security.BCryptHashing;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	public UsersService() {
		this.usersRepository = usersRepository;
	}

	public UsersModel registerUser(String login, String password, String email, String schoolId) {
		if (login == null || password == null) {
			return null;
		} else {

			if (usersRepository.findFirstByLogin(login).isPresent()) {
				System.out.println("Duplicate login");
				return null;
			}

			UsersModel usersModel = new UsersModel();

			usersModel.setLogin(login);
			usersModel.setPassword(BCryptHashing.hashPassword(password));
			// usersModel.setPassword(password);
			usersModel.setEmail(email);
			usersModel.setSchoolId(schoolId);
			return usersRepository.save(usersModel);
		}

	}

	public UsersModel authenticate(String login, String password) {
		boolean passwordFound =  BCryptHashing.verifyPassword(password, usersRepository.findFirstByLogin(login)
				.map(UsersModel::getPassword).orElse(null));
		if( !passwordFound ) {
			return null;

		}else {
			return usersRepository.findFirstByLogin(login).orElse(null);
		}
		//return usersRepository.findByLoginAndPassword(login, password).orElse(null);

	}

	public UsersModel getUser(Integer id) {
		return usersRepository.findById(id).orElse(null);
	}

	public boolean checkUsername(String username) {
		if (usersRepository.findFirstByLogin(username).isPresent()) {
			return false;
		}
		return true;

	}

	// public List<UsersModel> getUserbyId(Long id){
	// return (List<UsersModel>) usersRepository.getReferenceById(id);
	// }
}
