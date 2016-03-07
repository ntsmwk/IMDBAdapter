package at.jku.imdbadapter.app;

import at.jku.imdbadapter.model.SearchCollection;
import at.jku.imdbadapter.rest.ImDBRESTServiceClient;

public class ImDBMainApp {

	public static void main(String[] args) {
		ImDBRESTServiceClient client = new ImDBRESTServiceClient();
		// Movie movie = client.getMovieByTitle("Game");
		// System.out.println(movie.getActors());

		// movie = client.getMovieByID("tt0944947");
		// System.out.println(movie.getAwards());

		SearchCollection movieList = client.getMoviesBySearch("Game");
		System.out.println(movieList.getSearch());

	}
}
