package com.github.yjgbg.demo201202.ctl;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static int x = 0;

    @SneakyThrows
    @GetMapping("hello")
    public String hello() {
        Thread.sleep(200);
        if (x%100 == 0) System.out.printf("访问了%s次数\n",x);
        return "hello,world"+x++;
    }
}
