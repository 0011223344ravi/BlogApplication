package com.example.blog.services;

import java.util.List;

import com.example.blog.entities.Post;
import com.example.blog.payloads.PostDto;
import com.example.blog.payloads.PostResponse;

public interface PostService {

	
	//create
	
	PostDto createPost(PostDto postDto , Integer userId, Integer categoryId);
	
	//update 
	
	PostDto updatePost(PostDto postDto , Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all posts by pagination
	PostResponse getAllPost( Integer pageNumber,Integer pageSize, String sortBy,String sortDir);
	
	//get all posts
	
	List<PostDto> getAllPost_();
	
	//get single post 
	
	PostDto getPostById(Integer postId);
	
	//get all post by category
	
	List<PostDto>  getAllPostByCategory(Integer categoryId);
	
	//get all post by User
	List<PostDto> getAllPostByUser(Integer userId);
	
	List<PostDto> serachPosts(String keyword);
	
	
}
