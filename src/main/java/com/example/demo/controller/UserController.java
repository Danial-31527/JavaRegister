package com.example.demo.controller;

import com.example.demo.model.Blog;
import com.example.demo.model.User;
import com.example.demo.service.BlogService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final BlogService blogService;

    @GetMapping("/user-main-page")
    public String userMainPage(Model model, Principal principal) {
        if (principal != null) {
            String firstName = principal.getName();
            User user = userService.findByFirstName(firstName);
            model.addAttribute("user", user);
            List<Blog> userBlogs = blogService.listBlogsByUser(user);
            model.addAttribute("userBlogs", userBlogs);
        }
        return "blog/main-page";
    }
}
