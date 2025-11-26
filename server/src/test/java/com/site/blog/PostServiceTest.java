package com.site.blog;

import com.site.blog.entity.Post;
import com.site.blog.input.PostInput;
import com.site.blog.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostServiceTest {

    private PostService postService;

    @BeforeEach
    public void setup() {
        this.postService = new PostService();
    }

    @Test
    void testCreatePostSuccessfully() {
        PostInput input = new PostInput("John Doe", "2025-06-16T10:30:00Z", "Hello World");
        Post post = postService.createPost(input);

        assertNotNull(post.id());
        assertEquals(input.getAuthor(), post.author());
        assertEquals(input.getContent(), post.content());
        assertEquals(Instant.parse(input.getDate()), post.date());
    }
}
