package at.jku.imdbadapter.transformer;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import at.jku.imdbadapter.model.Movie;

public class JSONObject2MovieTransformer {

	public Movie transformToMovie(String jsonString) {
		Gson gson = new Gson();
		Movie movie = gson.fromJson(jsonString, Movie.class);
		return movie;
	}

	public List<Movie> transformToMovieList(String jsonString) {
		Gson gson = new Gson();
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		movieList = gson.fromJson(jsonString, new TypeToken<ArrayList<Movie>>() {}.getType());
		return movieList;
	}
}
