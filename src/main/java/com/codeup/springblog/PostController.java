package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String getPosts() {
        return "This is where posts will be posted";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String getIndividualPost(@PathVariable int id) {
        return "This is where an individual post with an id of " + id + " will be.";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "This is where posts are created";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String newPost() {
        return "This is a new post create";
    }
}
