package com.example.todo_app.controller.simple;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("/")
    public String getSimpleResponse(){
      return "I am a simple response.";
    }
}
