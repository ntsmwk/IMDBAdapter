package at.jku.imdbadapter.builder;

import java.text.MessageFormat;

public class TvMediaUrlBuilder {

    private static final String INT_FORMAT = "%02d";
    private static final String TV_MEDIA_URL = "http://www.tv-media.at/nnw1-tv-data/programs_{0}.{1}.json";

    private int day;
    private int month;

    public TvMediaUrlBuilder setDay(int day) {
        this.day = day;
        return this;
    }

    public TvMediaUrlBuilder setMonth(int month) {
        this.month = month;
        return this;
    }

    public String build() {
        String dayAsString = String.format(INT_FORMAT, day);
        String monthAsString = String.format(INT_FORMAT, month);
        return MessageFormat.format(TV_MEDIA_URL, dayAsString, monthAsString);
    }

}
