package Common;

public interface Tier2MovieCreatorControllerInterface {
	
	public void createMovie (String title, String yearCreation, String releaseDate, double price, String nameStudio,
	String nameDirector, String description, String nameMainActor);
	public String getMovies();
	
	
}
