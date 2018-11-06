package tier2.controller;

import java.rmi.RemoteException;

import Common.Movie;
import Common.Tier2MovieCreatorControllerInterface;
import Common.Tier3MovieCreatorControllerInterface;
import tier2.model.Tier2MovieCreatorModel;
import tier2.view.Tier2MovieCreatorView;

public class Tier2MovieCreatorController implements Tier2MovieCreatorControllerInterface {
	
	private Tier2MovieCreatorModel model;
	private Tier2MovieCreatorView view;
	private Tier3MovieCreatorControllerInterface tier3Controller;
	
	//How does the server work? where do we start it ?
	
	public Tier2MovieCreatorController(Tier2MovieCreatorModel model, Tier2MovieCreatorView view, Tier3MovieCreatorControllerInterface controller)
	{
		this.model = model;
		this.view = view;
		this.tier3Controller = controller;
	}
	
	public void execute(int choice)
	{
		//Choices done for testing
		switch (choice)
		{
		case 0:
			System.exit(1);
			break;
		case 1:
			//Should receive this stuff from tier 1
			view.show("Creating movie...");
			String title = view.get("Title: ");
			String yearCreation = view.get("Creation year: ");
			String releaseDate = view.get("Release Date: ");
			double price = Double.parseDouble(view.get("Price: "));
			String nameStudio = view.get("Name of studio: ");
			String nameDirector = view.get("Director name: ");
			String description = view.get("Description: ");
			String nameMainActor = view.get("Main Actor Name: ");
			createMovie(title, yearCreation, releaseDate, price, nameStudio, nameDirector, description, nameMainActor);
			break;
		case 2:
			view.show("Getting movies...");
			view.show(getMovies());
			break;
		default:
			view.show("Wake up meow meow, nah he deed");
			break;
		}
	}
	
	

	
	//Not sure what happens when the server creates a movie or retrieves the movies
	@Override
	public void createMovie(String title, String yearCreation, String releaseDate, double price, String nameStudio,
			String nameDirector, String description, String nameMainActor) {
		Movie movie = new Movie(title, yearCreation, releaseDate, price, nameStudio, nameDirector, description, nameMainActor);
		try {
			tier3Controller.saveMovie(movie);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Should this return a string like in the astah? 
	//Its used by the threadhandler to get the movies, can't be used as string
	//Should tier 2 server be the one to convert the movie objects to anything or keep them as they are?
	@Override
	public String getMovies() {
		String movies = "";
		// TODO Auto-generated method stub
		try {
			movies = tier3Controller.getMovies().toString();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}

}
