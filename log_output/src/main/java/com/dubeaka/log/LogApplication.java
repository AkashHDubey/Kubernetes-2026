package com.dubeaka.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
			System.out.println(currentString);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
	}

}
