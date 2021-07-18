package com.br.blog.controllers;

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

import com.br.blog.domain.Tag;
import com.br.blog.services.TagService;

@RestController
@RequestMapping(value = "/tags")
public class TagRestController {

	@Autowired
	private TagService service;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(this.service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.findById(id));
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Tag obj) {
		return ResponseEntity.ok(this.service.save(obj));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@RequestBody Tag obj, @PathVariable Long id) {
		return ResponseEntity.ok(this.service.update(obj, id));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.delete(id));
	}

}
