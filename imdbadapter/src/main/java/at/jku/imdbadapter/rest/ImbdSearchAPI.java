package at.jku.imdbadapter.rest;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import at.jku.imdbadapter.builder.ImdbUrlBuilder;
import at.jku.imdbadapter.model.ombd.Movie;
import at.jku.imdbadapter.task.MovieTask;
import at.jku.imdbadapter.task.SearchTask;

/**
 * 
 * This search API enables you to look for detail on movies, series and games
 * from {@link http://http://www.imdb.com/}.
 * 
 * @author Berthold
 *
 */
public final class ImbdSearchAPI {

    public enum Type {
        MOVIE, SERIES, GAME
    }

    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    /**
     * Find movie by id
     *
     * @param id
     * @return movie
     */
    public Movie findByImdbId(String id) {
        String movieURL = new ImdbUrlBuilder().setId(id).build();
        return forkJoinPool.invoke(new MovieTask(movieURL));
    }

    /**
     * Search for movies by specific type and title
     * 
     * @param title
     * @param type
     * @return list of movies
     */
    public List<Movie> search(String title, Type type) {
        String typeAsString = type.name().toLowerCase();
        String searchURL = new ImdbUrlBuilder().setSearchTitle(title).setType(typeAsString).build();
        return forkJoinPool.invoke(new SearchTask(searchURL, true));
    }
}
