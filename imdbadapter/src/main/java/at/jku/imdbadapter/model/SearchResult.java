package at.jku.imdbadapter.model;

public class SearchResult {

	private final String Title;
	private final String Year;
	private final String imdbID;
	private final String Type;
	private final String Poster;

	public SearchResult(String title, String year, String imdbID, String type, String poster) {
		super();
		Title = title;
		Year = year;
		this.imdbID = imdbID;
		Type = type;
		Poster = poster;
	}

	public String getTitle() {
		return Title;
	}

	public String getYear() {
		return Year;
	}

	public String getImdbID() {
		return imdbID;
	}

	public String getType() {
		return Type;
	}

	public String getPoster() {
		return Poster;
	}

}
