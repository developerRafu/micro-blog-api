package com.br.blog.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.blog.domain.Post;
import com.br.blog.domain.Tag;
import com.br.blog.domain.dto.PostDTO;
import com.br.blog.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostRestController {

	@Autowired
	private PostService service;

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<PostDTO> dtos = this.service.findAll()
				.stream()
				.map(post -> this.convertToDTO(post))
				.collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		PostDTO dto = this.convertToDTO(this.service.findById(id));
		return ResponseEntity.ok(dto);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody PostDTO obj) {
		Post post = this.convertToEntity(obj);
		PostDTO dto = this.convertToDTO(this.service.save(post));
		return ResponseEntity.ok(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@RequestBody PostDTO obj, @PathVariable Long id) {
		Post post = this.convertToEntity(obj);
		PostDTO dto = this.convertToDTO(this.service.update(post, id));
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.delete(id));
	}

	private PostDTO convertToDTO(Post post) {
		return new PostDTO(post);
	}

	private Post convertToEntity(PostDTO dto) {
		Post post = new Post(dto.getId(), dto.getTitle(), dto.getContent(), null);
		Set<Tag> tags = new HashSet<>();
		
		dto.getTags().forEach(tag -> tags.add(new Tag(null, tag)));
		
		post.getTags().addAll(tags);
		return post;
	}
}
