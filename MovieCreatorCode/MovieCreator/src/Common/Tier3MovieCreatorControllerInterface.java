/**
 * 
 */
package Common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Claudiu
 *
 */
public interface Tier3MovieCreatorControllerInterface extends Remote{
	public static final String SERVICE_NAME = "MovieCreatorDatabaseServer";
public void saveMovie(Movie movie) throws RemoteException;
public Movie[] getMovies() throws RemoteException;
}
