package at.jku.imdbadapter.model.tvMedia;

import at.jku.imdbadapter.model.Model;

public class Sender implements Model {
    private long id;
    private String sender;
    private String sendername;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    @Override
    public String toString() {
        return String.format("Sender [sendername=%s]", sendername);
    }

}
