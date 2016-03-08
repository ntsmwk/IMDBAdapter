package at.jku.imdbadapter.model.ombd;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class SearchCollection extends OmbdModel {
    private int totalResults;
    private List<Movie> movies = new ArrayList<>();

    public SearchCollection() {
    }

    public SearchCollection(int totalResults, List<Movie> movies) {
        setValid(true);
        this.movies = movies;
        this.totalResults = totalResults;
    }

    @XmlElement(name = "Search")
    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

}
