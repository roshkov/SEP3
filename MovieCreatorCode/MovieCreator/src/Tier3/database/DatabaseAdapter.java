package Tier3.database;

import java.sql.SQLException;
import java.util.ArrayList;

import Common.Movie;
import utility.persistence.MyDatabase;

/**
 * @author Claudiu
 *
 */
public class DatabaseAdapter implements TargetDatabase {
	private MyDatabase database;
	public DatabaseAdapter(String driver, String url, String user, String pw) {
		try {
			database = new MyDatabase(driver, url, user, pw);
			/*
			 * DatabaseAdapter database = new DatabaseAdapter("org.postgresql.Driver",
			 * "jdbc:postgresql://localhost:5432/Zinema", "postgres", "2308");
			 */
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	// Method to save a movie in the database
	@Override
	public void saveMovie(Movie movie) {
		String title = movie.getTitle();
		String yearCreation = movie.getYearCreation();
		String releaseDate = movie.getReleaseDate();
		String price = "" + movie.getPrice();
		String nameStudio = movie.getNameStudio();
		String nameDirector = movie.getNameDirector();
		String description = movie.getDescription();
		String nameMainActor = movie.getNameMainActor();

		String statement = "INSERT INTO \"Movie\" VALUES ('" + title + "', '" + yearCreation + "', '" + releaseDate + "', "
				+ price + ", '" + nameStudio + "', '" + nameDirector + "', '" + description + "', '" + nameMainActor + "');";

		try {
			database.update(statement, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// Method to get an array of the movies stored in the database
	@Override
	public Movie[] getMovies() {
		String statement = "SELECT * FROM \"Movie\";";
		ArrayList<Object[]> results = null;
		try {
			results = database.query(statement, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Movie[] Movies = new Movie[results.size()];
		for (int i = 0; i < results.size(); i++) {
			Object[] row = results.get(i);
			String title = row[0].toString();
			String yearCreation = row[1].toString();
			String releaseDate = row[2].toString();
			double price = Double.parseDouble(row[3].toString());
			String nameStudio = row[4].toString();
			String nameDirector = row[5].toString();
			String description = row[6].toString();
			String nameMainActor = row[7].toString();
			Movies[i] = new Movie(title, yearCreation, releaseDate, price, nameStudio, nameDirector, description, nameMainActor);
		}
		return Movies;
	}
}
