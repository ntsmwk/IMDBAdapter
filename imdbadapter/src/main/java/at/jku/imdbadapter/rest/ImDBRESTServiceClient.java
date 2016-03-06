package at.jku.imdbadapter.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import at.jku.imdbadapter.builder.ImDBURLBuilder;
import at.jku.imdbadapter.model.Movie;
import at.jku.imdbadapter.model.ResponseType;
import at.jku.imdbadapter.transformer.JSONObject2MovieTransformer;

public class ImDBRESTServiceClient {

	private final String URL = "http://www.omdbapi.com/?";

	public Movie getMovieByTitle(String title) {
		String movieURL = new ImDBURLBuilder(URL).setResponseType(ResponseType.JSON).setTitle(title).build();
		String response = callImDBRestService(movieURL);
		return new JSONObject2MovieTransformer().transformToMovie(response);
	}

	public Movie getMovieByID(String id) {
		String movieURL = new ImDBURLBuilder(URL).setResponseType(ResponseType.JSON).setID(id).build();
		String response = callImDBRestService(movieURL);
		return new JSONObject2MovieTransformer().transformToMovie(response);
	}

	public List<Movie> getMoviesBySearch(String search) {
		String searchURL = new ImDBURLBuilder(URL).setResponseType(ResponseType.JSON).setSearchTitle(search).build();
		String response = callImDBRestService(searchURL);
		return new JSONObject2MovieTransformer().transformToMovieList(response);
		
	}

	private String callImDBRestService(String URLString) {
		String response = "";

		try {
			URL url = new URL(URLString);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String line;
			while ((line = in.readLine()) != null) {
				response += line + "\n";
			}
			in.close();
		} catch (Exception e) {
			System.out.println("Error while calling REST Service");
			e.printStackTrace();
		}
		return response;
	}

}
