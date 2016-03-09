package at.jku.imdbadapter.app;

import java.util.List;

import at.jku.imdbadapter.model.ombd.Movie;
import at.jku.imdbadapter.rest.ImdbSearchClient;
import at.jku.imdbadapter.rest.ImdbSearchClient.Type;

public class ImdbMainApplication {

    public static void main(String[] args) {
        ImdbSearchClient client = new ImdbSearchClient();

        long startTime = System.currentTimeMillis();
        System.out.println(client.searchProgramsBySender("ORF1", 9, 3));
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        System.out.println(client.searchProgramsByTitle("Two and a Half Men", 9, 3));
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        System.out.println(client.findByImdbId("tt0944947"));
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        List<Movie> movies = client.search("Game of", Type.SERIES);
        System.out.println("Size: " + movies.size() + "\n" + movies);
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));

    }
}
