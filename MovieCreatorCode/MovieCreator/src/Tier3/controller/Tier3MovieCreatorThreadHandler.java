package tier3.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import tier3.view.Tier3MovieCreatorView;
import common.Package;

public class Tier3MovieCreatorThreadHandler  implements Runnable {

	private Socket clientSocket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private Tier3MovieCreatorView view;
	private String ip;
	
	public Tier3MovieCreatorThreadHandler(Socket clientSocket,Tier3MovieCreatorView view) throws IOException {
		super();
		this.clientSocket = clientSocket;
		this.view = view;
		// Read from stream : String tmp = inputStream.readUTF();
		inputStream = new DataInputStream(clientSocket.getInputStream());

		// Write into stream : outputStream.writeUTF(new String("text to send"));
		outputStream = new DataOutputStream(clientSocket.getOutputStream());

		this.ip = clientSocket.getInetAddress().getHostAddress();
		view.show(ip + " connected");
	}

	/**
	 * This method waits for a request Package from the client then sends a reply Package back to him
	 * 
	 * @see operation
	 * @see Package
	 */
	@Override
	public void run() {
		boolean continueCommuticating = true;
		try {
			while (continueCommuticating) {

				String line = inputStream.readUTF();
				view.show(ip + "> " + line);

				// convert from JSon
				Gson gson = new Gson();
				Package request = gson.fromJson(line, Package.class);
				view.show("package: " + request.getText());

				Package reply = operation(request);

				// convert to JSon
				// gson = new Gson();
				String json = gson.toJson(reply);
				outputStream.writeUTF(json);
				view.show("Server to " + ip + "> " + reply);
				if (reply.getText().equalsIgnoreCase("EXIT")) {
					continueCommuticating = false;
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
	 * Method that takes the request Package then uses the model to create a reply Package depending on the request 
	 * @param request The Package received from the client
	 * @return a Package containing what the client requested
	 * @see Package
	 */
	
//Are we using packages or something else? What about the package class itself?
	private Package operation(Package request) {
		switch (request.getText()) {
		case Package.GET:
			return new Package("GOOD FORMAT");
			/*String list = controller.getMovies();
			if (list.length() <= 0)
				return new Package("NO MOVIES", list);
			return new Package(Package.GET, list);

		case Package.CREATE:
			title, yearCreation, releaseDate, price, nameStudio, nameDirector, description, nameMainActor
			controller.createMovie(request.getTitle(), request.getYearCreation(), request.getPrice(), request.getNameStudio(), request.getNameDirector(), request.getDescription(), request.getNameMainActor());
			return new Package("MOVIE CREATED");*/
			
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
