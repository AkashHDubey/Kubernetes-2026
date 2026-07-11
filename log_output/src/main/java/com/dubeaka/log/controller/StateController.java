package com.dubeaka.log.controller;

import com.dubeaka.log.LogApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StateController {

    @GetMapping("/get-current-timestamp")
    public String getCurrentTimestamp(){
        return LogApplication.currentString;
    }

}
