package common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Package class used to send information between tiers
 * 
 * @author Stefan
 *
 */
public class Package {
	
	public static final String GETMOVIES = "GETMOVIES";
	public static final String VERIFY = "VERIFY";
	public static final String ADD = "ADD";
	public static final String EXIT = "EXIT";
	
	/**
	 * Indicates the purpose of the package
	 */
	@SerializedName("Header")
	@Expose
	private String header = null;
	/**
	 * If more information in the form of text is needed
	 */
	@SerializedName("Body")
	@Expose
	private String body = null;
	/**
	 * In order to be able to send Movie objects within packages
	 * 
	 * @see Movie
	 */
	@SerializedName("Movie")
	@Expose
	private Movie movie = null;
	/**
	 * You can send the yearCreation field of a movie separately to have it validated
	 * @see Movie
	 */
	@SerializedName("YearCreation")
	@Expose
	private String yearCreation = null;
	/**
	 * You can send the releaseDate field of a movie separately to have it validated
	 * @see Movie
	 */
	@SerializedName("ReleaseDate")
	@Expose
	private String releaseDate = null;
	/**
	 * You can send the price field of a movie separately to have it validated
	 * @see Movie
	 */
	@SerializedName("Price")
	@Expose
	private String price = null;

	/**
	 * Used to specify commands without any data actually contained in the package
	 * @param header
	 */
	public Package(String header) {
		this.header = header;
	}

	/**
	 * Package with some text information contained in the body
	 * @param header
	 * @param body
	 */
	public Package(String header, String body) {
		this.header = header;
		this.body = body;
	}

	/**
	 * Used to send a Movie
	 * @param header
	 * @param movie
	 */
	public Package(String header, Movie movie) {
		this.header = header;
		this.movie = movie;
	}
	
	/**
	 * Used in tier1 when sending the user's input for validation by tier2
	 * @see tier1.controller.Tier1MovieCreatorController#execute(int)
	 * @param header
	 * @param yearCreation
	 * @param releaseDate
	 * @param price
	 * @param movie
	 */
	public Package(String header, String yearCreation, String releaseDate, String price, Movie movie)
	{
		this.header = header;
		this.yearCreation = yearCreation;
		this.releaseDate = releaseDate;
		this.price = price;
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

	public String getYearCreation() {
		return yearCreation;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public String getPrice() {
		return price;
	}
}
