package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/main-page")
    public String mainPage() {
        return "main"; // Вернуть имя шаблона для главной страницы
    }

    @GetMapping("/profil")
    public String profilePage() {
        return "profil"; // Вернуть имя шаблона для страницы профиля
    }

    @GetMapping("/myblog")
    public String myBlogPage() {
        return "myblog"; // Вернуть имя шаблона для страницы "Мой блог"
    }

}
