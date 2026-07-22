package com.dubeaka.log.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class StateController {

    @GetMapping("/get-file-info")
    public String getCurrentTimestamp() {
        String content;
        try {
            content = Files.readString(Paths.get("/usr/src/app/file/timestamp.txt"));
            content = content
                    + "\n" + "Ping / Pongs: "
                    + Files.readString(Paths.get("/usr/src/app/file/ping-pong.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }

}
