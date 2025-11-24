package com.site.blog.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostRestController {

    @GetMapping("/test")
    public Map<String, String> sayHello() {
        return Map.of("message", "Hello World!");
    }
}
