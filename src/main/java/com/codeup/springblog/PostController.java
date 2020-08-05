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

//    List<Post> listOfPosts = new ArrayList<>();
//    Post post = new Post(1, "Example Title", "No. So tell me, Marty, how long have you been in port? Oh, pleased to meet you, Calvin Marty Klein. Do you mind if I sit here? Now, of course not, Biff, now, I wouldn't want that to happen. Well uh, good, fine.");
//    Post post2 = new Post(2, "Title2", "Nah, I just don't think I'm cut out for music. Marty, you interacted with anybody else today, besides me? Biff. Good, you could start by sweeping the floor. I don't wanna know your name. I don't wanna know anything anything about you.");
//    Post post3 = new Post(3, "Title3", "Oh, uh, hey you, get your damn hands off her. Do you really think I oughta swear? Uh, I think so. Why is she gonna get angry with you? Yeah. Sam, quit fiddling with that thing and come in here and eat your dinner.");

    @GetMapping("/posts")
    public String getPosts(Model model) {
//        listOfPosts.add(post);
//        listOfPosts.add(post2);
//        listOfPosts.add(post3);
        model.addAttribute("posts", postsDao.findAll());
        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getIndividualPost(@PathVariable long id, Model model) {
//        listOfPosts.add(post);
//        listOfPosts.add(post2);
//        listOfPosts.add(post3);
//        for(Post x : listOfPosts) {
//            if(x.getId() == id) {
//                model.addAttribute("title", "Title: " + x.getTitle());
//                model.addAttribute("body", x.getBody());
//                break;
//            } else {
//                model.addAttribute("title", "When a post has been selected, it will be displayed here.");
//            }
//        }
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
        model.addAttribute("postId", id);
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
}
