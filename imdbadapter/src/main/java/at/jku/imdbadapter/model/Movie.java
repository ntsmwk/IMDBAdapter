package at.jku.imdbadapter.model;

public class Movie extends SearchResult {

	private final String Rated;
	private final String Released;
	private final String Runtime;
	private final String Genre;
	private final String Director;
	private final String Writer;
	private final String Actors;
	private final String Plot;
	private final String Language;
	private final String Country;
	private final String Awards;
	private final String Metascore;
	private final String imdbRating;
	private final String imdbVotes;
	private final String Response;

	public Movie(String title, String year, String rated, String released, String runtime, String genre,
			String director, String writer, String actors, String plot, String language, String country, String awards,
			String poster, String metascore, String imdbRating, String imdbVotes, String imdbID, String type,
			String response) {
		super(title, year, imdbID, type, poster);
		Rated = rated;
		Released = released;
		Runtime = runtime;
		Genre = genre;
		Director = director;
		Writer = writer;
		Actors = actors;
		Plot = plot;
		Language = language;
		Country = country;
		Awards = awards;
		Metascore = metascore;
		this.imdbRating = imdbRating;
		this.imdbVotes = imdbVotes;
		Response = response;
	}

	public String getRated() {
		return Rated;
	}

	public String getReleased() {
		return Released;
	}

	public String getRuntime() {
		return Runtime;
	}

	public String getGenre() {
		return Genre;
	}

	public String getDirector() {
		return Director;
	}

	public String getWriter() {
		return Writer;
	}

	public String getActors() {
		return Actors;
	}

	public String getPlot() {
		return Plot;
	}

	public String getLanguage() {
		return Language;
	}

	public String getCountry() {
		return Country;
	}

	public String getAwards() {
		return Awards;
	}

	public String getMetascore() {
		return Metascore;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public String getImdbVotes() {
		return imdbVotes;
	}

	public String getResponsde() {
		return Response;
	}

}
