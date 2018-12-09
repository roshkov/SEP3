package tier2.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.Init;
import common.Package;
import common.Schedule;
import tier2.view.Tier2MovieSchedulerView;

/**
 * The class that handles the response to the client's requests
 * 
 * @author Claudiu
 *
 */
public class Tier2MovieSchedulerThreadHandler implements Runnable {

	/**
	 * Input stream from the client
	 */
	private DataInputStream inputStream;
	/**
	 * Output stream to the client
	 */
	private DataOutputStream outputStream;
	/**
	 * Server socket used to initialize the stream to the database when needed
	 */
	private Socket serverSocket;
	/**
	 * The interface for the view used
	 */
	private Tier2MovieSchedulerView view;
	/**
	 * ip of the client
	 */
	private String ip;
	/**
	 * The schedule class that holds the list of scheduled movies
	 */
	private Schedule schedule;
	/**
	 * A list of defaults Day values
	 */
	private ArrayList<String> days;
	/**
	 * A list of defaults Time values
	 */
	private ArrayList<String> times;
	
	private Socket clientSocket;

	/**
	 * Establish the streams and poppulate the array list with the default values in
	 * the days and times arraylists
	 * 
	 * @param clientSocket
	 * @param view
	 * @throws IOException
	 */
	public Tier2MovieSchedulerThreadHandler(Socket clientSocket, Tier2MovieSchedulerView view) throws IOException {
		super();

		addDays();

		addTimes();
		
		this.clientSocket = clientSocket;

		schedule = new Schedule();

		// Read from client stream
		inputStream = new DataInputStream(clientSocket.getInputStream());

		// Write into client stream
		outputStream = new DataOutputStream(clientSocket.getOutputStream());

		this.view = view;

		this.ip = clientSocket.getInetAddress().getHostAddress();
		view.show(ip + " connected");
	}

	/**
	 * Method to populate the Days arraylist with days of the week
	 */
	public void addDays() {
		days = new ArrayList<String>();
		days.add("Monday");
		days.add("Tuesday");
		days.add("Wednesday");
		days.add("Thursday");
		days.add("Friday");
		days.add("Saturday");
		days.add("Sunday");
		days.add("monday");
		days.add("tuesday");
		days.add("wednesday");
		days.add("thursday");
		days.add("friday");
		days.add("saturday");
		days.add("sunday");
	}

	/**
	 * Method to populate the Times arraylist with times available for scheduling
	 */
	public void addTimes() {
		times = new ArrayList<String>();
		times.add("10:00");
		times.add("13:00");
		times.add("16:00");
		times.add("20:00");
	}

	@Override
	public void run() {
		boolean continueCommuticating = true;
		try {
			while (continueCommuticating) {

//				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF8"));
//				String line = in.readLine();
				String line = inputStream.readUTF();
				view.show(ip + "> " + line);

				// convert from JSon
				// getting request from client
				GsonBuilder gsonBuilder = new GsonBuilder();
				gsonBuilder.serializeNulls();
				Gson gson = gsonBuilder.create();
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
	 * @param request The Package received from the client
	 * @return a Package containing what the client requested
	 * @throws IOException
	 * @throws UnknownHostException
	 * @see Package
	 */
	private Package operation(Package request) throws IOException {

		DataInputStream inputStream;
		DataOutputStream outputStream;
		BufferedReader in;
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeNulls();
		Gson gson = gsonBuilder.create();
		String json = "";
		String line = "";
		Package replyFromServer;

		try {
			view.show("Connecting to tier3 server");
			serverSocket = new Socket(Init.getInstance().getIpDb(), Init.getInstance().getPortDb());
		} catch (IOException e) {
			view.show("Database offline, couldn't connect to server");
			e.printStackTrace();
		}

		switch (request.getHeader()) {
		case Package.GETROOMS:
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

			if (replyFromServer.getBody() != null)
				return replyFromServer;
			else {
				replyFromServer.setBody("The System couldn't find the rooms");
				return replyFromServer;
			}

		case Package.GETROOM:
			try {
				Integer.parseInt(request.getBody());
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
				view.show(ip + "> " + line);

				// convert from JSon
				replyFromServer = gson.fromJson(line, Package.class);
				view.show("package: " + replyFromServer.getBody());

				// Close the streams when you are done
				inputStream.close();
				outputStream.close();

			} catch (Exception e) {
				e.printStackTrace();
				replyFromServer = new Package("INVALID FORMAT", "You need to input a number");
				return replyFromServer;
			}
			if (replyFromServer.getBody() != null)
				return replyFromServer;
			else {
				replyFromServer.setBody("The System couldn't find the room");
				return replyFromServer;
			}

		case Package.GETRENTEDMOVIES:
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

			if (replyFromServer.getBody() != null)
				return replyFromServer;
			else {
				replyFromServer.setBody("The System couldn't find the rented movies");
				return replyFromServer;
			}

		case Package.GETMOVIE:
			try {
				Integer.parseInt(request.getBody());
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
			} catch (Exception e) {
				e.printStackTrace();
				replyFromServer = new Package("INVALID FORMAT", "You need to input a number");
				return replyFromServer;
			}

			if (replyFromServer.getBody() != null)
				return replyFromServer;
			else {
				replyFromServer.setBody("The System couldn't find the movie");
				return replyFromServer;
			}

		case Package.SCHEDULEDMOVIE:
			// if the room/movie is null it means that the user inputed something else
			// besides a number or a number not present in the list
			if (request.getScheduledMovie().getRoom() == null)
				return new Package("401",
						"Wrong ID/Wrong format Inputted(Must be a number present in the list written with digits)\n ");
			if (request.getScheduledMovie().getMovie() == null)
				return new Package("401",
						"Wrong ID/Wrong format Inputted(Must be a number present in the list written with digits)\n ");
			// if the system can find the day or time present in the list, it sends to the
			// user he inputted the wrong day and/or time
			if (!days.contains(request.getScheduledMovie().getDay()))
				return new Package("401", "Wrong Day Inputted");
			if (!times.contains(request.getScheduledMovie().getTime()))
				return new Package("401", "Wrong Time Inputted");
			schedule.addScheduledMovie(request.getScheduledMovie());

			return new Package("200", "ScheduleSent");

		case Package.SENDSCHEDULE:
			// Read from database server stream
			inputStream = new DataInputStream(serverSocket.getInputStream());

			// Write into database server stream
			outputStream = new DataOutputStream(serverSocket.getOutputStream());

			Package requestT3 = new Package("SENDSCHEDULE", schedule.getList());

			// sending request to tier 3 server
			json = gson.toJson(requestT3);
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

			if (replyFromServer.getBody() != null)
				return replyFromServer;
			else {
				replyFromServer.setBody("The System couldn't find the schedule");
				return replyFromServer;
			}

		case Package.CANCELSCHEDULE:

			// FIXED: Setting the schedule to null makes it unusable
			// This way we can still use it after it has been recreated
			schedule = new Schedule();

			return new Package("200", "ScheduleCanceled");

		case Package.ADDROOM:
			try {
				// We check if we can parse it into an int so we can check if an integer was
				// inputted by the user
				int size = Integer.parseInt(request.getBody());
				request.getRoom().setSize(size);
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
			} catch (Exception e) {
				e.printStackTrace();
				replyFromServer = new Package("INVALID FORMAT", "You need to input a number");
				return replyFromServer;
			}
			return replyFromServer;

		case Package.REMOVEROOM:
			try {
				// We check if we can parse it into an int even if the int is not used so we can
				// check if an integer was inputted by the user
				Integer.parseInt(request.getBody());
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
			} catch (Exception e) {
				e.printStackTrace();
				replyFromServer = new Package("INVALID FORMAT", "You need to input a number in the ID field");
				return replyFromServer;
			}
			if (replyFromServer.getBody() != null)
				return replyFromServer;
			else {
				replyFromServer.setBody("The System couldn't find the room");
				return replyFromServer;
			}

		case Package.GETSCHEDULE:
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

			if (replyFromServer.getBody() != null)
				return replyFromServer;
			else {
				replyFromServer.setBody("The System couldn't find the schedule");
				return replyFromServer;
			}
		case Package.GETALLSCHEDULE:
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

			// Might throw an error change the replyFromServer.getList() part if you do get
			// one
			view.show("package: " + replyFromServer.getList().toString());

			// Close the streams when you are done
			inputStream.close();
			outputStream.close();

			// If it has error here, just remove the if/else
			if (replyFromServer.getList() != null)
				return replyFromServer;
			else {
				replyFromServer.setBody("The System couldn't find the schedule");
				return replyFromServer;
			}
		case Package.UPDATESCHEDULE:
			// Read from database server stream
			inputStream = new DataInputStream(serverSocket.getInputStream());

			// Write into database server stream
			outputStream = new DataOutputStream(serverSocket.getOutputStream());
			
			//If there any issues use the request, change the header into sendschedule and send it further on
			Package updateT3 = new Package("SENDSCHEDULE", request.getList());

			// sending request to tier 3 server
			json = gson.toJson(updateT3);
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
			
			//Remove if/else if you have issues
			if (replyFromServer.getList() != null)
				return replyFromServer;
			else {
				replyFromServer.setBody("The System couldn't find the schedule");
				return replyFromServer;
			}
		default:
			return new Package("WRONG FORMAT");
		}
	}

}
