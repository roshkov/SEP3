package tier3.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.Movie;
import utility.persistence.MyDatabase;

/**
 * @author Claudiu
 *
 */
public class DatabaseAdapter implements TargetDatabase {
	Connection conn;

	public DatabaseAdapter(String url, String user, String pw) {
		try {
			//Class.forName checks if the jar is put
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url, user, pw);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Method to save a movie in the database
	@Override
	public boolean saveMovie(Movie movie) {
		String title = movie.getTitle();
		String yearCreation = movie.getYearCreation();
		String releaseDate = movie.getReleaseDate();
		String price = "" + movie.getPrice();
		String nameStudio = movie.getNameStudio();
		String nameDirector = movie.getNameDirector();
		String description = movie.getDescription();
		String nameMainActor = movie.getNameMainActor();

		String queryString = "INSERT INTO Movie (title,yearCreation,releaseDate,price,nameStudio,nameDirector,description,nameMainActor) VALUES ('"
				+ title + "', '" + yearCreation + "', '" + releaseDate + "', " + price + ", '" + nameStudio + "', '"
				+ nameDirector + "', '" + description + "', '" + nameMainActor + "');";

		try {
			Statement statement = conn.createStatement();
			//executeQuery throws error because there is no set result returned. It's not a query
			statement.execute(queryString);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Method to get an array of the movies stored in the database
	@Override
	public String getMovies() {
		String result = "";
		Statement statement;
		try {
			statement = conn.createStatement();
			String queryString = "SELECT * FROM Movie";
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				result = result + "Title: " + rs.getString("title") + " Year Of creation: "
						+ rs.getString("yearCreation") + " Release Date: " + rs.getString("releaseDate") + " Price: "
						+ rs.getString("price") + " Name of the Studio: " + rs.getString("nameStudio")
						+ " Name of the Director: " + rs.getString("nameDirector") + " Description: "
						+ rs.getString("description") + " Name of Main Actor: " + rs.getString("nameMainActor")
						+ " Rented: " + rs.getBoolean("rented") + "\n";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
