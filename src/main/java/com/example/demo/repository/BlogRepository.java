package com.example.demo.repository;

import com.example.demo.model.Blog;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    Optional<Blog> findByTitle(String title);

    List<Blog> findByUser(User user);
}