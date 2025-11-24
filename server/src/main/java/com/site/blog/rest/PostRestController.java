package com.site.blog.rest;

import com.site.blog.entity.BlogPost;
import com.site.blog.error.BlogPostNotFoundException;
import com.site.blog.service.BlogPostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        return this.postService.getAllPostsSortedTimestamp();
    }

    @DeleteMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String blogPostId) {
        if (this.postService.containsPost(blogPostId)) {
            this.postService.removePost(blogPostId);
        } else {
            throw new BlogPostNotFoundException();
        }
    }


}
