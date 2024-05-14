package com.example.SpringInternalization.controller;

import com.example.SpringInternalization.helper.UtilMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class MessageController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/changeLanguage")
    public ResponseEntity<?> changeLanguage(@RequestParam("lang")String updatedLanguage){
        UtilMap.langMap.put("lang",updatedLanguage);
//        Locale locale = LocaleContextHolder.getLocale();
        Locale.setDefault(Locale.forLanguageTag(updatedLanguage));
        return ResponseEntity.ok().body("You Select language: "+updatedLanguage);
    }
    @GetMapping("/country")
    public ResponseEntity<?> country(Locale locale,@RequestParam String key){
        key=key.replace(" ","_");
        System.out.println(messageSource.getMessage(key,null,locale));
        return ResponseEntity.ok().body(messageSource.getMessage(key,null,Locale.forLanguageTag(UtilMap.langMap.get("lang"))));
    }
    @GetMapping("/home")
    public ResponseEntity<?> home(Locale locale){
        return ResponseEntity.ok().body(messageSource.getMessage("welcome",null,Locale.forLanguageTag(UtilMap.langMap.get("lang"))));
    }
    @GetMapping("/login")
    public ResponseEntity<?> login(Locale locale){
        return ResponseEntity.ok().body(messageSource.getMessage("Chennai Super Kings v Delhi Capitals",null, Locale.forLanguageTag(UtilMap.langMap.get("lang"))));
    }
}
