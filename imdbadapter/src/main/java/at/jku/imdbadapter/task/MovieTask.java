package at.jku.imdbadapter.task;

import java.util.concurrent.RecursiveTask;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import at.jku.imdbadapter.model.Movie;

public class MovieTask extends RecursiveTask<Movie> {

    private String url;

    public MovieTask(String url) {
        this.url = url;
    }

    @Override
    protected Movie compute() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(url);
        return target.request().get(Movie.class);
    }

}
