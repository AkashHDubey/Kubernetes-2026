package com.dubeaka.log.controller;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@RestController
public class StateController {

    @GetMapping("/")
    public String getCurrentTimestamp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        String targetUrl = "http://ping-pong-svc:2345/pings";
        Request request = new Request.Builder()
                .url(targetUrl)
                .get()
                .addHeader("Accept", "application/json")
                .build();

        String pings = "";
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                pings = response.body().string();
            } else {
                System.err.println("Request failed with code: " + response.code());
            }
        } catch (IOException e) {
            System.err.println("Network error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        String content;
        try {
            content = Files.readString(Paths.get("/usr/src/app/file/timestamp.txt"));
            content = content
                    + "\n" + "Ping / Pongs: "
                    + pings;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }

}
