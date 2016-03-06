package at.jku.imdbadapter.model;

public enum ResponseType {
	JSON("json"), XML("xml");

	String value;

	private ResponseType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
