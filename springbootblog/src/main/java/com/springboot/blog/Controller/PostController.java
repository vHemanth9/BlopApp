package com.springboot.blog.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.Dto.PostDto;
import com.springboot.blog.Service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	PostService postservice;
	
	@PostMapping
	public ResponseEntity<PostDto> createpost(@Valid @RequestBody PostDto postdto)
	{
		return new ResponseEntity<>(postservice.createpost(postdto),HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<PostDto> Allposts()
	{
		return postservice.getAllpost();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("id")long id)
	{
      return ResponseEntity.ok(postservice.getpostbyid(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postdto, @PathVariable("id")long id)
	{
		return new ResponseEntity<>(postservice.updatepost(postdto, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePostById(@PathVariable("id")long id)
	{
		postservice.deletePost(id);
		
		return new ResponseEntity<>("post entity deleted successfully",HttpStatus.OK);
	}
}
