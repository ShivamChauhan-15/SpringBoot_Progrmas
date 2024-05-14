//package com.example.SpringInternalization.filter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.context.i18n.LocaleContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.servlet.LocaleResolver;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Locale;
//
//@Component
//public class LocaleFilter extends OncePerRequestFilter {
//    @Autowired
//    private  LocaleResolver localeResolver;
//    @Autowired
//    MessageSource messageSource;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        filterChain.doFilter(request, response);
//        String language = request.getParameter("language");
//        if (language != null && !language.isEmpty()) {
//            Locale locale = new Locale(language);
//            localeResolver.setLocale(request, response, locale);
//        }
//
//    }
//
//
//}
