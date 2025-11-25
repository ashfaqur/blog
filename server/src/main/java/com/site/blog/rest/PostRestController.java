package com.site.blog.rest;

import com.site.blog.entity.Post;
import com.site.blog.input.PostInput;
import com.site.blog.response.PostDescriptionResponse;
import com.site.blog.response.PostListResponse;
import com.site.blog.response.PostResponse;
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
    public ResponseEntity<PostListResponse> getPosts() {
        List<Post> posts = this.postService.getAllPostsSortedTimestamp();
        return ResponseEntity.ok().body(new PostListResponse("A list of posts", posts));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPost(
            @PathVariable String postId) {
        Post post = this.postService.getPost(postId);
        return ResponseEntity.ok().body(new PostResponse("A single post", post));
    }

    @PostMapping
    public ResponseEntity<PostResponse> addPost(@Valid @RequestBody PostInput postInput) {
        Post post = this.postService.createPost(postInput);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new PostResponse("Post created", post));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable String postId,
            @Valid @RequestBody PostInput postInput) {
        Post post = this.postService.updatePost(postId, postInput);
        return ResponseEntity.ok().body(new PostResponse("Post updated", post));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<PostDescriptionResponse> deletePost(@PathVariable String postId) {
        this.postService.removePost(postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new PostDescriptionResponse("Post deleted"));
    }
}
