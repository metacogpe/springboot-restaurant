package com.example.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages")
public class PageController {

    @GetMapping("/main")
    public ModelAndView main(){
        return new ModelAndView("aaa/main");  // templates/aaa 디렉토리 내의 main.html 파일을 의미

    }
}
