package at.jku.imdbadapter.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Search extends BaseModel {
    private int totalResults;
    private List<Movie> movies;

    public Search(){
    }
    
    public Search(int totalResults, List<Movie> movies) {
        setResponse(true);
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
