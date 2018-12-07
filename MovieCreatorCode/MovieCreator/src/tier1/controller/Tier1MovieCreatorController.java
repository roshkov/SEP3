package tier1.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.Init;
import common.Movie;
import common.Package;
import tier1.view.Tier1MovieCreatorView;

public class Tier1MovieCreatorController {

	private Socket serverSocket;
	private Tier1MovieCreatorView view;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private Gson gson;

	// constructor
	public Tier1MovieCreatorController(Tier1MovieCreatorView view) {
		try {
			this.view = view;
			view.show("Starting tier1 client");
			serverSocket = new Socket(Init.getInstance().getIp(), Init.getInstance().getPort());

			// Read from stream : String tmp = inputStream.readUTF();
			inputStream = new DataInputStream(serverSocket.getInputStream());
			// Write into stream : outputStream.writeUTF(new String("text to send"));
			outputStream = new DataOutputStream(serverSocket.getOutputStream());

			Gson gson = new Gson();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void execute(int choice) {
		// Choices done for testing
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeNulls();
		Gson gson = gsonBuilder.create();

		switch (choice) {
		case 0:
			System.exit(1);
			break;

		case 1: // case 1 should create a movie object and send it to tier2

			view.show("Creating movie...\n");
			boolean ok = false;
			Movie movie = new Movie("initialized");
			while (ok == false) {
				String title = view.get("Title: ");
				String yearCreation = view.get("Creation year: ");
				String releaseDate = view.get("Release Date [DD/MM/YYYY] :");
				// TODO Implement a class to check date format
				String price = view.get("Price: ");
				String nameStudio = view.get("Name of studio: ");
				String nameDirector = view.get("Director name: ");
				String description = view.get("Description: ");
				String nameMainActor = view.get("Main Actor Name: ");
				Movie verifyMovie = new Movie(title, null, null, 0, nameStudio, nameDirector, description, nameMainActor);

				Package VERIFY = new Package("VERIFY", yearCreation, releaseDate, price, verifyMovie);

				// send to tier 2 server
				String json = gson.toJson(VERIFY);

				try {
					// sending 'VERIFY' package to tier 2 in json format
					outputStream.writeUTF(json);
					String answer = inputStream.readUTF();
					Package serverAnswer = gson.fromJson(answer, Package.class);

					// check what the server said about the input
					if (serverAnswer.getHeader().equals("CREATIONYEAR")) {
						view.show("Please enter only digits for this field");
					} else if (serverAnswer.getHeader().equals("RELEASEDATE")) {
						view.show("Please enter the date in this exact format: [DD/MM/YYYY]");
					} else if (serverAnswer.getHeader().equals("PRICE")) {
						view.show("Please enter only numbers in this field. Do not leave an empty field \n e.g.: 12.3");
					} else if (serverAnswer.getHeader().equals("OK"))
					{
						ok = true;
						movie.setTitle(title);
						movie.setYearCreation(yearCreation);
						movie.setReleaseDate(releaseDate);
						movie.setPrice(Double.parseDouble(price));
						movie.setNameStudio(nameStudio);
						movie.setNameDirector(nameDirector);
						movie.setDescription(description);
						movie.setNameMainActor(nameMainActor);
					}

				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			
			view.show("Movie created! \n");

			Package ADD = new Package("ADD", movie);

			// send to tier 2 server
			String json1 = gson.toJson(ADD);

			try {
				// sending 'ADD' package to tier 2 in json format
				outputStream.writeUTF(json1);
				String answer = inputStream.readUTF();
				Package serverAnswer = gson.fromJson(answer, Package.class);
				view.show("package: " + serverAnswer.getBody());
			} catch (IOException e) {

				e.printStackTrace();
			}
			break;

		case 2: // case 2 should get movies
			view.show("Getting movies...");
			String answer;

			try {
				// receive from tier 2 server
				Package GETMOVIES = new Package("GETMOVIES");

				// send to tier 2 server
				String jsonGET = gson.toJson(GETMOVIES);
				outputStream.writeUTF(jsonGET);
				answer = inputStream.readUTF();
				Package serverAnswer = gson.fromJson(answer, Package.class);
				view.show("package: " + serverAnswer.getBody());

			} catch (IOException e) {

				e.printStackTrace();
			}
			break;

		default:
			view.show("INVALID INPUT");
			break;
		}

	}

}