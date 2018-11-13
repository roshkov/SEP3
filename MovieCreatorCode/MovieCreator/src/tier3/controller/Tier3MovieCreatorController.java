package Tier3.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Tier3.database.DatabaseAdapter;
import Tier3.view.Tier3MovieCreatorView;

public class Tier3MovieCreatorController implements Runnable {
	private ServerSocket welcomeSocket;
	private Tier3MovieCreatorView view;
	private DatabaseAdapter database;

	public Tier3MovieCreatorController(Tier3MovieCreatorView view, int port) {
		this.view = view;
		startServer(port);
		//Remove hardcoding
		database = new DatabaseAdapter("org.postgresql.Driver",
				"jdbc:postgresql://localhost:5432/Zinema", "postgres", "postgres");
	}

	public void startServer(int port) {
		try {
			view.show("Starting tier3 server");
			welcomeSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			view.show("Waiting for a client...");
			try {
				Socket socket = welcomeSocket.accept();
				Tier3MovieCreatorThreadHandler c;
				c = new Tier3MovieCreatorThreadHandler(socket, view, database);
				Thread t = new Thread(c);
				t.start();
				view.show("Client connected");
			} catch (IOException e) {
				view.show("Error in server. Message: " + e.getMessage());
			}
		}
	}

}
