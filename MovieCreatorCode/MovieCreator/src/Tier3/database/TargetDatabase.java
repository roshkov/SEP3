/**
 * 
 */
package Tier3.database;


import Common.Movie;

/**
 * @author Claudiu
 *
 */
public interface TargetDatabase {
	public void saveMovie(Movie movie);
	public Movie[] getMovies(); 
}
