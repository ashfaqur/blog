package com.site.blog.response;

import com.site.blog.entity.Post;

import java.util.List;

public record PostListResponse(String description, List<Post> posts) {
}
