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

    public List<BlogPost> getAllPosts() {
        return this.blogPostMap.values().stream().sorted(
                Comparator.comparing(blogPost -> Instant.parse(blogPost.date()))
        ).toList();
    }

    private void generateSampleData() {
        BlogPost post1 = new BlogPost("123", "Jane Doe", "2025-05-16T10:30:00Z", "First post content.");
        BlogPost post2 = new BlogPost("124", "John Smith", "2025-05-16T11:00:00Z", "Second post content.");
        this.blogPostMap.put(post1.id(), post1);
        this.blogPostMap.put(post2.id(), post2);
    }


}
