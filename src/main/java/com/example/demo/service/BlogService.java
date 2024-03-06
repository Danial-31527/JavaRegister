package com.example.demo.service;

import com.example.demo.model.Blog;
import com.example.demo.model.User;

import java.util.List;

public interface BlogService {
    List<Blog> listBlogs();
    void saveBlog(Blog blog);
    void deleteBlog(Long id);
    Blog getBlogById(Long id);

    // Добавьте следующую строку для получения блогов по пользователю
    List<Blog> listBlogsByUser(User user);
}
