package com.codeup.springblog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post, Long> {
    Post findById(long id);
}
