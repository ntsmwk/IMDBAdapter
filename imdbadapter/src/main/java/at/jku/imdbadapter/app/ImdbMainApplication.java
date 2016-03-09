package at.jku.imdbadapter.app;

import java.util.List;

import at.jku.imdbadapter.model.ombd.Movie;
import at.jku.imdbadapter.rest.ImdbSearchClient;

public class ImdbMainApplication {

    public static void main(String[] args) {
        ImdbSearchClient client = new ImdbSearchClient();

        long startTime = System.currentTimeMillis();
        System.out.println(client.searchByTitle("Game of"));
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        System.out.println(client.searchByImdbId("tt0944947"));
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        List<Movie> movies = client.searchMovies("Game");
        System.out.println("Size: " + movies.size() + "\n" + movies);
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        System.out.println(client.searchProgramsBySender("ORF1", 9, 3));
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        System.out.println(client.searchProgramsByTitle("Two and a Half Men", 9, 3));
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
    }
}
