package com.dubeaka.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class LogApplication {

	public static void main(String[] args) {
        SpringApplication.run(LogApplication.class, args);
    }
}
