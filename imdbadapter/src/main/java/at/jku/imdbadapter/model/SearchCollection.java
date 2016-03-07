package at.jku.imdbadapter.model;

public class SearchCollection {

	private final SearchResult[] Search;
	private final String totalResults;
	private final String Response;

	public SearchCollection(SearchResult[] search, String totalResults, String response) {
		super();
		Search = search;
		this.totalResults = totalResults;
		Response = response;
	}

	public SearchResult[] getSearch() {
		return Search;
	}

	public String getTotalResults() {
		return totalResults;
	}

	public String getResponse() {
		return Response;
	}

}
