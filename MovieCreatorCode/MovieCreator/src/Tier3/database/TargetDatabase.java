/**
 * 
 */
package Tier3.database;


import common.Movie;

/**
 * @author Claudiu
 *
 */
public interface TargetDatabase {
	public boolean saveMovie(Movie movie);
	public String getMovies(); 
}
