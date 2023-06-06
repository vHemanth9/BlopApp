package com.springboot.blog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.Entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	
	List<Comment> findByPostId(long postId);
}
