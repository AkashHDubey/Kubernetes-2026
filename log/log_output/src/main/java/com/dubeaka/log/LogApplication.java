package com.dubeaka.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class LogApplication {

    public static String currentString = "";

	public static void main(String[] args) {
		SpringApplication.run(LogApplication.class, args);
		String randomString = UUID.randomUUID().toString();
		while (true){
            currentString = Instant.now()+" "+randomString;
            try (BufferedWriter writer = Files.newBufferedWriter(
                    Paths.get("/usr/src/app/file/timestamp.txt"),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND)) {

                writer.write(currentString+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
	}

}
