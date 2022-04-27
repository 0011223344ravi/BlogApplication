package com.example.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.blog.config.AppConstants;
import com.example.blog.entities.Post;
import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.PostDto;
import com.example.blog.payloads.PostResponse;
import com.example.blog.payloads.UserDto;
import com.example.blog.repositories.PostRepo;
import com.example.blog.services.PostService;
import com.example.blog.config.AppConstants;
@RestController
@RequestMapping("/api/posts")



public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostRepo postRepo;
	
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId )
	{
		PostDto createPost =this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
		
	}
	
	//getbyUser
	
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>>getPostByUser(@PathVariable Integer userId){
		List<PostDto> posts = this.postService.getAllPostByUser(userId);
		
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>>getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> posts = this.postService.getAllPostByCategory(categoryId);
		
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity< PostDto>getPostBy_Id(@PathVariable Integer postId){
		PostDto posts = this.postService.getPostById(postId);
		
		
		return new ResponseEntity< PostDto>(posts,HttpStatus.OK);
		
	}
	
	
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse>getAllPost(
			
			@RequestParam(value ="pageNumber ",defaultValue =  AppConstants.PAGE_NUMBER  , required =false )Integer pageNumber ,

			@RequestParam(value ="pageSize ", defaultValue=AppConstants.PAGE_SIZE  , required =false )Integer pageSize,
			@RequestParam(value ="sortBy ",defaultValue= AppConstants.SORT_BY  , required =false )String sortBy,
			@RequestParam(value ="sortDir ",defaultValue= AppConstants.SORT_DIR, required =false )String sortDir
		
		  ){
		
		
		PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize,sortBy,sortDir);
		
		
		return new ResponseEntity< PostResponse>(postResponse,HttpStatus.OK);
		
	}
	
	@GetMapping("/getAllPosts")
	public ResponseEntity<List<PostDto>>getAllPost_( ){
		
		
		List<PostDto> posts  = this.postService.getAllPost_( );
		
		
		return new ResponseEntity< List<PostDto>>(posts,HttpStatus.OK);
		
	}
	


@PutMapping("/posts/{postId}")
public ResponseEntity<PostDto> updatePost( @RequestBody  PostDto postDto, @PathVariable Integer postId  ){
	
	
	PostDto postDto1 =this.postService.updatePost(postDto,postId);
	return   ResponseEntity.ok(postDto1);
	
}

//delete

@DeleteMapping("posts/{postId}")
public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
	this.postService.deletePost(postId);
	
	
	return  new ResponseEntity<ApiResponse>(new ApiResponse("post deleted successfully ",true), HttpStatus.OK);
	
	
}

@GetMapping("/posts/search/{keywords}")
public ResponseEntity<List<PostDto>>serachPostByTitle(@PathVariable("keywords")String keywords
		
		){
	
	
	List<PostDto> posts  = this.postService.serachPosts( keywords);
	
	
	return new ResponseEntity< List<PostDto>>(posts,HttpStatus.OK);
	
}


	
	
	 
	
	

}
