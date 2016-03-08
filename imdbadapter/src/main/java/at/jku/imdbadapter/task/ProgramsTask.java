package at.jku.imdbadapter.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import at.jku.imdbadapter.model.tvMedia.Program;
import at.jku.imdbadapter.model.tvMedia.TvMediaEntry;

public class ProgramsTask extends RecursiveTask<List<Program>> {

    private String url;

    public ProgramsTask(String url) {
        this.url = url;
    }

    @Override
    protected List<Program> compute() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        return collectPrograms(client.target(url).request().get(TvMediaEntry[].class));
    }

    private List<Program> collectPrograms(TvMediaEntry[] tvMediaEntries) {
        List<TvMediaEntry> tvMediaEntryList = Arrays.asList(tvMediaEntries);
        tvMediaEntryList.parallelStream().forEach((tvMediaEntry) -> updateTvMediaEntry(tvMediaEntry));

        return tvMediaEntryList.parallelStream().map((tvMediaEntry) -> mapTvMediaEntryToPrograms(tvMediaEntry))
                .flatMap((programs) -> programs.stream()).collect(Collectors.toList());
    }

    private List<Program> mapTvMediaEntryToPrograms(TvMediaEntry tvMediaEntry) {
        List<Program> programs = new ArrayList<>();
        programs.addAll(tvMediaEntry.getPrograms().getMorning());
        programs.addAll(tvMediaEntry.getPrograms().getMidday());
        programs.addAll(tvMediaEntry.getPrograms().getEvening());
        programs.addAll(tvMediaEntry.getPrograms().getNight());
        return programs;
    }

    private void updateTvMediaEntry(TvMediaEntry tvMediaEntry) {
        String sender = tvMediaEntry.getSender().getSender();
        updateProgramList(mapTvMediaEntryToPrograms(tvMediaEntry), sender);
    }

    private void updateProgramList(List<Program> programs, String sender) {
        programs.parallelStream().forEach((program) -> updateProgram(program, sender));
    }

    private void updateProgram(Program program, String sender) {
        program.setSender(sender);
    }
}
