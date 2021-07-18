package com.br.blog.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.blog.domain.Post;
import com.br.blog.domain.Tag;
import com.br.blog.repositories.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	@Autowired
	private TagService tagService;

	public List<Post> findAll() {
		return this.repository.findAll();
	}

	public Post findById(Long id) {
		return this.repository.findById(id).orElseThrow(() -> new RuntimeException("Error ao procurar objeto"));
	}

	@Transactional
	public Post save(Post obj) {
		obj.setInstant(LocalDateTime.now());
		this.repository.save(obj);
		for(Tag t : obj.getTags()) {
			this.tagService.save(t);
			t.getPosts().add(obj);
			this.tagService.save(t);
		}
		return this.repository.save(obj);
	}

	public Post update(Post obj, Long id) {
		Post postFound = this.findById(id);
		postFound.setTitle(obj.getTitle());
		postFound.setContent(obj.getContent());
		obj.setEdited(true);
		return this.save(postFound);
	}

	public boolean delete(Long id) {
		try {
			this.repository.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao deletar post");
		}
		return true;
	}

}
