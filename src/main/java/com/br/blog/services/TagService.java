package com.br.blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.blog.domain.Tag;
import com.br.blog.repositories.TagRepository;

@Service
public class TagService {

	@Autowired
	private TagRepository repository;

	public List<Tag> findAll() {
		return this.repository.findAll();
	}

	public Tag findById(Long id) {
		return this.repository.findById(id).orElseThrow(() -> new RuntimeException("Error ao procurar objeto"));
	}

	public Tag save(Tag obj) {
		return this.repository.save(obj);
	}

	public Tag update(Tag obj, Long id) {
		this.findById(id);
		if (obj.getId().equals(id)) {
			return this.save(obj);
		}
		throw new RuntimeException("Erro ao localizar post");
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
