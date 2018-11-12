package tier3.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import tier3.database.DatabaseAdapter;
import tier3.view.Tier3MovieCreatorView;
import common.Package;

public class Tier3MovieCreatorThreadHandler implements Runnable {

	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private Tier3MovieCreatorView view;
	private String ip;
	private DatabaseAdapter database;

	public Tier3MovieCreatorThreadHandler(Socket clientSocket, Tier3MovieCreatorView view, DatabaseAdapter database)
			throws IOException {
		super();
		startDatabase(database);
		startView(view);
		communicateClient(clientSocket);
	}

	public void communicateClient(Socket clientSocket) {
		try {
			// Read from stream : String tmp = inputStream.readUTF();
			inputStream = new DataInputStream(clientSocket.getInputStream());
			// Write into stream : outputStream.writeUTF(new String("text to send"));
			outputStream = new DataOutputStream(clientSocket.getOutputStream());

			this.ip = clientSocket.getInetAddress().getHostAddress();
			view.show(ip + " connected");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startView(Tier3MovieCreatorView view) {
		this.view = view;
	}

	public void startDatabase(DatabaseAdapter database) {
		this.database = database;
	}

	/**
	 * This method waits for a request Package from the client then sends a reply
	 * Package back to him
	 * 
	 * @see operation
	 * @see Package
	 */
	@Override
	public void run() {
		boolean continueCommuticating = true;
		try {
			while (continueCommuticating) {
				Gson gson = new Gson();
				String line = inputStream.readUTF();
				view.show(ip + "> " + line);

				// convert from JSon
				Package request = gson.fromJson(line, Package.class);
				view.show("package: " + request.getHeader());

				Package reply = operation(request);

				// convert to JSon
				String json = gson.toJson(reply);
				outputStream.writeUTF(json);
				view.show("Server to " + ip + "> " + reply);
				if (reply.getHeader().equalsIgnoreCase("EXIT")) {
					continueCommuticating = false;
					close();
				}
			}
			view.show("Closing connection to client: " + ip);

		} catch (Exception e) {
			String message = e.getMessage();
			if (message == null) {
				message = "Connection lost";
			}
			view.show("Error for client: " + ip + " - Message: " + message);
		}
	}

	/**
	 * Method that takes the request Package then uses the model to create a reply
	 * Package depending on the request
	 * 
	 * @param request The Package received from the client
	 * @return a Package containing what the client requested
	 * @see Package
	 */

//Are we using packages or something else? What about the package class itself?
	private Package operation(Package request) {
		switch (request.getHeader()) {
		case Package.GET:
			String result = database.getMovies();
			return new Package("GET", result);
			//after you add you get a list of all movies back
		case Package.ADD:
			if(database.saveMovie(request.getMovie()))
			return new Package ("ADD", database.getMovies());
		default:
			return new Package("WRONG FORMAT");

		}

	}

	public void close() {
		try {
			inputStream.close();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
