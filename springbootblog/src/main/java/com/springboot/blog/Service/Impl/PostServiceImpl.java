package com.springboot.blog.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blog.Dto.PostDto;
import com.springboot.blog.Entity.Post;
import com.springboot.blog.ExceptionHandler.ResourceNotFoundException;
import com.springboot.blog.Repository.PostRepository;
import com.springboot.blog.Service.PostService;
@Service
public class PostServiceImpl implements PostService{

	@Autowired
	PostRepository postrepo;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public PostDto createpost(PostDto postdto) {
		
		Post post=maptoEntity(postdto);
		
		Post newpost=postrepo.save(post);
		
		//convert entity to dto
		
		PostDto postresponse=maptoDto(newpost);
		
		return postresponse;
	}

	@Override
	public List<PostDto> getAllpost() {
		
		List<Post> posts=postrepo.findAll();
		 return posts.stream().map(post->maptoDto(post)).collect(Collectors.toList());
		 
	}

	@Override
	public PostDto getpostbyid(long id) {
		
		Post post=postrepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id));
		
		return maptoDto(post);
	}

	@Override
	public PostDto updatepost(PostDto postdto, long id) {
		
		Post post=postrepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id));

		post.setTitle(postdto.getTitle());
		post.setDescription(postdto.getDescription());
		post.setContent(postdto.getContent());
		
		Post updatedpost=postrepo.save(post);
		
		return maptoDto(updatedpost);
	}
	
	@Override
	public void deletePost(long id) {
		Post post=postrepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id));
         postrepo.delete(post);
	}
	
	

	 private  PostDto maptoDto(Post post)
	 {
		 PostDto postdto=mapper.map(post, PostDto.class);
		 
//		 PostDto postdto=new PostDto();
//		 postdto.setId(post.getId());
//		 postdto.setTitle(post.getTitle());
//		 postdto.setContent(post.getContent());
//		 postdto.setDescription(post.getDescription());
		 return postdto;
	 }
	 
	 private Post maptoEntity(PostDto postdto)
	 {
		 Post post=mapper.map(postdto, Post.class);
		 
//		 Post post=new Post();
//		 post.setTitle(postdto.getTitle());
//		 post.setContent(postdto.getContent());
//		 post.setDescription(post.getDescription());
		 return post;
	 }

	

}
