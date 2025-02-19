package com.techzen.academy_n1224c1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello") // API , End Point
    public String greeting(@RequestParam(defaultValue = "World") String name,
                           @RequestParam(defaultValue = "Da Nang") String address) {
        return "Hello World " + name + " - " + address;
    }
}
