package at.jku.imdbadapter.rest;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import at.jku.imdbadapter.builder.ImdbUrlBuilder;
import at.jku.imdbadapter.model.Movie;
import at.jku.imdbadapter.task.MovieTask;
import at.jku.imdbadapter.task.SearchTask;

public class ImdbSearchClient {
    private static final String URL = "http://www.omdbapi.com/?";

    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public Movie searchMovieByTitle(String title) {
        String movieURL = new ImdbUrlBuilder(URL).setTitle(title).build();
        return forkJoinPool.invoke(new MovieTask(movieURL));
    }

    public Movie searchMovieByImdbID(String id) {
        String movieURL = new ImdbUrlBuilder(URL).setID(id).build();
        return forkJoinPool.invoke(new MovieTask(movieURL));
    }

    public List<Movie> searchMovies(String search) {
        String searchURL = new ImdbUrlBuilder(URL).setSearchTitle(search).build();
        return forkJoinPool.invoke(new SearchTask(searchURL, true)).getMovies();
    }

}
