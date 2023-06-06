package com.springboot.blog.Dto;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
	private long id;
	
	@NotEmpty
	@Size(min=2,message="title should be atleast 2 chartacters")
	private String title;
	
	@NotEmpty
	@Size(min=2,message="description should be atleast 2 chartacters")
	private String description;
	
	@NotEmpty
	//@Size(min=5,message="content should be atleast 2 chartacters")
	private String content;
	
	private Set<CommentDto> comments;

}
