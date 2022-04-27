package com.example.blog.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.UserDto;
import com.example.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
    private UserService userService;
	
	
	//create
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody  UserDto userDto ){
		
		
		UserDto userDto1 =this.userService.creatUser(userDto);
		return new ResponseEntity<>(userDto1,HttpStatus.CREATED);
		
	}
	//upadte
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody  UserDto userDto, @PathVariable("userId") Integer uid  ){
		
		
		UserDto userDto1 =this.userService.updateUser(userDto,uid);
		return   ResponseEntity.ok(userDto1);
		
	}
	
	//delete
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
		this.userService.deleteUser(uid);
		
		
		return  new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully ",true), HttpStatus.OK);
		
		
	}
	
	//get
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserDto>> getAllUSers( ){
		return ResponseEntity.ok(this.userService.getAllUsers());
		
		
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUSers(@PathVariable("userId")  Integer uid  ){
		return ResponseEntity.ok(this.userService.getUserById(uid));
		
		
		
	}
	
}
