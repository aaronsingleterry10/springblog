package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostsRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    private final PostsRepository postsDao;

    public ProfileController(PostsRepository postsDao) {
        this.postsDao = postsDao;
    }

    @GetMapping("/profile")
    public String goToProfile(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(loggedInUser.getUsername());
        List<Post> allPosts = postsDao.findAll();
        List<Post> loggedInUserPosts = new ArrayList<>();
//        for(Post x : allPosts) {
//            if (x.getParentUser().getId() == loggedInUser.getId()) {
//                loggedInUserPosts.add(x);
//            }
//        }
        model.addAttribute("user", loggedInUser);
//        model.addAttribute("posts", loggedInUserPosts);
        return "/users/profile";
    }

//    @PostMapping("/profile")
//    public String returnUsername(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, Model model) {
//        List<String> ads = new ArrayList<>();
//        ads.add("add 1");
//        ads.add("add 2");
//        ads.add("add 3");
//
//        model.addAttribute("username", username);
//        model.addAttribute("password", password);
//        model.addAttribute("ads", ads);
//        return "profile";
//    }
}
