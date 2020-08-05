package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class PostController {

    private final PostsRepository postsDao;

    private final UserRepository usersDao;

    public PostController(PostsRepository postsDao, UserRepository usersDao) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
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
    public String createPost() {
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String newPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body) {
        Random rand = new Random();
        int id = rand.nextInt(4) + 1;
        long newId = id;
        User user = usersDao.findById(newId);
        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setBody(body);
        newPost.setParentUser(user);
        postsDao.save(newPost);
        return "redirect:/posts";
    }

    @GetMapping("/posts/update/{id}")
    public String toUpdatePost(@PathVariable long id, Model model) {
        Post updatePost = postsDao.getOne(id);
        model.addAttribute("post", updatePost);
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

    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id) {
        Post deletePost = postsDao.findById(id);
        postsDao.delete(deletePost);
        return "redirect:/posts";
    }
}
