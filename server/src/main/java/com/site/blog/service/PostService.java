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

    // TODO: Replace with a DB
    private final Map<String, Post> postMap;

    public PostService() {
        this.postMap = new ConcurrentHashMap<>();
    }

    /**
     * Get post with given id
     *
     * @param id ID of the post
     * @return Post with given ID if it exists
     */
    public Post getPost(String id) {
        Post post = this.postMap.get(id);
        if (post == null) {
            throw new PostNotFoundException();
        }
        return post;
    }

    /**
     * Create a new post with given parameters
     *
     * @param input Post input data
     * @return Newly created post
     */
    public Post createPost(PostInput input) {
        String id = generateId();
        Post post = buildPost(id, input);
        this.postMap.put(id, post);
        return post;
    }

    /**
     * Update post with given parameter
     *
     * @param id    ID of the post
     * @param input Post input data with updated information
     * @return Post with updated data if it exists
     */
    public Post updatePost(String id, PostInput input) {
        Post updatedPost = this.postMap.computeIfPresent(
                id, (key, oldPost) -> buildPost(id, input));
        if (updatedPost == null) {
            throw new PostNotFoundException();
        }
        return updatedPost;
    }

    /**
     * Builds new post with given data
     *
     * @param id    ID for the new post
     * @param input Post input data
     * @return Newly created post
     */
    private Post buildPost(String id, PostInput input) {
        Instant date;
        try {
            date = Instant.parse(input.getDate());
        } catch (DateTimeParseException e) {
            throw new PostCreationException("Invalid date format "
                    + input.getDate() + ". Expected format example: 2025-05-16T10:30:00Z");
        }
        return new Post(id, input.getAuthor(), date, input.getContent());
    }

    /**
     * Removes the post with given id
     *
     * @param id Post Id
     */
    public void removePost(String id) {
        Post post = this.postMap.remove(id);
        if (post == null) {
            throw new PostNotFoundException();
        }
    }

    /**
     * @return List of posts sorted according to ascending timestamps
     */
    public List<Post> getAllPostsSortedTimestamp() {
        return this.postMap.values().stream().sorted(
                Comparator.comparing(Post::date)
        ).toList();
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }
}
