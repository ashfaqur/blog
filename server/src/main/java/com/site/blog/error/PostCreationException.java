package com.site.blog.error;

public class PostCreationException extends RuntimeException {

    public PostCreationException(String message) {
        super(message);
    }
}
