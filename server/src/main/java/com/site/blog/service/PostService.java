package com.site.blog.service;

import com.site.blog.entity.Post;
import com.site.blog.error.PostCreationException;
import com.site.blog.error.PostNotFoundException;
import com.site.blog.input.PostInput;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class PostService {

    private static final int AUTHOR_CHAR_LIMIT = 200;
    private static final int CONTENT_CHAR_LIMIT = 10000;

    private final Map<String, Post> blogPostMap;

    public PostService() {
        this.blogPostMap = new HashMap<>();
        generateSampleData();
    }

    public Post getPost(String id) {
        Post post = this.blogPostMap.get(id);
        if (post == null) {
            throw new PostNotFoundException();
        }
        return post;
    }

    public Post createPost(PostInput input) {
        String id = generateId();
        Post post = createPost(id, input);
        this.blogPostMap.put(id, post);
        return post;
    }

    public Post updatePost(String id, PostInput input) {
        if (this.blogPostMap.containsKey(id)) {
            Post newPost = createPost(id, input);
            this.blogPostMap.put(id, newPost);
            return newPost;
        } else {
            throw new PostNotFoundException();
        }
    }

    private Post createPost(String id, PostInput input) {
        Instant date;
        try {
            date = Instant.parse(input.getDate());
        } catch (NumberFormatException e) {
            throw new PostCreationException("Invalid date format " + input.getDate() + ". Expected format example: 2025-05-16T10:30:00Z");
        }
        return new Post(id, input.getAuthor(), date, input.getContent());
    }

    public boolean containsPost(String id) {
        return this.blogPostMap.containsKey(id);
    }

    public void removePost(String id) {
        if (this.blogPostMap.containsKey(id)) {
            this.blogPostMap.remove(id);
        } else {
            throw new PostNotFoundException();
        }
    }

    public List<Post> getAllPostsSortedTimestamp() {
        return this.blogPostMap.values().stream().sorted(
                Comparator.comparing(Post::date)
        ).toList();
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    private void generateSampleData() {
        Post post1 = new Post("123", "Jane Doe", Instant.parse("2025-05-16T10:30:00Z"), "First post content.");
        Post post2 = new Post("124", "John Smith", Instant.parse("2025-05-16T11:00:00Z"), "Second post content.");
        this.blogPostMap.put(post1.id(), post1);
        this.blogPostMap.put(post2.id(), post2);
    }
}
