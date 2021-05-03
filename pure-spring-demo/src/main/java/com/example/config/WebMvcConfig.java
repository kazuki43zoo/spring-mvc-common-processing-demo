package com.example.config;

import com.example.component.controller.CustomHandlerInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("com.example.component.controller")
public class WebMvcConfig implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new CustomHandlerInterceptor())
        .addPathPatterns("/**") // 適用対象のパス(パターン)を指定する
        .excludePathPatterns("/static/**"); // 除外するパス(パターン)を指定する
  }
}
