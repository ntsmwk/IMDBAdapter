package at.jku.imdbadapter.app;

import java.util.List;

import at.jku.imdbadapter.model.ombd.Movie;
import at.jku.imdbadapter.model.tvMedia.TvMediaEntry;
import at.jku.imdbadapter.rest.ImdbSearchClient;

public class ImdbMainApplication {

    public static void main(String[] args) {
        ImdbSearchClient client = new ImdbSearchClient();

        long startTime = System.currentTimeMillis();
        System.out.println(client.searchMovieByTitle("Game of"));
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
        
        startTime = System.currentTimeMillis();
        System.out.println(client.searchByImdbID("tt0944947"));
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        List<Movie> movies = client.searchMovies("Game");
        System.out.println("Size: " + movies.size() + "\n" + movies);
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        List<TvMediaEntry> program = client.searchProgram(8, 3);
        System.out.println("Size: " + program.size() + "\n" + program);
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
    }
}
