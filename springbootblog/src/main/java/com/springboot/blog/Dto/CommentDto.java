package com.springboot.blog.Dto;

import lombok.Data;

@Data
public class CommentDto {
	
	private long id;
    private String email;
    private String name;
    private String body;
	 

}
