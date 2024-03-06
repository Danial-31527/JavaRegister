package com.example.demo.service;

import com.example.demo.model.Comment;

public interface CommentService {
    void saveComment(Long blogId, Comment comment);
}
