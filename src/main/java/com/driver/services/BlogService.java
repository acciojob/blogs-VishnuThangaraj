package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    @Autowired
    ImageService imageService;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setPubDate(new Date());

        //create a blog at the current time
        Optional<User> users = userRepository1.findById(userId);
        if(users.isPresent()){
            User user = users.get();
            blog.setUser(user);
            user.getBlogList().add(blog); // add blog to the userList
            userRepository1.save(user);
        }

        blogRepository1.save(blog); // Save Blog to the Database

        return blog;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        blogRepository1.deleteById(blogId);
    }
}
