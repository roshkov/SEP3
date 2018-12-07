package tier2.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.Package;
import tier2.view.Tier2MovieCreatorView;

public class Tier2MovieCreatorThreadHandler implements Runnable {

	private Socket clientSocket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private Socket serverSocket;
	private Tier2MovieCreatorView view;
	private String ip;

	public Tier2MovieCreatorThreadHandler(Socket clientSocket, Tier2MovieCreatorView view) throws IOException {
		super();
		// Connecting to client socket
		this.clientSocket = clientSocket;

		// Read from client stream
		inputStream = new DataInputStream(clientSocket.getInputStream());

		// Write into client stream
		outputStream = new DataOutputStream(clientSocket.getOutputStream());

		this.view = view;

		this.ip = clientSocket.getInetAddress().getHostAddress();
		view.show(ip + " connected");
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

				String line = inputStream.readUTF();
				view.show(ip + "> " + line);

				// convert from JSon
				// getting request from client
				Gson gson = new Gson();
				gson.serializeNulls();
				System.out.println(line);
				Package request = gson.fromJson(line, Package.class);
				view.show("package: " + request.getHeader());

				// creating reply by communicating with tier 3 server
				Package reply = operation(request);

				// convert to JSon
				// sending reply to client
				String json = gson.toJson(reply);
				outputStream.writeUTF(json);
				view.show("Server to " + ip + "> " + reply);
				if (reply.getHeader().equalsIgnoreCase("EXIT")) {
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
	 * Method that takes the request Package then uses the model to create a reply
	 * Package depending on the request
	 * 
	 * @param request
	 *            The Package received from the client
	 * @return a Package containing what the client requested
	 * @throws IOException
	 * @throws UnknownHostException
	 * @see Package
	 */
	private Package operation(Package request) throws UnknownHostException, IOException {

		DataInputStream inputStream;
		DataOutputStream outputStream;
		BufferedReader in;
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeNulls();
		Gson gson = gsonBuilder.create();
		String json = "";
		String line = "";
		Package replyFromServer;
		Package requestToServer;
		
		try {
			view.show("Connecting to tier3 server");
			serverSocket = new Socket("localhost", 1097);
		} catch (IOException e) {
			view.show("Database offline, couldn't connect to server");
			e.printStackTrace();
		}

		switch (request.getHeader()) {
		case Package.GETMOVIES:
			// Read from database server stream
			inputStream = new DataInputStream(serverSocket.getInputStream());

			// Write into database server stream
			outputStream = new DataOutputStream(serverSocket.getOutputStream());
			// sending request to tier 3 server

			json = gson.toJson(request);
			outputStream.writeUTF(json);

			// getting reply from tier 3 server
			// Makes sure the message is read in UTF8
			in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream(), "UTF8"));
			line = in.readLine();
			// line = inputStream.readUTF();
			view.show(ip + "> " + line);

			// convert from JSon

			replyFromServer = gson.fromJson(line, Package.class);
			view.show("package: " + replyFromServer.getBody());
			// Close the streams when you are done
			inputStream.close();
			outputStream.close();

			return replyFromServer;

		case Package.ADD:
			// Read from server stream
			inputStream = new DataInputStream(serverSocket.getInputStream());

			// Write into server stream
			outputStream = new DataOutputStream(serverSocket.getOutputStream());
			// sending request to tier 3 server
			requestToServer = new Package("ADD", request.getMovie());
			json = gson.toJson(requestToServer);
			outputStream.writeUTF(json);

			// getting reply from tier 3 server
			// Makes sure the message is read in UTF8
			in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream(), "UTF8"));
			line = in.readLine();
			view.show(ip + "> " + line);

			// convert from JSon
			replyFromServer = gson.fromJson(line, Package.class);
			view.show("package: " + replyFromServer.getBody());
			
			// Close the streams when you are done
			inputStream.close();
			outputStream.close();
			return replyFromServer;

		case Package.VERIFY:
			//verifying the year of creation
			try
			{
				Integer.parseInt(request.getYearCreation());
			}
			catch(NumberFormatException e)
			{
				view.show("Creation year invalid");
				e.printStackTrace();
				return new Package("CREATIONYEAR", "NumberFormatException caught");
			}
			
			//verifying the release date
			try
			{
				String[] date = request.getReleaseDate().split("/");
				for (String element : date)
				{
					Integer.parseInt(element);
				}
			}
			catch (NumberFormatException e)
			{
				view.show("Date invalid");
				e.printStackTrace();
				return new Package("RELEASEDATE", "NumberFormatException caught");
			}
			//verifying the price field
			try {
				Double testPrice = Double.parseDouble(request.getPrice());
			}
			catch (NumberFormatException e)
			{
				view.show("Price invalid");
				e.printStackTrace();
				return new Package("PRICE", "NumberFormatException caught");
			}
			catch (NullPointerException e)
			{
				view.show("Price was empty string");
				e.printStackTrace();
				return new Package("PRICE", "NullPointerException caught -- empty string");
			}
			
			return new Package("OK", "Valid input");
			
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
