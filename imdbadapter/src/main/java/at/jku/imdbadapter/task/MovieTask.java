package at.jku.imdbadapter.task;

import java.util.concurrent.RecursiveTask;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import at.jku.imdbadapter.model.ombd.Movie;

public class MovieTask extends RecursiveTask<Movie> {

    private String url;

    public MovieTask(String url) {
        this.url = url;
    }

    @Override
    protected Movie compute() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        Movie movie = client.target(url).request().get(Movie.class);
        return movie.isValid() ? movie : null;
    }

}
