package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getIndividualPost(@PathVariable int id, Model model) {
        Post post = new Post(1, "Example Title", "No. So tell me, Marty, how long have you been in port? Oh, pleased to meet you, Calvin Marty Klein. Do you mind if I sit here? Now, of course not, Biff, now, I wouldn't want that to happen. Well uh, good, fine.");
        String postTitle = post.getTitle();
        String postBody = post.getBody();
//        model.addAttribute("id", id);
        if (id == post.getId()) {
            model.addAttribute("title","Title: " + postTitle);
            model.addAttribute("body", postBody);
        } else {
            model.addAttribute("title", "When a post has been selected, it will be displayed here.");
        }

        return "/posts/show";
    }

//    @GetMapping("/posts/create")
//    @ResponseBody
//    public String createPost() {
//        return "This is where posts are created";
//    }

//    @PostMapping("/posts/create")
//    @ResponseBody
//    public String newPost() {
//        return "This is a new post create";
//    }
}
