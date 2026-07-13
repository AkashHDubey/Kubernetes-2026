package com.dubeaka.ping_pong.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    public StringBuilder o = new StringBuilder();

    @GetMapping("/ping-pong")
    public String countPing(){
        o.append("o");
        return "pong " + o;
    }

}
