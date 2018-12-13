/**
 * 
 */
package common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Claudiu
 *
 */
public class Movie {
	@SerializedName("Title")
	@Expose
	private String title;
	@SerializedName("YearCreation")
	@Expose
	private String yearCreation;
	@SerializedName("ReleaseDate")
	@Expose
	private String releaseDate;
	@SerializedName("Price")
	@Expose
	private double price;
	@SerializedName("NameStudio")
	@Expose
	private String nameStudio;
	@SerializedName("NameDirector")
	@Expose
	private String nameDirector;
	@SerializedName("Description")
	@Expose
	private String description;
	@SerializedName("NameMainActor")
	@Expose
	private String nameMainActor;
	@SerializedName("Rented")
	@Expose
	private boolean rented;

	public Movie(String title) {
		this.title = title;
	}

	public Movie(String title, String yearCreation, String releaseDate, double price, String nameStudio,
			String nameDirector, String description, String nameMainActor) {
		super();
		this.title = title;
		this.yearCreation = yearCreation;
		this.releaseDate = releaseDate;
		this.price = price;
		this.nameStudio = nameStudio;
		this.nameDirector = nameDirector;
		this.description = description;
		this.nameMainActor = nameMainActor;
		this.rented = false;
	}
	
	public Movie(String title, String yearCreation, String releaseDate, String nameStudio,
			String nameDirector, String description, String nameMainActor) {
		super();
		this.title = title;
		this.yearCreation = yearCreation;
		this.releaseDate = releaseDate;
		this.nameStudio = nameStudio;
		this.nameDirector = nameDirector;
		this.description = description;
		this.nameMainActor = nameMainActor;
		this.rented = false;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYearCreation() {
		return yearCreation;
	}

	public void setYearCreation(String yearCreation) {
		this.yearCreation = yearCreation;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getNameStudio() {
		return nameStudio;
	}

	public void setNameStudio(String nameStudio) {
		this.nameStudio = nameStudio;
	}

	public String getNameDirector() {
		return nameDirector;
	}

	public void setNameDirector(String nameDirector) {
		this.nameDirector = nameDirector;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNameMainActor() {
		return nameMainActor;
	}

	public void setNameMainActor(String nameMainActor) {
		this.nameMainActor = nameMainActor;
	}

	@Override
	public String toString() {
		return "Movie [Title=" + title + ", yearCreation=" + yearCreation + ", releaseDate=" + releaseDate + ", price="
				+ price + ", nameStudio=" + nameStudio + ", nameDirector=" + nameDirector + ", description="
				+ description + ", nameMainActor=" + nameMainActor + "]";
	}

	public boolean getRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

}
