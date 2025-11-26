package com.site.blog.rest;

import com.site.blog.entity.Post;
import com.site.blog.input.PostInput;
import com.site.blog.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Post API")
public class PostRestController {

    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @Operation(summary = "List all posts", description = "A list of posts")
    @ApiResponse(responseCode = "200", description = "A list of posts")
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = this.postService.getAllPostsSortedTimestamp();
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping("/{postId}")
    @Operation(summary = "Get a post by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A single post"),
            @ApiResponse(responseCode = "404", description = "Post not found", content = @Content)})
    public ResponseEntity<Post> getPost(
            @PathVariable String postId) {
        Post post = this.postService.getPost(postId);
        return ResponseEntity.ok().body(post);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new post")
    @ApiResponse(responseCode = "201", description = "Post created")
    public ResponseEntity<Post> addPost(@Valid @RequestBody PostInput postInput) {
        Post post = this.postService.createPost(postInput);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(post);
    }

    @PutMapping(value = "/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a post by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post updated"),
            @ApiResponse(responseCode = "404", description = "Post not found", content = @Content)})
    public ResponseEntity<Post> updatePost(
            @PathVariable String postId,
            @Valid @RequestBody PostInput postInput) {
        Post post = this.postService.updatePost(postId, postInput);
        return ResponseEntity.ok().body(post);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update a post by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Post deleted"),
            @ApiResponse(responseCode = "404", description = "Post not found", content = @Content)})
    public void deletePost(@PathVariable String postId) {
        this.postService.removePost(postId);
    }
}
