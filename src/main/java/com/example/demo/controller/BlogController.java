package com.example.demo.controller;

import com.example.demo.model.Blog;
import com.example.demo.model.Comment;
import com.example.demo.model.User;
import com.example.demo.service.BlogService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;
    private final UserService userService;

    @GetMapping("/")
    public String blogs(Model model) {
        model.addAttribute("blogs", blogService.listBlogs());
        return "blogs";
    }

    @GetMapping("/blog/{id}")
    public String blogInfo(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getBlogById(id));
        return "blog-info";
    }

    @PostMapping("/blog/delete/{id}")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/";
    }

    @GetMapping("/create-blog")
    public String showCreateBlogForm(Model model, Principal principal) {
        if (principal != null) {
            String firstName = principal.getName();
            User user = userService.findByFirstName(firstName);

            if (user != null) {
                model.addAttribute("author", user.getFirstName() + " " + user.getLastName());
            } else {
                model.addAttribute("author", "Unknown Author");
            }
        } else {
            model.addAttribute("author", "Unknown Author");
        }

        return "create-blog";
    }

    @PostMapping("/create-blog")
    public String createBlog(Blog blog, Principal principal) {
        if (principal != null) {
            String firstName = principal.getName();
            User user = userService.findByFirstName(firstName);

            if (user != null) {
                blog.setUser(user);

                // Создайте комментарии и установите блог в комментарии
                Comment comment1 = new Comment();
                comment1.setUser(user);
                comment1.setContent("Sample comment 1");
                comment1.setBlog(blog);

                Comment comment2 = new Comment();
                comment2.setUser(user);
                comment2.setContent("Sample comment 2");
                comment2.setBlog(blog);

                blog.setComments(Arrays.asList(comment1, comment2));

                blogService.saveBlog(blog);
            } else {
                blogService.saveBlog(blog);
                // Можно добавить логику обработки ошибки или просто не сохранять блог
            }
        } else {
            // Обработка сценария, когда Principal отсутствует
            blogService.saveBlog(blog);
        }

        return "redirect:/main-page";
    }


    @GetMapping("/blog/update/{id}")
    public String updateBlogForm(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        return "update-blog";
    }

    @PostMapping("/blog/update")
    public String updateBlog(@ModelAttribute Blog blog) {
        if (blog.getId() != null) {
            blogService.saveBlog(blog);
        }
        return "redirect:/";
    }
}
