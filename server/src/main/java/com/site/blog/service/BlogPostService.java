package com.site.blog.service;

import com.site.blog.entity.BlogPost;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogPostService {

    private final Map<String, BlogPost> blogPostMap;

    public BlogPostService() {
        this.blogPostMap = new HashMap<>();
        generateSampleData();
    }

    public boolean containsPost(String id) {
        return this.blogPostMap.containsKey(id);
    }

    public void removePost(String id) {
        this.blogPostMap.remove(id);
    }

    public void removeAllPosts() {
        this.blogPostMap.clear();
    }

    public List<BlogPost> getAllPostsSortedTimestamp() {
        return this.blogPostMap.values().stream().sorted(
                Comparator.comparing(BlogPost::date)
        ).toList();
    }

    private void generateSampleData() {
        BlogPost post1 = new BlogPost("123", "Jane Doe", Instant.parse("2025-05-16T10:30:00Z"), "First post content.");
        BlogPost post2 = new BlogPost("124", "John Smith", Instant.parse("2025-05-16T11:00:00Z"), "Second post content.");
        this.blogPostMap.put(post1.id(), post1);
        this.blogPostMap.put(post2.id(), post2);
    }


}
