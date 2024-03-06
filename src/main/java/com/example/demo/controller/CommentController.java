package com.example.demo.controller;

import com.example.demo.model.Comment;
import com.example.demo.model.User;
import com.example.demo.service.CommentService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;

    @PostMapping("/add-comment/{blogId}")
    public String addComment(@PathVariable Long blogId, Comment comment, Principal principal) {
        if (principal != null) {
            String firstName = principal.getName();
            User user = userService.findByFirstName(firstName);
            comment.setUser(user);
            commentService.saveComment(blogId, comment);
        }
        return "redirect:/blog/" + blogId;
    }
}
