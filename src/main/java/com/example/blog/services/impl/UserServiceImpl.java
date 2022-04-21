package com.example.blog.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.exceptions.*;
import com.example.blog.entities.User;
import com.example.blog.payloads.UserDto;
import com.example.blog.repositories.UserRepo;
import com.example.blog.services.UserService;


@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	
	private UserRepo userRepo ;
	
@Autowired
	
	private ModelMapper modelMapper ;
	
	
	
	@Override
	public UserDto creatUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.UserToUserDto(savedUser);
		
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","Id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updateUser = this.userRepo.save(user);
		UserDto userToDto = this.UserToUserDto(updateUser);
		
		return userToDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","Id",userId));
		
		 
		return this.UserToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDtos =users.stream().map(user->this.UserToUserDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","Id",userId));
		this.userRepo.delete(user);
	
	
	}
	
	  private User dtoToUser(UserDto userDto) {
			//Earlier implementation
		  
		  /*
			 * User user = new User();
			 * 
			 * user.setId(userDto.getId()); user.setName(userDto.getName());
			 * user.setEmail(userDto.getEmail()); user.setAbout(userDto.getAbout());
			 * user.setPassword(userDto.getPassword()); ;
			 * 
			 * 
			 * return user;
			 */
		  //new implementation
		  User user = this.modelMapper.map(userDto,User.class);
		  return user;
		  //getting the use of ModelMapper to map the one model into another
	 }
	 
	  
	  private UserDto UserToUserDto(User  user ) {
		  UserDto userDto = new UserDto();
		  
		  userDto.setId(user.getId());
		  userDto.setName(user.getName());
		  userDto.setEmail(user.getEmail());
		  userDto.setAbout(user.getAbout());
		  userDto.setPassword(user.getPassword());
		  
		  
		  
		  return userDto;
	 }
	 
}
