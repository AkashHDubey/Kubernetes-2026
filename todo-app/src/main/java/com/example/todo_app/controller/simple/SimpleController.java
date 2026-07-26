package com.example.todo_app.controller.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Objects;
import java.util.Random;

@Controller
public class SimpleController {

    @Autowired
    RestClient restClient;

    @GetMapping("/")
    public String getSimpleResponse(){
        return "todo";
    }

    @GetMapping("/image")
    public ResponseEntity<byte[]> getImage(){

        Path outputFile = Paths.get("/usr/src/app/files/image.jpeg");
        ByteArrayOutputStream baos;

        //Check and delete if the file is old
        if(Files.exists(outputFile)){
            try {
                BasicFileAttributes attrs = Files.readAttributes(outputFile, BasicFileAttributes.class);
                Instant cutoffTime = Instant.now().minus(10, ChronoUnit.MINUTES);
                if (attrs.creationTime().toInstant().isBefore(cutoffTime)) {
                    Files.delete(outputFile);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //Check if the file exists and then return it from local
        if (Files.exists(outputFile)){
            try {
                BufferedImage bufferedImage = ImageIO.read(outputFile.toFile());
                baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpeg", baos);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(baos.toByteArray());
        }

        //If the file doesn't exist then download it, save it and then return
        Random rand = new Random();
        int random4Digit = rand.nextInt(4000) + 1000;
        byte[] imageBytes =  restClient.get()
                .uri("/"+random4Digit)
                .retrieve()
                .body(byte[].class);

        try (ByteArrayInputStream bais = new ByteArrayInputStream(Objects.requireNonNull(imageBytes))){
            BufferedImage bufferedImage = ImageIO.read(bais);
            boolean success = ImageIO.write(bufferedImage, "jpeg", outputFile.toFile());
            if (!success) {
                System.err.println("No appropriate writer found for the format.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }
}
