package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostsRepository postsDao;

    public PostController(PostsRepository postsDao) {
        this.postsDao = postsDao;
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {

        model.addAttribute("posts", postsDao.findAll());
        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getIndividualPost(@PathVariable long id, Model model) {
        model.addAttribute("postId", id);
//        refactor code below to pass in the object to the view instead of just "title" and "body"
        model.addAttribute("title", postsDao.findById(id).getTitle());
        model.addAttribute("body", postsDao.findById(id).getBody());

        return "/posts/show";
    }

    @GetMapping("/posts/create")
    public String createPost() {
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String newPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body) {
        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setBody(body);
        postsDao.save(newPost);
        return "redirect:/posts";
    }

    @GetMapping("/posts/update/{id}")
    public String toUpdatePost(@PathVariable long id, Model model) {
//        refactor code to use .getOne method to send over the object instead of individual properties
        model.addAttribute("postId", id);
        model.addAttribute("title", postsDao.findById(id).getTitle());
        model.addAttribute("body", postsDao.findById(id).getBody());
        return "/posts/update";
    }

    @PostMapping("/posts/update/{id}")
    public String updatePost(@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body, Model model) {
        Post updatePost = postsDao.findById(id);
        updatePost.setTitle(title);
        updatePost.setBody(body);
        postsDao.save(updatePost);
        return "redirect:/posts";
    }

    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id) {
        Post deletePost = postsDao.findById(id);
        postsDao.delete(deletePost);
        return "redirect:/posts";
    }
}
