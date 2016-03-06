package at.jku.imdbadapter.builder;

import at.jku.imdbadapter.model.ResponseType;

public class ImDBURLBuilder {

	StringBuilder url;

	public ImDBURLBuilder(String url) {
		this.url = new StringBuilder(url);
	}

	public ImDBURLBuilder setResponseType(ResponseType responseType) {
		url.append("&r=" + responseType.getValue());
		return this;
	}

	public ImDBURLBuilder setTitle(String title) {
		url.append("&t=" + title);
		return this;
	}
	
	public ImDBURLBuilder setSearchTitle(String title) {
		url.append("&s=" + title);
		return this;
	}

	public ImDBURLBuilder setID(String id) {
		url.append("&i=" + id);
		return this;
	}

	public ImDBURLBuilder setYear(int year) {
		url.append("&y=" + year);
		return this;
	}

	public String build() {
		return url.toString();
	}

}
