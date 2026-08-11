package com.example.todo_app.controller.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/backend")
public class SimpleController {

    @PostMapping("/todos")
    public ResponseEntity<String> getSimpleResponse(@RequestBody Map<String, Object> payload){

        Path outputFile = Paths.get("/usr/src/app/files/todo.txt");
        try {
            Files.write(outputFile, Collections.singletonList((String) payload.get("messageText")),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok((String) payload.get("messageText"));
    }

    @GetMapping("/todos")
    public List<String> getTodos(){

        List<String> items = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get("/usr/src/app/files/todo.txt"))) {
            lines.forEach(items::add);
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return items;
    }
}
