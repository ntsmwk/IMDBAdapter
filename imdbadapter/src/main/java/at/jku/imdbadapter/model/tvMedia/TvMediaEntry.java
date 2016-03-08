package at.jku.imdbadapter.model.tvMedia;

import at.jku.imdbadapter.model.Model;

public class TvMediaEntry implements Model {

    private Sender sender;
    private ProgramCollection programs;

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public ProgramCollection getPrograms() {
        return programs;
    }

    public void setPrograms(ProgramCollection programs) {
        this.programs = programs;
    }

    @Override
    public String toString() {
        return String.format("TvMediaEntry [sender=%s, programs=%s]", sender, programs);
    }

}
