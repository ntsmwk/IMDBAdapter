package at.jku.imdbadapter.app;

import at.jku.imdbadapter.rest.ImdbSearchClient;

public class ImdbMainApplication {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ImdbSearchClient client = new ImdbSearchClient();
        System.out.println(client.searchMovieByTitle("Game"));
        System.out.println(client.searchMovieByImdbID("tt0944947"));
        System.out.println(client.searchMovies("Game"));
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
    }
}
