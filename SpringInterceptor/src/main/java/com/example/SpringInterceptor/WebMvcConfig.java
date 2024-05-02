package com.example.SpringInterceptor;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/login");  // Add the specific URL pattern here
    }

}

//this to apply filter on specific url
//@Configuration
//public class WebMvcConfig {
//    @Bean
//    public FilterRegistrationBean < MyFilter > filterRegistrationBean() {
//        FilterRegistrationBean < MyFilter > registrationBean = new FilterRegistrationBean();
//        MyFilter filter = new MyFilter();
//
//        registrationBean.setFilter(filter);
//        registrationBean.addUrlPatterns("/login");
////        registrationBean.setOrder(1); //set precedence
//        return registrationBean;
//    }
//}

