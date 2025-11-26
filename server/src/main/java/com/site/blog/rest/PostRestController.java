package com.site.blog.rest;

import com.site.blog.entity.Post;
import com.site.blog.input.PostInput;
import com.site.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostRestController {

    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = this.postService.getAllPostsSortedTimestamp();
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(
            @PathVariable String postId) {
        Post post = this.postService.getPost(postId);
        return ResponseEntity.ok().body(post);
    }

    @PostMapping
    public ResponseEntity<Post> addPost(@Valid @RequestBody PostInput postInput) {
        Post post = this.postService.createPost(postInput);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(post);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(
            @PathVariable String postId,
            @Valid @RequestBody PostInput postInput) {
        Post post = this.postService.updatePost(postId, postInput);
        return ResponseEntity.ok().body(post);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String postId) {
        this.postService.removePost(postId);
    }
}
