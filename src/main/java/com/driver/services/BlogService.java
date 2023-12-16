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
        //create a blog at the current time
        Optional<User> users = userRepository1.findById(userId);
        User user = users.get();

        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setUser(user);

        blogRepository1.save(blog); // Save Blog to the Database

        user.getBlogList().add(blog); // add blog to the userList

        return blog;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        Optional <Blog> blogOptional = blogRepository1.findById(blogId);
        Blog blog = blogOptional.get();

        blog.getImageList().forEach(images -> imageService.deleteImage(images.getId()));

        blogRepository1.deleteById(blogId); // Delete the Blog
    }
}
