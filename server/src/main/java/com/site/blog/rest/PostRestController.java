package com.site.blog.rest;

import com.site.blog.entity.BlogPost;
import com.site.blog.service.BlogPostService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostRestController {

    private final BlogPostService postService;

    public PostRestController(BlogPostService postService) {
        this.postService = postService;
    }

    @GetMapping("/test")
    public Map<String, String> sayHello() {
        return Map.of("message", "Hello World!");
    }

    @GetMapping
    public List<BlogPost> getPosts() {
        return this.postService.getAllPosts();
    }

    @DeleteMapping("/")
    public String deletePosts() {
        this.postService.removeAllPosts();
        return "All blog posts deleted";
    }


}
