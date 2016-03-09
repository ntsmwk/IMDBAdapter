package at.jku.imdbadapter.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import at.jku.imdbadapter.builder.ImdbUrlBuilder;
import at.jku.imdbadapter.model.ombd.Movie;
import at.jku.imdbadapter.model.ombd.SearchCollection;

public class SearchTask extends RecursiveTask<List<Movie>> {
    private String url;
    private boolean recurivseSearch;

    public SearchTask(String url) {
        this.url = url;
    }

    public SearchTask(String url, boolean recursiveSearch) {
        this.url = url;
        this.recurivseSearch = recursiveSearch;
    }

    @Override
    protected List<Movie> compute() {
        SearchCollection searchCollection = callSearch(url);
        if (!searchCollection.isValid()) {
            return Collections.emptyList();
        }
        if (!recurivseSearch || determinePages(searchCollection.getTotalResults()) == 1) {
            return loadMovieDetails(searchCollection);
        }
        return computeAll(searchCollection.getTotalResults());
    }

    private List<Movie> loadMovieDetails(SearchCollection searchCollection) {
        ConcurrentSkipListSet<Movie> movies = new ConcurrentSkipListSet<>();
        for (ForkJoinTask<Movie> task : invokeAll(generateMovieTasks(searchCollection.getMovies()))) {
            movies.add(task.join());
        }
        return new ArrayList<>(movies);
    }

    private List<Movie> computeAll(int totalResults) {
        ConcurrentSkipListSet<Movie> movies = new ConcurrentSkipListSet<>();
        for (ForkJoinTask<List<Movie>> task : invokeAll(generateSearchTasks(totalResults))) {
            movies.addAll(task.join());
        }
        return new ArrayList<>(movies);
    }

    private List<SearchTask> generateSearchTasks(int totalResults) {
        List<SearchTask> searchTasks = new ArrayList<>();
        for (int i = 1; i <= determinePages(totalResults); i++) {
            searchTasks.add(new SearchTask(buildSearchUrl(i)));
        }
        return searchTasks;
    }

    private List<MovieTask> generateMovieTasks(List<Movie> movies) {
        return movies.stream().map((movie) -> createMovieTask(movie)).collect(Collectors.toList());
    }

    private MovieTask createMovieTask(Movie movie) {
        String movieUrl = new ImdbUrlBuilder().setId(movie.getImdbId()).build();
        return new MovieTask(movieUrl);
    }

    private int determinePages(int totalResults) {
        return (totalResults / 10) + 1;
    }

    private String buildSearchUrl(int page) {
        StringBuilder builder = new StringBuilder(url);
        builder.append("&page=").append(page);
        return builder.toString();
    }

    private SearchCollection callSearch(String url) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        return client.target(url).request().get(SearchCollection.class);
    }
}
