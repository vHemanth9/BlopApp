package com.springboot.blog.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.Dto.CommentDto;
import com.springboot.blog.Entity.Comment;
import com.springboot.blog.Entity.Post;
import com.springboot.blog.ExceptionHandler.BlogApiException;
import com.springboot.blog.ExceptionHandler.ResourceNotFoundException;
import com.springboot.blog.Repository.CommentRepository;
import com.springboot.blog.Repository.PostRepository;
import com.springboot.blog.Service.CommentService;

@Service
public class CommentServiceImpl  implements CommentService{

	@Autowired
	CommentRepository commentrepo;
	
	@Autowired ModelMapper mapper;
	
	@Autowired
	PostRepository postrepo;
	
	@Override
	public CommentDto createComment(long postid, CommentDto commentdto) {
		
		Comment comment=maptoEntity(commentdto);
		
		Post post=postrepo.findById(postid).orElseThrow(
				()->new ResourceNotFoundException("Post", "id", postid));
		
		comment.setPost(post);
		
		Comment res=commentrepo.save(comment);
		
		CommentDto response=maptoDto(res);
		
		return response;
		
	}
	
	//convert Dto to entity
	private Comment maptoEntity(CommentDto commentdto)
	{
		Comment comment=mapper.map(commentdto, Comment.class);
//		Comment comment=new Comment();
//		comment.setId(commentdto.getId());
//		comment.setBody(commentdto.getBody());
//		comment.setEmail(commentdto.getEmail());
//		comment.setName(commentdto.getName());
		return comment;
	}
	
	private CommentDto maptoDto(Comment comment)
	{
		CommentDto commentres=mapper.map(comment, CommentDto.class);
//		CommentDto commentres=new CommentDto();
//		commentres.setBody(comment.getBody());
//		commentres.setEmail(comment.getEmail());
//		commentres.setId(comment.getId());
//		commentres.setName(comment.getName());
		return commentres;
	}

	@Override
	public List<CommentDto> getCommentByPostId(long postId) {
		// TODO Auto-generated method stub
		List<Comment> comments=commentrepo.findByPostId(postId);
		
		return comments.stream().map(comment->maptoDto(comment)).collect(Collectors.toList()) ;
	}

	@Override
	public CommentDto getCommentById(long commentId) {
		Comment response=commentrepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "CommentId", commentId));
		return maptoDto(response);
	}

	@Override
	public CommentDto getCommentById(long postId, long commentId) {
		// TODO Auto-generated method stub
		
		Post post=postrepo.findById(postId).orElseThrow(
				()->new ResourceNotFoundException("Post", "id", postId));
		
		Comment response=commentrepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "CommentId", commentId));

		if(!response.getPost().getId().equals(post.getId()))
		{
			throw  new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to post");
		}
		
		return maptoDto(response);
	}

	@Override
	public CommentDto updateCommentById(long postId, long commentId,CommentDto commentdto) {
		
		Post post=postrepo.findById(postId).orElseThrow(
				()->new ResourceNotFoundException("Post", "id", postId));
		
		Comment response=commentrepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "CommentId", commentId));

		if(!response.getPost().getId().equals(post.getId()))
		{
			throw  new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to post");
		}
		
		response.setBody(commentdto.getBody());
		response.setEmail(commentdto.getEmail());
		response.setName(commentdto.getName());
		
		Comment updatedcomment=commentrepo.save(response);
		
		return maptoDto(updatedcomment);
	}

	@Override
	public String deleteCommentById(long postId, long commentId) {
		// TODO Auto-generated method stub

		Post post=postrepo.findById(postId).orElseThrow(
				()->new ResourceNotFoundException("Post", "id", postId));
		
		Comment response=commentrepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "CommentId", commentId));

		if(!response.getPost().getId().equals(post.getId()))
		{
			throw  new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to post");
		}
		commentrepo.delete(response);
		
		return "Comment related to post is successfully deleted";
	}
	
	

}
