package com.example.ghumantu.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer{
	@Override
	public void addCorsMappings(CorsRegistry corsRegistry) {
		corsRegistry.addMapping("/**")
		.allowedMethods("*")
		.allowedOrigins("http://localhost:3000")
		.allowedHeaders("*")
		.maxAge(3600L)
        .allowedHeaders("*")
        .exposedHeaders("Authorization")
        .allowCredentials(true);
	}
}
