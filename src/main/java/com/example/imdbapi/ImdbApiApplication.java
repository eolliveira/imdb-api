package com.example.imdbapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@SpringBootApplication
public class ImdbApiApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(ImdbApiApplication.class, args);

        // HTTP REQUEST
        HttpRequest request = HttpRequest
                .newBuilder()
                .GET()
                .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/api_key"))
                .headers("Accept", "aplication/json")
                .timeout(Duration.ofSeconds(3))
                .build();

        // HTTP CLIENT
        HttpClient client = HttpClient
                .newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                //http -> https...
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        //HTTP RESPONSE

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        /*
        //executa request de maneira assincrona
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
        */
    }
}
