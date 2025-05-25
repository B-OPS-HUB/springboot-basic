package dev.practice.servlet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellobootController {

    @GetMapping("/hello")
    public String hello(String name){
        return "Hello" + name;
    }
}
