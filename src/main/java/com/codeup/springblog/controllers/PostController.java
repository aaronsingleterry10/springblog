package com.codeup.springblog.controllers;

import com.codeup.springblog.*;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Controller
public class PostController {

    private final PostsRepository postsDao;
    private final UserRepository usersDao;
    private final EmailService emailService;

    public PostController(PostsRepository postsDao, UserRepository usersDao, EmailService emailService) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        List<Post> allPosts = postsDao.findAll();
        model.addAttribute("posts", allPosts);
        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getIndividualPost(@PathVariable long id, Model model) {
        Post singlePost = postsDao.getOne(id);
        model.addAttribute("post", singlePost);
        return "/posts/show";
    }

    @GetMapping("/posts/create")
    public String createPost(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String newPost(@ModelAttribute Post post) {
        Random rand = new Random();
        int id = rand.nextInt(4) + 1;
        long newId = id;
        User user = usersDao.findById(newId);
        post.setParentUser(user);
        postsDao.save(post);
        emailService.prepareAndSend(post, "New Post Alert", "Hello, this is just a message alerting you that you have created a new post!");
        return "redirect:/posts";
    }
//    public String newPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body) {
//        Random rand = new Random();
//        int id = rand.nextInt(4) + 1;
//        long newId = id;
//        User user = usersDao.findById(newId);
//        Post newPost = new Post();
//        newPost.setTitle(title);
//        newPost.setBody(body);
//        newPost.setParentUser(user);
//        postsDao.save(newPost);
//        return "redirect:/posts";
//    }

    @GetMapping("/posts/{id}/edit")
    public String toUpdatePost(@PathVariable long id, Model model) {
        Post updatePost = postsDao.getOne(id);
        model.addAttribute("post", updatePost);
        return "/posts/update";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Post post) {
        User user = postsDao.getOne(id).getParentUser();
        post.setParentUser(user);
        postsDao.save(post);
        return "redirect:/posts";
    }

//    @PostMapping("/posts/{id}/edit")
//    public String updatePost(@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body) {
//        Post updatePost = postsDao.findById(id);
//        updatePost.setTitle(title);
//        updatePost.setBody(body);
//        postsDao.save(updatePost);
//        return "redirect:/posts";
//    }

    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id) {
        Post deletePost = postsDao.findById(id);
        postsDao.delete(deletePost);
        return "redirect:/posts";
    }
}
