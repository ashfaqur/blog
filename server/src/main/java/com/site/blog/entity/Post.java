package com.site.blog.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

public record Post(
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        String id,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        String author,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        Instant date,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        String content) {
}
