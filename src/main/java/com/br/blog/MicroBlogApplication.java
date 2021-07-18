package com.br.blog;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.blog.domain.Post;
import com.br.blog.domain.Tag;
import com.br.blog.services.PostService;

@SpringBootApplication
public class MicroBlogApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MicroBlogApplication.class, args);
	}
	
	@Autowired
	private PostService postService;

	@Override
	public void run(String... args) throws Exception {
		Post p1 = new Post(null, "Stone ocean teaser", "Após anos Stone Ocean Será lançado, as aventuras de Jolyne Kujo finalmente serão animadas", LocalDateTime.now());
		Tag t1 = new Tag(null, "#StoneOcean");
		Tag t2 = new Tag(null, "#JJBA");
		p1.getTags().addAll(Arrays.asList(t1,t2));
		this.postService.save(p1);
	}

}
