package com.site.blog.service;

import com.site.blog.entity.BlogPost;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BlogPostService {

    private final Map<String, BlogPost> blogPostMap;

    public BlogPostService() {
        this.blogPostMap = new HashMap<>();
    }


}
