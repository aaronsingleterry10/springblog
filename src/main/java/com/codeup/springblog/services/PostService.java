package com.codeup.springblog.services;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostsRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private UserRepository usersDao;
    private PostsRepository postsDao;

    public PostService(UserRepository usersDao, PostsRepository postsDao) {
        this.usersDao = usersDao;
        this.postsDao = postsDao;
    }

    public List<Post> returnPosts() {
        List<Post> posts = postsDao.findAll();
        return posts;
    }

    public Post returnIndividualPost(long id) {
        Post post = postsDao.getOne(id);
        return post;
    }

    public void createNewPost(Post post, long id) {
       User user = usersDao.getOne(id);
       post.setParentUser(user);
       postsDao.save(post);
    }
}
