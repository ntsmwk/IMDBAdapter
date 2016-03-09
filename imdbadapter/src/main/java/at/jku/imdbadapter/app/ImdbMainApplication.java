package at.jku.imdbadapter.app;

import java.util.List;

import at.jku.imdbadapter.model.ombd.Movie;
import at.jku.imdbadapter.model.tvMedia.Program;
import at.jku.imdbadapter.rest.ImbdSearchAPI;
import at.jku.imdbadapter.rest.ImbdSearchAPI.Type;
import at.jku.imdbadapter.rest.TvMediaProgramAPI;

/**
 * 
 * This application is a testcase to show the user, how to use your API.
 * 
 * @author Berthold
 *
 */

public class ImdbMainApplication {

    private static ImbdSearchAPI imbdSearchAPI = new ImbdSearchAPI();
    private static TvMediaProgramAPI tvMediaSearchAPI = new TvMediaProgramAPI();

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<Program> programs = tvMediaSearchAPI.searchByBroadcaster("ORF1", 9, 3);
        System.out.println("size: " + programs.size() + ", programs: " + programs);
        System.out.println("time: " + (System.currentTimeMillis() - startTime) + "\n");

        startTime = System.currentTimeMillis();
        programs = tvMediaSearchAPI.searchByTitle("Two and a Half Men", 1, 3);
        System.out.println("size: " + programs.size() + ", programs: " + programs);
        System.out.println("time: " + (System.currentTimeMillis() - startTime) + "\n");

        startTime = System.currentTimeMillis();
        List<Movie> movies = imbdSearchAPI.search("Game of", Type.SERIES);
        System.out.println("size: " + movies.size() + ", movie: " + movies);
        System.out.println("time: " + (System.currentTimeMillis() - startTime) + "\n");

        startTime = System.currentTimeMillis();
        System.out.println(imbdSearchAPI.findByImdbId("tt0944947"));
        System.out.println("time: " + (System.currentTimeMillis() - startTime));
    }
}
