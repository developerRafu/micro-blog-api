package com.br.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.blog.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
