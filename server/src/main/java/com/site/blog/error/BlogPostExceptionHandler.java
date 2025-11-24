package com.site.blog.error;

import com.site.blog.response.BlogPostErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BlogPostExceptionHandler {

    @ExceptionHandler(BlogPostNotFoundException.class)
    public ResponseEntity<BlogPostErrorResponse> handleException(BlogPostNotFoundException exception) {
        BlogPostErrorResponse errorResponse = new BlogPostErrorResponse("Post not found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BlogPostErrorResponse> handleException(Exception exception) {
        BlogPostErrorResponse errorResponse = new BlogPostErrorResponse("Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
