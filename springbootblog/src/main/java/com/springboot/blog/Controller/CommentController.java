package com.springboot.blog.Controller;

import java.util.List;

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

import com.springboot.blog.Dto.CommentDto;
import com.springboot.blog.Service.CommentService;


@RestController
@RequestMapping("/api/comments")
public class CommentController {
	@Autowired
	CommentService commentservice;
	
	@PostMapping("/posts/{postid}/comments")
	public ResponseEntity<CommentDto> createcomment(@RequestBody CommentDto commentdto,@PathVariable("postid") long postid)
	{
		return new ResponseEntity<>(commentservice.createComment(postid, commentdto),HttpStatus.CREATED);
	}
	
	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getAllCommentByPostId(@PathVariable("postId")long postId)
	{
		return commentservice.getCommentByPostId(postId);
	}
	
	@GetMapping("/commentId/comment")
	public CommentDto getCommentByCommentId(@PathVariable("/commentId") long commentId)
	{
		return commentservice.getCommentById(commentId);
	}
	
	@GetMapping("/posts/{postId}/comments/{commentId}")
	public CommentDto getCommentById(@PathVariable("postId")long postId,@PathVariable("commentId") long commentId)
	{
		return commentservice.getCommentById(postId, commentId);
	}
	
	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updatecomment(@PathVariable("postId")long postId,@PathVariable("commentId")long commentId,@RequestBody CommentDto commentdto)
	{
		CommentDto updatedcomment=commentservice.updateCommentById(postId, commentId, commentdto);
		return new ResponseEntity<>(updatedcomment,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public String deleteCommentById(@PathVariable("postId")long postId,@PathVariable("commentId")long commentId)
	{
		return commentservice.deleteCommentById(postId, commentId);
	}

}
