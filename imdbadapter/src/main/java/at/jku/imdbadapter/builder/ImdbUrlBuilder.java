package at.jku.imdbadapter.builder;

import at.jku.imdbadapter.model.ombd.Type;

public class ImdbUrlBuilder {
    private static final String OMBD_URL = "http://www.omdbapi.com/?";

    private StringBuilder urlBuilder;

    public ImdbUrlBuilder() {
        this.urlBuilder = new StringBuilder(OMBD_URL).append("&r=json");
    }

    public ImdbUrlBuilder setTitle(String title) {
        urlBuilder.append("&t=" + title);
        return this;
    }

    public ImdbUrlBuilder setSearchTitle(String title) {
        urlBuilder.append("&s=" + title);
        return this;
    }

    public ImdbUrlBuilder setID(String id) {
        urlBuilder.append("&i=" + id);
        return this;
    }

    public ImdbUrlBuilder setYear(int year) {
        urlBuilder.append("&y=" + year);
        return this;
    }

    public ImdbUrlBuilder setType(Type movie) {
        urlBuilder.append("&type=" + movie.name().toLowerCase());
        return this;
    }

    public ImdbUrlBuilder setPage(int page) {
        urlBuilder.append("&page=" + page);
        return this;
    }

    public String build() {
        return urlBuilder.toString();
    }
}
