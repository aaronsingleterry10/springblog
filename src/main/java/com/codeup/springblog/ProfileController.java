package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String goToProfile() {
        return "profile";
    }

    @PostMapping("/profile")
    public String returnUsername(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, Model model) {
        List<String> ads = new ArrayList<>();
        ads.add("add 1");
        ads.add("add 2");
        ads.add("add 3");

        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("ads", ads);
        return "profile";
    }
}
