package com.example.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@ComponentScan("com.example.component.servlet")
@ComponentScan("com.example.component.service")
@ComponentScan("com.example.component.aspect")
@EnableAspectJAutoProxy // 追加
public class AppConfig {
  @Bean
  MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.addBasenames("messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }
}
