package at.jku.imdbadapter.rest;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import at.jku.imdbadapter.builder.ImdbUrlBuilder;
import at.jku.imdbadapter.builder.TvMediaUrlBuilder;
import at.jku.imdbadapter.model.ombd.Movie;
import at.jku.imdbadapter.model.ombd.Type;
import at.jku.imdbadapter.model.tvMedia.TvMediaEntry;
import at.jku.imdbadapter.task.MovieTask;
import at.jku.imdbadapter.task.ProgrammTask;
import at.jku.imdbadapter.task.SearchTask;

public class ImdbSearchClient {
    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    public List<TvMediaEntry> searchProgram(int day, int month) {
        String programmUrl = new TvMediaUrlBuilder().setDay(day).setMonth(month).build();
        return forkJoinPool.invoke(new ProgrammTask(programmUrl));
    }

    public Movie searchMovieByTitle(String title) {
        String movieURL = new ImdbUrlBuilder().setTitle(title).build();
        return forkJoinPool.invoke(new MovieTask(movieURL));
    }

    public Movie searchByImdbID(String id) {
        String movieURL = new ImdbUrlBuilder().setID(id).build();
        return forkJoinPool.invoke(new MovieTask(movieURL));
    }

    public List<Movie> searchMovies(String search) {
        String searchURL = new ImdbUrlBuilder().setSearchTitle(search).setType(Type.MOVIE).build();
        return forkJoinPool.invoke(new SearchTask(searchURL, true)).getMovies();
    }

}
