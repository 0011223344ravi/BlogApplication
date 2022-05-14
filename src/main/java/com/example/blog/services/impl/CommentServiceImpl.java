package com.example.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entities.Comment;
import com.example.blog.entities.Post;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.payloads.CommentDto;
import com.example.blog.repositories.CommentRepo;
import com.example.blog.repositories.PostRepo;
import com.example.blog.services.CommentService;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
	private PostRepo postRepo;
    @Autowired
	private CommentRepo commentRepo;
    
    @Autowired
    private ModelMapper modelMapper;
	
	
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
	Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post Id",postId ));
	
	Comment comment = this.modelMapper.map(commentDto, Comment.class );
	comment.setPost(post);
	Comment savedComment = this.commentRepo.save(comment);
    return 	this.modelMapper.map(savedComment, CommentDto.class);
	
	
	
	}

	@Override
	public void deleteComment(Integer commentId) {
		 Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment ", "Comment Id",commentId));
		 this.commentRepo.delete(comment);
		
	}

}
