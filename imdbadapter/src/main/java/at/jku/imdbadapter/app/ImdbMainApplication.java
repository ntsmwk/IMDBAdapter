package at.jku.imdbadapter.app;

import java.util.List;

import at.jku.imdbadapter.model.ombd.Movie;
import at.jku.imdbadapter.rest.ImbdSearchAPI;
import at.jku.imdbadapter.rest.TvMediaProgramAPI;
import at.jku.imdbadapter.rest.ImbdSearchAPI.Type;

public class ImdbMainApplication {

    private static ImbdSearchAPI imbdSearchAPI = new ImbdSearchAPI();
    private static TvMediaProgramAPI tvMediaSearchAPI = new TvMediaProgramAPI();

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(tvMediaSearchAPI.searchProgramsBySender("ORF1", 9, 3));
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        System.out.println(tvMediaSearchAPI.searchProgramsByTitle("Two and a Half Men", 9, 3));
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        System.out.println(imbdSearchAPI.findByImdbId("tt0944947"));
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        List<Movie> movies = imbdSearchAPI.search("Game of", Type.SERIES);
        System.out.println("Size: " + movies.size() + "\n" + movies);
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));

    }
}
