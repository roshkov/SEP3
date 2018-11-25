package common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Package {
	
	public static final String GETMOVIES = "GETMOVIES";
	public static final String ADD = "ADD";
	public static final String EXIT = "EXIT";
	
	@SerializedName("Header")
	@Expose
	private String header = null;
	@SerializedName("Body")
	@Expose
	private String body = null;
	@SerializedName("Movie")
	@Expose
	private Movie movie = null;

	public Package(String header) {
		this.header = header;
	}

	public Package(String header, String body) {
		this.header = header;
		this.body = body;
	}

	public Package(String header, Movie movie) {
		this.header = header;
		this.movie = movie;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getHeader() {
		return header;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
