package at.jku.imdbadapter.rest;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import at.jku.imdbadapter.builder.ImdbUrlBuilder;
import at.jku.imdbadapter.builder.TvMediaUrlBuilder;
import at.jku.imdbadapter.model.ombd.Movie;
import at.jku.imdbadapter.model.tvMedia.Program;
import at.jku.imdbadapter.task.MovieTask;
import at.jku.imdbadapter.task.ProgramsTask;
import at.jku.imdbadapter.task.SearchTask;

public final class ImdbSearchClient {
    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    public Movie searchByTitle(String title) {
        String movieURL = new ImdbUrlBuilder().setTitle(title).build();
        return forkJoinPool.invoke(new MovieTask(movieURL));
    }

    public Movie searchByImdbID(String id) {
        String movieURL = new ImdbUrlBuilder().setID(id).build();
        return forkJoinPool.invoke(new MovieTask(movieURL));
    }

    public List<Program> searchProgramsByTitle(String title, int day, int month) {
        String programUrl = new TvMediaUrlBuilder().setDay(day).setMonth(month).build();
        return filterProgramsByTitle(title, forkJoinPool.invoke(new ProgramsTask(programUrl)));
    }

    public List<Program> searchProgramsBySender(String sender, int day, int month) {
        String programUrl = new TvMediaUrlBuilder().setDay(day).setMonth(month).build();
        return filterProgramsBySender(sender, forkJoinPool.invoke(new ProgramsTask(programUrl)));
    }

    public List<Movie> searchMovies(String search) {
        String searchURL = new ImdbUrlBuilder().setSearchTitle(search).build();
        return forkJoinPool.invoke(new SearchTask(searchURL, true)).getMovies();
    }

    private List<Program> filterProgramsByTitle(String title, List<Program> programs) {
        return programs.parallelStream().filter(createProgramTitlePredicate(title)).collect(Collectors.toList());
    }

    private List<Program> filterProgramsBySender(String sender, List<Program> programs) {
        return programs.stream().filter(createProgramSenderPredicate(sender)).collect(Collectors.toList());
    }

    private Predicate<Program> createProgramTitlePredicate(String title) {
        return (program) -> program.getTitle().equals(title);
    }

    private Predicate<Program> createProgramSenderPredicate(String sender) {
        return (program) -> program.getSender().equals(sender);
    }
}
