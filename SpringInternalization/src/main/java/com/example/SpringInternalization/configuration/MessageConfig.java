package com.example.SpringInternalization.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Configuration
public class MessageConfig {
    //for resolving locale
//    @Bean
//    public LocaleResolver localeResolver() {
////        SessionLocaleResolver slr = new SessionLocaleResolver();
//        CookieLocaleResolver slr=new CookieLocaleResolver();
//        slr.setDefaultLocale(Locale.US);
////        slr.setLocaleAttributeName("session.current.locale");
//        return slr;
//    }

    //for intercepting locale
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor(){
//        LocaleChangeInterceptor lci=new LocaleChangeInterceptor();
//        lci.setParamName("language");
//        return lci;
//    }

    //for retrieving message
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("language/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    //registering filter
//    @Bean
//    public FilterRegistrationBean<LocaleFilter> filterRegistrationBean(LocaleFilter filter) {
//        FilterRegistrationBean < LocaleFilter > registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(filter);
//        registrationBean.addUrlPatterns("/country","/home");
//        registrationBean.setOrder(1); //set precedence
//        return registrationBean;
//    }
}
