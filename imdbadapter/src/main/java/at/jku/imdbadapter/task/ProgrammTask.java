package at.jku.imdbadapter.task;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import at.jku.imdbadapter.model.tvMedia.TvMediaEntry;

public class ProgrammTask extends RecursiveTask<List<TvMediaEntry>> {

    private String url;

    public ProgrammTask(String url) {
        this.url = url;
    }

    @Override
    protected List<TvMediaEntry> compute() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        return Arrays.asList(client.target(url).request().get(TvMediaEntry[].class));
    }
}
