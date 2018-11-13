package tier3.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import tier2.controller.Tier2MovieCreatorServer;
import tier3.database.DatabaseAdapter;
import tier3.view.Tier3MovieCreatorView;

public class Tier3MovieCreatorController {

	private Tier3MovieCreatorView view;
	private Tier3MovieCreatorServer server;
	private DatabaseAdapter database;

	public Tier3MovieCreatorController(Tier3MovieCreatorView view, int port) {
		this.view = view;
		
		try {
			this.server = new Tier3MovieCreatorServer(port, this);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Thread t = new Thread(this.server);
		t.start();

		//Remove hardcoding
		//database = new DatabaseAdapter("org.postgresql.Driver","jdbc:postgresql://localhost:5432/Zinema", "postgres", "2308");
		database = new DatabaseAdapter("jdbc:sqlserver://localhost:1433;databaseName=master", "sa", "root");
	}
	
	public Tier3MovieCreatorView getView() {
		return view;
	}
	
	public DatabaseAdapter getDatabase() {
		return database;
	}
}
