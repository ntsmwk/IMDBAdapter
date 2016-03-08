package at.jku.imdbadapter.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import at.jku.imdbadapter.model.ombd.Movie;
import at.jku.imdbadapter.model.ombd.SearchCollection;

public class SearchTask extends RecursiveTask<SearchCollection> {
    private String url;
    private boolean searchAll;

    public SearchTask(String url) {
        this.url = url;
    }

    public SearchTask(String url, boolean searchAll) {
        this.url = url;
        this.searchAll = searchAll;
    }

    @Override
    protected SearchCollection compute() {
        SearchCollection searchCollection = callSearch(url);
        if (!searchAll || determineTotalPages(searchCollection) == 1) {
            return searchCollection;
        }

        return computeAll(searchCollection);
    }

    private SearchCollection computeAll(SearchCollection searchCollection) {
        ConcurrentSkipListSet<Movie> movies = new ConcurrentSkipListSet<>(searchCollection.getMovies());
        for (ForkJoinTask<SearchCollection> task : invokeAll(generateSearchTasks(searchCollection))) {
            movies.addAll(task.join().getMovies());
        }
        return new SearchCollection(searchCollection.getTotalResults(), new ArrayList<>(movies));
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
