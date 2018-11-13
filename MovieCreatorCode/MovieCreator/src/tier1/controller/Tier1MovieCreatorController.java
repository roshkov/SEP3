package tier1.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.google.gson.Gson;

import common.Movie;
import common.Package;
import tier1.view.Tier1MovieCreatorView;
import java.util.Date;

public class Tier1MovieCreatorController {
	private Socket serverSocket;
	private Tier1MovieCreatorView view;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private Gson gson;

//constructor	
	public Tier1MovieCreatorController(Tier1MovieCreatorView view) {
		try {
			this.view = view;
			view.show("Starting tier1 client. Boobies!");
			serverSocket = new Socket("localhost", 1098);

			// Read from stream : String tmp = inputStream.readUTF();
			inputStream = new DataInputStream(serverSocket.getInputStream());
			// Write into stream : outputStream.writeUTF(new String("text to send"));
			outputStream = new DataOutputStream(serverSocket.getOutputStream());

			Gson gson = new Gson();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void execute(int choice) {
		// Choices done for testing
		gson = new Gson();

		switch (choice) {
		case 0:
			System.exit(1);
			break;

		case 1: // case 1 should create a movie object and send it to tier2

			view.show("Creating movie...\n");
			String title = view.get("Title: ");
			String yearCreation = view.get("Creation year: ");
			String releaseDate = view.get("Release Date [DD/MM/YYYY] :");
			// ToDo: Implement a class to check date format
			double price = Double.parseDouble(view.get("Price: "));
			while (price < 0) {
				view.get("Wrong price format try again! /nPrice: ");
			}
			String nameStudio = view.get("Name of studio: ");
			String nameDirector = view.get("Director name: ");
			String description = view.get("Description: ");
			String nameMainActor = view.get("Main Actor Name: ");
			Movie movie = new Movie(title, yearCreation, releaseDate, price, nameStudio, nameDirector, description,
					nameMainActor);

			view.show("Movie created! \n");

			Package ADD = new Package("ADD", movie);

			// send to tier 2 server
			String json = gson.toJson(ADD);

			try {
				// sending 'ADD' package to tier 2 in json format
				outputStream.writeUTF(json);
				String answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				view.show("package: " + request.getBody());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case 2: // case 2 should get movies... somehow
			view.show("Getting movies...");
			String answer;

			try {
				// receive from tier 2 server
				Package GET = new Package("GET");

				// send to tier 2 server
				String jsonGET = gson.toJson(GET);
				outputStream.writeUTF(jsonGET);
				answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				view.show("package: " + request.getBody());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		default:
			view.show("what the hell?");
			break;
		}

	}

}