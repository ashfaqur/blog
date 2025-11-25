package com.site.blog.response;

import com.site.blog.entity.Post;

public record PostResponse(String description, Post post) {
}
