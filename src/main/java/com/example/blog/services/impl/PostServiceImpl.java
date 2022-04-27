package com.example.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.blog.entities.Category;
import com.example.blog.entities.Post;
import com.example.blog.entities.User;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.payloads.PostDto;
import com.example.blog.payloads.PostResponse;
import com.example.blog.repositories.CategoryRepo;
import com.example.blog.repositories.PostRepo;
import com.example.blog.repositories.UserRepo;
import com.example.blog.services.PostService;


@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;

	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
    private UserRepo userRepo;
	
	@Autowired 
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User ","User id", userId));
		Category category =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ", "Category Id" ,categoryId));
		Post post =this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(null); 
		
		post.setCategory(category);
		post.setUser(user);
		
		Post newPost = this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post ", "PostID ", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		//post.setImageName(postDto.setImageName("ravi.png"));
		Post updatedPost = this.postRepo.save(post);
		PostDto postDto1 = this.modelMapper.map(updatedPost, PostDto.class);
		
		return postDto1;
	}

	@Override
	public void deletePost(Integer postId) {
    Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post ", "PostID ", postId));
	this.postRepo.delete(post);	
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort= Sort.by(sortBy).ascending();
			
		}else {
			sort= Sort.by(sortBy).descending();
		}
		
		org.springframework.data.domain.Pageable p = PageRequest.of(pageNumber,pageSize , sort) ;
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allPosts = pagePost.getContent();
		//List<Post> allPosts = this.postRepo.findAll();
		List<PostDto> allPostDto =allPosts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(allPostDto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}
	
	@Override
	public List<PostDto> getAllPost_() {
		List<Post > allPosts =this.postRepo.findAll();
		
		List<PostDto> allPostDto =allPosts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		 
		return allPostDto;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post" , "Post id", postId));
		PostDto postDto =this.modelMapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer categoryId) {
	Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ", "Category Id",categoryId	));
	List<Post> posts = this.postRepo.findByCategory(cat);
	List<PostDto>  postDtos= posts.stream().map(post->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

	return postDtos;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException());
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto>  postDtos= posts.stream().map(post->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

		return postDtos;
	//	return null;
	}

	 

	@Override
	public List<PostDto> serachPosts(String keyword) {
	 List<Post>  posts =this.postRepo.searchByTitle("%"+keyword+"%");
	 List<PostDto> postDtos =posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	

}
