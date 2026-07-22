package com.dubeaka.ping_pong.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@RestController
public class PingController {

    public StringBuilder o = new StringBuilder();

    @GetMapping("/ping-pong")
    public String countPing(){

        o.append("o");
        try (BufferedWriter writer = Files.newBufferedWriter(
                Paths.get("/usr/src/app/file/ping-pong.txt"), StandardOpenOption.CREATE)) {
            writer.write(String.valueOf(o.length()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "pong " + o;
    }

}
