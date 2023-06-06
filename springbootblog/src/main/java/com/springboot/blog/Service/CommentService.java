package com.springboot.blog.Service;

import java.util.List;

import com.springboot.blog.Dto.CommentDto;

public interface CommentService {
	
	CommentDto createComment(long postid,CommentDto commentdto);

	List<CommentDto> getCommentByPostId(long postId);
	
	CommentDto getCommentById(long commentId);
	
	CommentDto getCommentById(long postId,long commentId);
	
	CommentDto updateCommentById(long postId,long commentId,CommentDto commentdto);
	
	String deleteCommentById(long postId,long commentId);
}
