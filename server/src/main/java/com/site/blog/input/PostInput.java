package com.site.blog.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostInput {

    @NotBlank(message = "Author is mandatory")
    @Size(max = 200, message = "Author name cannot exceed 200 characters")
    @Schema(type = "string", example = "Jane Doe")
    private final String author;

    @NotBlank(message = "Date is mandatory")
    @Schema(type = "string", format = "date-time",
            example = "2025-05-16T10:30:00Z"
    )
    private final String date;

    @NotBlank(message = "Content is mandatory")
    @Size(max = 10000, message = "Content cannot exceed 10000 characters")
    @Schema(type = "string", example = "This is a post content.")
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


