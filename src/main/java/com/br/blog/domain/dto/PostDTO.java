package com.br.blog.domain.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.br.blog.domain.Post;
import com.br.blog.domain.Tag;

public class PostDTO {

	private Long id;
	private String title;
	private String content;
	private LocalDateTime data;
	private Boolean edited;
	private Set<String> tags = new HashSet<>();

	public PostDTO() {

	}

	public PostDTO(Long id, String title, String content, LocalDateTime data) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.data = data;
	}

	public PostDTO(Post obj) {
		this.id = obj.getId();
		this.content = obj.getContent();
		this.title = obj.getTitle();
		this.data = obj.getInstant();
		this.edited = obj.getEdited();
		this.loadTags(obj.getTags());
	}

	private void loadTags(Set<Tag> tags2) {
		tags2.forEach(tag -> this.tags.add(tag.getTitle()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getData() {
		return data;
	}

	public Boolean getEdited() {
		return edited;
	}

	public void setEdited(Boolean edited) {
		this.edited = edited;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Set<String> getTags() {
		return tags;
	}

}
