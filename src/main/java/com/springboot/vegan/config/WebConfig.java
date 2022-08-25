package com.springboot.vegan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${veganapp.path.images}")
    private String pathImages;

    public void addResourceHandlers(ResourceHandlerRegistry registry) {


        registry.addResourceHandler("/logos/**").addResourceLocations("file:" + pathImages);
    }
}
