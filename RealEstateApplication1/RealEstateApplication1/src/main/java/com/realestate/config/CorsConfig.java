package com.realestate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // ✅ Allow all endpoints
                        .allowedOrigins("http://localhost:3000")  // ✅ Allow React frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // ✅ Allowed HTTP methods
                        .allowedHeaders("*")  // ✅ Allow all headers
                        .allowCredentials(true);  // ✅ Allow cookies & authorization headers
            }
        };
    }
}
