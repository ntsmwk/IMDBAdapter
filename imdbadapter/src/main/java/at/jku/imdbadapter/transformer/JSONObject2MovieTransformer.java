package at.jku.imdbadapter.transformer;

import com.google.gson.Gson;

import at.jku.imdbadapter.model.Movie;
import at.jku.imdbadapter.model.SearchCollection;

public class JSONObject2MovieTransformer {

	public Movie transformToMovie(String jsonString) {
		Gson gson = new Gson();
		Movie movie = gson.fromJson(jsonString, Movie.class);
		return movie;
	}

	public SearchCollection transformToMovieList(String jsonString) {
		Gson gson = new Gson();
		SearchCollection movieList = gson.fromJson(jsonString, SearchCollection.class);
		return movieList;
	}
}
