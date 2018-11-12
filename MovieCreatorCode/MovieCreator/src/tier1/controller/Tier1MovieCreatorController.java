package tier1.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

import common.Movie;
import common.Package;
import tier1.view.Tier1MovieCreatorView;

public class Tier1MovieCreatorController implements Runnable {
	private Socket serverSocket;
	private Tier1MovieCreatorView view;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;

	public Tier1MovieCreatorController(Tier1MovieCreatorView view) {
		try {
			this.view = view;
			view.show("Starting tier1 client");
			serverSocket = new Socket("localhost", 1098);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		boolean ok = true;
		while (ok) {

			view.show("Connecting to a server...");
			// Package GET = new Package("GET");
			// It's okay to create objects inside the client as long as the purpose is communication
			// We don't handle any business logic by creating the object with the strings from the user
			Movie movie = new Movie("testADD3", "2018ADD", "2019ADD", 50.00, "ADDStudio", "ADDDirector",	"ADDDescription", "ADDMainActor");

			try {
				// Read from stream : String tmp = inputStream.readUTF();
				inputStream = new DataInputStream(serverSocket.getInputStream());
				// Write into stream : outputStream.writeUTF(new String("text to send"));
				outputStream = new DataOutputStream(serverSocket.getOutputStream());
				Gson gson = new Gson();
				Package ADD = new Package("ADD", movie);
				// send to tier 2 server
				String json = gson.toJson(ADD);
				outputStream.writeUTF(json);

				// receive from tier 2 server
				String answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				view.show("package: " + request.getBody());
				// remove the next 3 lines when the controller is fully implemented so it can
				// run as long as the user wants
				ok = false;
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}