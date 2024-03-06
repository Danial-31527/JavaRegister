package com.example.demo.service;

import com.example.demo.model.Blog;
import com.example.demo.model.Comment;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    @Override
    public void saveComment(Long blogId, Comment comment) {
        Blog blog = blogRepository.findById(blogId).orElseThrow();
        comment.setBlog(blog);
        commentRepository.save(comment);
    }
}
