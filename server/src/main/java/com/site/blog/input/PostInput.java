package com.site.blog.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostInput {

    @NotBlank(message = "Author is mandatory")
    @Size(max = 200, message = "Author name cannot exceed 200 characters")
    private final String author;

    @NotBlank(message = "Date is mandatory")
    private final String date;

    @NotBlank(message = "Content is mandatory")
    @Size(max = 10000, message = "Content cannot exceed 10000 characters")
    private final String content;

    public PostInput(String author, String date, String content) {
        this.author = author;
        this.date = date;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }
}


