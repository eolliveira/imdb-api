package com.example.imdbapi;

import com.example.imdbapi.entities.Movie;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ImdbApiApplication {

    private static final String api_key = "k_t9woiodr";
    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(ImdbApiApplication.class, args);

        // HTTP REQUEST
        HttpRequest request = HttpRequest
                .newBuilder()
                .GET()
                .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + api_key))
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
        //System.out.println(response.body());

        /*
        //executa request de maneira assincrona
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
        */


        //////////////////////Desafio 2 -#7DaysOfCode - Java 2/7:

        String retornoApi = response.body();

        //recorta a lista de objetos json
        String jsonFilmes = retornoApi.substring(retornoApi.indexOf("["), retornoApi.lastIndexOf("]") + 1);

        //LISTA DE FILMES
        System.out.println(jsonFilmes);

        //ObjJava -> JSON = gson.toJson
        //JSON -> ObjJava = gson.fromJson

        //declara instancia de gson
        Gson gson = new Gson();

        //transforma o json list em um array de Movie
        Movie[] arrayMovies = gson.fromJson(jsonFilmes, Movie[].class);

        //Transforma de Array para lista
        List<Movie> listMOvie = Arrays.asList(arrayMovies);

        //lista contendo todos os titulos dos filmes
        List<String> titles = new ArrayList<>();

        //lista contendo todos oa urlImages dos filmes
        List<String> urlImages = new ArrayList<>();

        for (Movie m: listMOvie){
            titles.add(m.getTitle());
            urlImages.add(m.getImage());
        }

        //imprime listas de atributos
        titles.forEach((x) -> System.out.println(x));
        urlImages.forEach((x) -> System.out.println(x));

    }
}
