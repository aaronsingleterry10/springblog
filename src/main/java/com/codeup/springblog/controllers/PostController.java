package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Image;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.ImagesRepository;
import com.codeup.springblog.repositories.PostsRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import com.codeup.springblog.services.PostService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class PostController {

    private final PostsRepository postsDao;
    private final UserRepository usersDao;
    private final EmailService emailService;
    private final PostService postService;
    private final ImagesRepository imagesDao;

    public PostController(PostsRepository postsDao, UserRepository usersDao, EmailService emailService, PostService postService, ImagesRepository imagesDao) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
        this.postService = postService;
        this.imagesDao = imagesDao;
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
//        List<Post> allPosts = postsDao.findAll();
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("posts", postService.returnPosts());
        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getIndividualPost(@PathVariable long id, Model model) {
        List<Image> images = postsDao.getOne(id).getImages();
        List<Image> photos = new ArrayList<>();
        List<Image> videos = new ArrayList<>();
        for (Image x : images) {
            System.out.println(x.getFileType());
            if (x.getFileType().equalsIgnoreCase("video/mp4")) {
                videos.add(x);
            } else if (x.getFileType().equalsIgnoreCase("image/jpeg")) {
                photos.add(x);
            }
        }
        model.addAttribute("post", postService.returnIndividualPost(id));
        model.addAttribute("videos", videos);
        model.addAttribute("photos", photos);
        return "/posts/show";
    }

    @GetMapping("/posts/create")
    public String createPost(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("post", new Post());
//        model.addAttribute("user", loggedInUser);
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String newPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, Model model, @RequestParam(name = "images") String[] images, @RequestParam(name = "file-type") String[] fileType) {
        if (postsDao.findByTitle(title) != null) {
            boolean invalidPost = true;
            model.addAttribute("invalidPost", invalidPost);
            return "/posts/create";
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = new Post(title, body, user);
        List<String> newImages = Arrays.asList(images);
        List<String> fileTypes = Arrays.asList(fileType);
        post.setParentUser(user);
        postsDao.save(post);
        for (int i = 0; i < newImages.size(); i++) {
            Image newImage = new Image();
            newImage.setParentPost(post);
            newImage.setImageUrl(newImages.get(i));
            newImage.setFileType(fileTypes.get(i));
            imagesDao.save(newImage);
        }
//        emailService.prepareAndSend(post, "New Post Alert", "Hello, this is just a message alerting you that you have created a new post!");
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
