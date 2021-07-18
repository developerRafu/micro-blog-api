package com.br.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.blog.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{

}
