package at.jku.imdbadapter.rest;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import at.jku.imdbadapter.builder.TvMediaUrlBuilder;
import at.jku.imdbadapter.model.tvMedia.Program;
import at.jku.imdbadapter.task.ProgramsTask;

public final class TvMediaProgramAPI {
    
    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    public List<Program> searchProgramsByTitle(String title, int day, int month) {
        String programUrl = new TvMediaUrlBuilder().setDay(day).setMonth(month).build();
        return filterProgramsByTitle(title, forkJoinPool.invoke(new ProgramsTask(programUrl)));
    }

    public List<Program> searchProgramsBySender(String sender, int day, int month) {
        String programUrl = new TvMediaUrlBuilder().setDay(day).setMonth(month).build();
        return filterProgramsBySender(sender, forkJoinPool.invoke(new ProgramsTask(programUrl)));
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
        return (program) -> program.getBroadcaster().equals(sender);
    }
}
