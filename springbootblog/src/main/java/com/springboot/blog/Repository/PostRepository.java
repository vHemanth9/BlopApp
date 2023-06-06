package com.springboot.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.blog.Entity.Post;
@Repository
public interface PostRepository  extends JpaRepository<Post, Long>{
	

}
