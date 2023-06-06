package com.springboot.blog.Service;



import java.util.List;

import com.springboot.blog.Dto.PostDto;


public interface PostService {
 
	PostDto createpost(PostDto postdto);
	
	List<PostDto> getAllpost();
	
	PostDto getpostbyid(long id);
	
	PostDto updatepost(PostDto postdto,long id);
	
	void deletePost(long id);
}
