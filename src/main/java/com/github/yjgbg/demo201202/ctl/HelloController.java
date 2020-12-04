package com.github.yjgbg.demo201202.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("hello")
    public String hello() {
        System.out.println("hello,world");
        return "hello,world";
    }
}
