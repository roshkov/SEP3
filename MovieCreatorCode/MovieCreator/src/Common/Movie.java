/**
 * 
 */
package Common;

/**
 * @author Claudiu
 *
 */
public class Movie {
private String Title;
private String yearCreation;
private String releaseDate;
private double price;
private String nameStudio;
private String nameDirector;
private String description;
private String nameMainActor;
public Movie(String title, String yearCreation, String releaseDate, double price, String nameStudio,
		String nameDirector, String description, String nameMainActor) {
	super();
	Title = title;
	this.yearCreation = yearCreation;
	this.releaseDate = releaseDate;
	this.price = price;
	this.nameStudio = nameStudio;
	this.nameDirector = nameDirector;
	this.description = description;
	this.nameMainActor = nameMainActor;
}
public String getTitle() {
	return Title;
}
public void setTitle(String title) {
	Title = title;
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
	return "Movie [Title=" + Title + ", yearCreation=" + yearCreation + ", releaseDate=" + releaseDate + ", price="
			+ price + ", nameStudio=" + nameStudio + ", nameDirector=" + nameDirector + ", description=" + description
			+ ", nameMainActor=" + nameMainActor + "]";
}

}
