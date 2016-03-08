package at.jku.imdbadapter.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import at.jku.imdbadapter.builder.ImdbUrlBuilder;
import at.jku.imdbadapter.builder.TvMediaUrlBuilder;
import at.jku.imdbadapter.model.ombd.Movie;
import at.jku.imdbadapter.model.ombd.Type;
import at.jku.imdbadapter.model.tvMedia.Program;
import at.jku.imdbadapter.model.tvMedia.ProgramCollection;
import at.jku.imdbadapter.model.tvMedia.TvMediaEntry;
import at.jku.imdbadapter.task.MovieTask;
import at.jku.imdbadapter.task.ProgrammTask;
import at.jku.imdbadapter.task.SearchTask;

public class ImdbSearchClient {
    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    public Movie searchByTitle(String title) {
        String movieURL = new ImdbUrlBuilder().setTitle(title).build();
        return forkJoinPool.invoke(new MovieTask(movieURL));
    }

    public Movie searchByImdbID(String id) {
        String movieURL = new ImdbUrlBuilder().setID(id).build();
        return forkJoinPool.invoke(new MovieTask(movieURL));
    }

    public List<Program> searchProgramm(String title, int day, int month) {
        String programUrl = new TvMediaUrlBuilder().setDay(day).setMonth(month).build();
        return findProgramsByTitle(title, forkJoinPool.invoke(new ProgrammTask(programUrl)));
    }
    
    public ProgramCollection searchProgramCollection(String sender, int day, int month) {
        String programUrl = new TvMediaUrlBuilder().setDay(day).setMonth(month).build();
        return findProgramsBySender(sender, forkJoinPool.invoke(new ProgrammTask(programUrl)));
    }

    public List<Movie> searchMovies(String search) {
        String searchURL = new ImdbUrlBuilder().setSearchTitle(search).setType(Type.MOVIE).build();
        return forkJoinPool.invoke(new SearchTask(searchURL, true)).getMovies();
    }

    private List<Program> findProgramsByTitle(String title, List<TvMediaEntry> tvMediaEntries) {
        Stream<Program> programStream = tvMediaEntries.parallelStream()
                .map((tvMediaEntry) -> mapTvMediaEntryToPrograms(tvMediaEntry))
                .flatMap((programs) -> programs.stream());
        return programStream.filter(filterProgramByTitle(title)).collect(Collectors.toList());
    }

    private Predicate<Program> filterProgramByTitle(String title) {
        return (program) -> program.getTitle().equals(title);
    }

    private List<Program> mapTvMediaEntryToPrograms(TvMediaEntry tvMediaEntry) {
        List<Program> programs = new ArrayList<>();
        programs.addAll(tvMediaEntry.getPrograms().getMorning());
        programs.addAll(tvMediaEntry.getPrograms().getMidday());
        programs.addAll(tvMediaEntry.getPrograms().getEvening());
        programs.addAll(tvMediaEntry.getPrograms().getNight());
        return programs;
    }

    private ProgramCollection findProgramsBySender(String sender, List<TvMediaEntry> tvMediaEntries) {
        Stream<TvMediaEntry> stream = tvMediaEntries.parallelStream().filter(createSenderFilter(sender));
        return stream.map((tvMediaEntry) -> tvMediaEntry.getPrograms()).findAny().get();
    }

    private Predicate<TvMediaEntry> createSenderFilter(String sender) {
        return (tvMediaEntry) -> tvMediaEntry.getSender().getSender().equals(sender);
    }

}
