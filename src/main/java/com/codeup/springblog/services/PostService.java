package com.codeup.springblog.services;

import com.codeup.springblog.repositories.PostsRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private UserRepository usersDao;
    private PostsRepository postsDao;

    public PostService(UserRepository usersDao, PostsRepository postsDao) {
        this.usersDao = usersDao;
        this.postsDao = postsDao;
    }

}
