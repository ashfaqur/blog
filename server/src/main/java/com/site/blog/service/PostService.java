package com.site.blog.service;

import com.site.blog.entity.Post;
import com.site.blog.error.PostCreationException;
import com.site.blog.error.PostNotFoundException;
import com.site.blog.input.PostInput;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PostService {

    private final Map<String, Post> postMap;

    public PostService() {
        this.postMap = new ConcurrentHashMap<>();
        generateSampleData();
    }

    public Post getPost(String id) {
        Post post = this.postMap.get(id);
        if (post == null) {
            throw new PostNotFoundException();
        }
        return post;
    }

    public Post createPost(PostInput input) {
        String id = generateId();
        Post post = createPost(id, input);
        this.postMap.put(id, post);
        return post;
    }

    public Post updatePost(String id, PostInput input) {
        if (this.postMap.containsKey(id)) {
            Post newPost = createPost(id, input);
            this.postMap.put(id, newPost);
            return newPost;
        } else {
            throw new PostNotFoundException();
        }
    }

    private Post createPost(String id, PostInput input) {
        Instant date;
        try {
            date = Instant.parse(input.getDate());
        } catch (DateTimeParseException e) {
            throw new PostCreationException("Invalid date format " + input.getDate() + ". Expected format example: 2025-05-16T10:30:00Z");
        }
        return new Post(id, input.getAuthor(), date, input.getContent());
    }

    public boolean containsPost(String id) {
        return this.postMap.containsKey(id);
    }

    public void removePost(String id) {
        if (this.postMap.containsKey(id)) {
            this.postMap.remove(id);
        } else {
            throw new PostNotFoundException();
        }
    }

    public List<Post> getAllPostsSortedTimestamp() {
        return this.postMap.values().stream().sorted(
                Comparator.comparing(Post::date)
        ).toList();
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    private void generateSampleData() {
        Post post1 = new Post("123", "Jane Doe", Instant.parse("2025-05-16T10:30:00Z"), "First post content.");
        Post post2 = new Post("124", "John Smith", Instant.parse("2025-05-16T11:00:00Z"), "Second post content.");
        this.postMap.put(post1.id(), post1);
        this.postMap.put(post2.id(), post2);
    }
}
