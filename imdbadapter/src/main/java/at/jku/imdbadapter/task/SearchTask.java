package at.jku.imdbadapter.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

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
        if (!recurivseSearch || determineTotalPages(searchCollection) == 1) {
            return searchCollection.getMovies();
        }
        return computeAll(searchCollection);
    }

    private List<Movie> computeAll(SearchCollection searchCollection) {
        ConcurrentSkipListSet<Movie> movies = new ConcurrentSkipListSet<>(searchCollection.getMovies());
        for (ForkJoinTask<List<Movie>> task : invokeAll(generateSearchTasks(searchCollection))) {
            movies.addAll(task.join());
        }
        return new ArrayList<>(movies);
    }

    private List<SearchTask> generateSearchTasks(SearchCollection searchCollection) {
        List<SearchTask> searchTasks = new ArrayList<>();
        for (int i = 2; i <= determineTotalPages(searchCollection); i++) {
            searchTasks.add(new SearchTask(buildSearchUrl(i)));
        }
        return searchTasks;
    }

    private String buildSearchUrl(int page) {
        StringBuilder builder = new StringBuilder(url);
        builder.append("&page=").append(page);
        return builder.toString();
    }

    private int determineTotalPages(SearchCollection searchCollection) {
        return (searchCollection.getTotalResults() / 10) + 1;
    }

    private SearchCollection callSearch(String url) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        return client.target(url).request().get(SearchCollection.class);
    }
}
