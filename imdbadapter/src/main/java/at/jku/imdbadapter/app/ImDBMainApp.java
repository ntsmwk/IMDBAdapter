package at.jku.imdbadapter.app;

import java.util.List;

import at.jku.imdbadapter.model.Movie;
import at.jku.imdbadapter.rest.ImDBRESTServiceClient;

public class ImDBMainApp {

	public static void main(String[] args) {
		ImDBRESTServiceClient client = new ImDBRESTServiceClient();
		// Movie movie = client.getMovieByTitle("Game");
		// System.out.println(movie.getActors());

		// movie = client.getMovieByID("tt0944947");
		// System.out.println(movie.getAwards());

		List<Movie> movieList = client.getMoviesBySearch("Game");
		System.out.println(movieList.get(0).getAwards());

	}
}
