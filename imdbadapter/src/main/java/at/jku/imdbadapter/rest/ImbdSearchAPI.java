package at.jku.imdbadapter.rest;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import at.jku.imdbadapter.builder.ImdbUrlBuilder;
import at.jku.imdbadapter.model.ombd.Movie;
import at.jku.imdbadapter.task.MovieTask;
import at.jku.imdbadapter.task.SearchTask;

public final class ImbdSearchAPI {
    public enum Type {
        MOVIE, SERIES, GAME
    }

    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    public Movie findByImdbId(String id) {
        String movieURL = new ImdbUrlBuilder().setId(id).build();
        return forkJoinPool.invoke(new MovieTask(movieURL));
    }

    public List<Movie> search(String searchString, Type type) {
        String typeAsString = type.name().toLowerCase();
        String searchURL = new ImdbUrlBuilder().setSearchTitle(searchString).setType(typeAsString).build();
        return forkJoinPool.invoke(new SearchTask(searchURL, true));
    }
}
