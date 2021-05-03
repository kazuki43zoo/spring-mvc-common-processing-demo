package com.example.config;

import com.example.component.servlet.CustomFilter;
import com.example.component.servlet.CustomServletRequestListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.filter.ApplicationContextHeaderFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class WebConfig {

  @Bean
  CustomServletRequestListener customServletRequestListener() {
    return new CustomServletRequestListener();
  }

  @Bean
  CustomFilter customFilter() {
    return new CustomFilter();
  }

  @Bean
  FilterRegistrationBean<?> commonsRequestLoggingFilter() {
    FilterRegistrationBean<?> registrationBean = new FilterRegistrationBean<>(new CommonsRequestLoggingFilter());
    registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE); // 優先度を指定。優先度が高い方が先に適用される。
    return registrationBean;
  }

  @Bean
  ApplicationContextHeaderFilter applicationContextHeaderFilter(ApplicationContext context) {
    return new ApplicationContextHeaderFilter(context);
  }

}
