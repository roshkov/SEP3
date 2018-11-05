package Tier3.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Common.Movie;
import Common.Tier3MovieCreatorControllerInterface;
import Tier3.database.DatabaseAdapter;
import Tier3.database.TargetDatabase;
import Tier3.view.Tier3MovieCreatorView;


public class Tier3MovieCreatorController extends UnicastRemoteObject implements Tier3MovieCreatorControllerInterface {
	
	private static final long serialVersionUID = 1L;
	private TargetDatabase database;
	private Tier3MovieCreatorView view;
	
	//Method to start the controller and the database
	public Tier3MovieCreatorController() throws RemoteException{
		database = new DatabaseAdapter("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/Zinema", "postgres",
				"2308");
	}
	
	//Method to set the created view
	public void setView(Tier3MovieCreatorView view)
	{
		this.view = view;
		view.show("The Database has been started");
	}
	
	//Method to save a movie to the database
	@Override
	public void saveMovie(Movie movie) throws RemoteException {
		database.saveMovie(movie);
		view.show("The movie " + movie.getTitle() + " has been added");
	}

	//Method to get an array of movies from the database
	@Override
	public Movie[] getMovies() throws RemoteException {
		view.show("Getting all movies... ");
		return database.getMovies();
	}

}
