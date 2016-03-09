package at.jku.imdbadapter.model.tvMedia;

import at.jku.imdbadapter.model.Model;

public class TvMediaEntry implements Model {

    private Broadcaster broadcaster;
    private ProgramCollection programs;

    public Broadcaster getSender() {
        return broadcaster;
    }

    public void setSender(Broadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }

    public ProgramCollection getPrograms() {
        return programs;
    }

    public void setPrograms(ProgramCollection programs) {
        this.programs = programs;
    }

    @Override
    public String toString() {
        return String.format("TvMediaEntry [sender=%s, programs=%s]", broadcaster, programs);
    }

}
