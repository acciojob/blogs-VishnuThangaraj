package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        // Get the Blog
        Optional<Blog> blogOptional = blogRepository2.findById(blogId);
        if(blogOptional.isPresent()){
            Blog blog = blogOptional.get();
            image.setBlog(blog);
            blog.getImageList().add(image); // add image to the blog
            blogRepository2.save(blog);
        }

        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Optional<Image> image = imageRepository2.findById(id);
        int count = 0;
        if(image.isPresent()){
            Image image1 = image.get();

            String dimensions = image1.getDimensions();
            int d = (dimensions.charAt(0) - '0') * (dimensions.charAt(dimensions.length()-1) - '0');
            int max = (screenDimensions.charAt(0) - '0') * (screenDimensions.charAt(screenDimensions.length()-1) - '0');

            while(max >= d){
                max -= d;
                count++;
            }
        }
        return count;
    }
}
