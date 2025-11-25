package com.site.blog.entity;

import java.time.Instant;

public record Post(String id, String author, Instant date, String content) {
}
