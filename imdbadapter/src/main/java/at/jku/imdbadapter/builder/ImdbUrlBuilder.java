package at.jku.imdbadapter.builder;

public class ImdbUrlBuilder {

    private StringBuilder url;

    public ImdbUrlBuilder(String url) {
        this.url = new StringBuilder(url).append("&r=json");
    }

    public ImdbUrlBuilder setTitle(String title) {
        url.append("&t=" + title);
        return this;
    }

    public ImdbUrlBuilder setSearchTitle(String title) {
        url.append("&s=" + title);
        return this;
    }

    public ImdbUrlBuilder setID(String id) {
        url.append("&i=" + id);
        return this;
    }

    public ImdbUrlBuilder setYear(int year) {
        url.append("&y=" + year);
        return this;
    }

    public ImdbUrlBuilder setPage(int page) {
        url.append("&page=" + page);
        return this;
    }

    public String build() {
        return url.toString();
    }
}
