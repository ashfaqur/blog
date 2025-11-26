package com.site.blog;

import com.site.blog.entity.Post;
import com.site.blog.error.PostCreationException;
import com.site.blog.error.PostNotFoundException;
import com.site.blog.input.PostInput;
import com.site.blog.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PostServiceTest {

    private PostService postService;

    @BeforeEach
    public void setup() {
        this.postService = new PostService();
    }

    @Test
    public void testCreatePostSuccessfully() {
        PostInput input = new PostInput("John Doe", "2025-06-16T10:30:00Z", "Hello World");
        Post post = this.postService.createPost(input);

        assertNotNull(post.id());
        assertEquals(input.getAuthor(), post.author());
        assertEquals(input.getContent(), post.content());
        assertEquals(Instant.parse(input.getDate()), post.date());
    }

    @Test
    public void testGetPostSuccessfully() {
        PostInput input = new PostInput("John Doe", "2025-05-16T10:30:00Z", "First blog post");
        Post created = this.postService.createPost(input);

        Post fetchedPost = this.postService.getPost(created.id());
        assertEquals(created.id(), fetchedPost.id());
        assertEquals(created.author(), fetchedPost.author());
        assertEquals(created.date(), fetchedPost.date());
        assertEquals(created.content(), fetchedPost.content());
    }

    @Test
    void testGetPostNotFound() {
        assertThrows(PostNotFoundException.class, () -> this.postService.getPost("id-does-not-exist"));
    }

    @Test
    void testUpdatePostSuccessfully() {
        PostInput input = new PostInput("John Doe", "2025-05-16T10:30:00Z", "First blog post");
        Post created = this.postService.createPost(input);

        PostInput updatedInput = new PostInput("J Doe", "2025-06-16T10:30:00Z", "Edited blog post");
        Post updated = this.postService.updatePost(created.id(), updatedInput);

        assertEquals(created.id(), updated.id());
        assertEquals(updatedInput.getAuthor(), updated.author());
        assertEquals(updatedInput.getContent(), updated.content());
        assertEquals(Instant.parse(updatedInput.getDate()), updated.date());
    }

    @Test
    void testUpdatePostNotFound() {
        PostInput input = new PostInput("John Doe", "2025-05-16T10:30:00Z", "First blog post");
        assertThrows(PostNotFoundException.class, () -> this.postService.updatePost("invalid-id", input));
    }

    @Test
    void testRemovePostSuccessfully() {
        PostInput input = new PostInput("John Doe", "2025-05-16T10:30:00Z", "First blog post");
        Post post = this.postService.createPost(input);
        this.postService.removePost(post.id());
        assertThrows(PostNotFoundException.class, () -> this.postService.getPost(post.id()));
    }

    @Test
    void testRemovePostNotFound() {
        assertThrows(PostNotFoundException.class, () -> this.postService.removePost("invalid-id"));
    }

    @Test
    void testCreatePostInvalidDate() {
        PostInput input = new PostInput("John Doe", "not-a-date", "First blog post");
        PostCreationException exception = assertThrows(PostCreationException.class,
                () -> this.postService.createPost(input));
        assertTrue(exception.getMessage().contains("Invalid date format"));
    }

    @Test
    void testGetAllPostsSortedByTimestamp() throws InterruptedException {
        PostInput input1 = new PostInput("John Doe", "2025-05-16T10:30:00Z", "First blog post");
        PostInput input2 = new PostInput("Jane Smith", "2025-06-16T10:30:00Z", "Second blog post");

        Post post1 = this.postService.createPost(input1);
        Post post2 = this.postService.createPost(input2);

        List<Post> posts = this.postService.getAllPostsSortedTimestamp();
        assertEquals(2, posts.size());
        assertEquals(post1.id(), posts.get(0).id());
        assertEquals(post2.id(), posts.get(1).id());
    }
}
