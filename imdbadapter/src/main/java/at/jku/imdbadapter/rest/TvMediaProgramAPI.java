package at.jku.imdbadapter.rest;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import at.jku.imdbadapter.builder.TvMediaUrlBuilder;
import at.jku.imdbadapter.model.tvMedia.Program;
import at.jku.imdbadapter.task.ProgramsTask;

/**
 * This API enables you to look up the program based on
 * {@link http://www.tv-media.com}.
 */
public final class TvMediaProgramAPI {

    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    /**
     * Search programs by title for specific day
     * 
     * @param title
     * @param day
     * @param month
     * @return programs
     */
    public List<Program> searchByTitle(String title, int day, int month) {
        String programUrl = new TvMediaUrlBuilder().setDay(day).setMonth(month).build();
        return filterProgramsByTitle(title, forkJoinPool.invoke(new ProgramsTask(programUrl)));
    }

    /**
     * Search programs by broadcaster for specific day
     * 
     * @param broadcaster
     * @param day
     * @param month
     * @return programs
     */
    public List<Program> searchByBroadcaster(String broadcaster, int day, int month) {
        String programUrl = new TvMediaUrlBuilder().setDay(day).setMonth(month).build();
        return filterProgramsBySender(broadcaster, forkJoinPool.invoke(new ProgramsTask(programUrl)));
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
