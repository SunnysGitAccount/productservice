package com.scaler.productservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This class will be above multiple methods
// that will be serving HTTP Requests at /hello
// This class will be service Rest (HTTP) APIS
// localhost:8080/hello
@RestController
@RequestMapping("/hello")
public class HelloController {

    // GET /hello/say
    @GetMapping("/say/{name}/{times}")
    public String sayHello(@PathVariable("name") String name,
                           @PathVariable("times") int times) {
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < times; ++i) {
            answer.append("Hello ").append(name);
            answer.append("<br>");
        }

        return answer.toString();
    }
    // Something in curly braces becomes a variables
}
