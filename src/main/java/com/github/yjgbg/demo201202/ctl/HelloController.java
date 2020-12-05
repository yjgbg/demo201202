package com.github.yjgbg.demo201202.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private static int x = 0;
    @GetMapping("hello")
    public String hello() {
        System.out.println("hello,world");
        return "hello,world"+x++;
    }
}
