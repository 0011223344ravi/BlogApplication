package com.example.blog.services;

import java.util.List;

import com.example.blog.entities.User;
import com.example.blog.payloads.UserDto;

public interface UserService {
	
	UserDto creatUser(UserDto user);
	UserDto updateUser(UserDto user,Integer UserId  );
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	

}
