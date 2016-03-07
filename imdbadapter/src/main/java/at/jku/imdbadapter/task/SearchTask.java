package at.jku.imdbadapter.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import at.jku.imdbadapter.model.Movie;
import at.jku.imdbadapter.model.Search;

public class SearchTask extends RecursiveTask<Search> {
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
    protected Search compute() {
        Search search = callSearch(url, Search.class);
        if (!searchAll || determineTotalPages(search) == 1) {
            return search;
        }

        return computeAll(search);
    }

    private Search computeAll(Search search) {
        ConcurrentSkipListSet<Movie> movies = new ConcurrentSkipListSet<>(search.getMovies());
        for (ForkJoinTask<Search> task : invokeAll(generateSearchTasks(search))) {
            movies.addAll(task.join().getMovies());
        }
        return new Search(search.getTotalResults(), new ArrayList<>(movies));
    }

    private List<SearchTask> generateSearchTasks(Search search) {
        List<SearchTask> searchTasks = new ArrayList<>();
        for (int i = 2; i <= determineTotalPages(search); i++) {
            searchTasks.add(new SearchTask(buildSearchUrl(i)));
        }
        return searchTasks;
    }

    private String buildSearchUrl(int page) {
        StringBuilder builder = new StringBuilder(url);
        builder.append("&page=").append(page);
        return builder.toString();
    }

    private int determineTotalPages(Search search) {
        return (search.getTotalResults() / 10) + 1;
    }

    private <T> T callSearch(String url, Class<T> responseClass) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        return client.target(url).request().get(responseClass);
    }
}
