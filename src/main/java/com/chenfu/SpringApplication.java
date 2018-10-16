package com.chenfu;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.Filter;

@SpringBootApplication
public class SpringApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HttpInterceptor httpInterceptor = new HttpInterceptor();
        registry.addInterceptor(httpInterceptor).addPathPatterns("/**");
    }

	@Bean
    public FilterRegistrationBean httpFilter(){
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new HttpFilter());
        registrationBean.addUrlPatterns("/threadlocal/*");
        return registrationBean;
    }
}
