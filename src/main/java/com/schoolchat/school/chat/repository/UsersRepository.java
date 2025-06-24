package com.schoolchat.school.chat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolchat.school.chat.model.UsersModel;

public interface UsersRepository extends JpaRepository<UsersModel, Integer> {
	Optional<UsersModel> findByLoginAndPassword(String login, String password);

	Optional<UsersModel> findFirstByLogin(String login);

	Optional<UsersModel> findById(long id);

	Optional<UsersModel> findAllById(Integer id);

	Optional<UsersModel> readAllById(Integer id);

	Optional<UsersModel> findByEmail(String email);
}
