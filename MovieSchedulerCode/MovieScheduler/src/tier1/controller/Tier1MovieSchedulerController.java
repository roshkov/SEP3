package tier1.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import common.Movie;
import common.Package;
import common.Room;
import common.ScheduledMovie;
import tier1.view.Tier1MovieSchedulerView;

public class Tier1MovieSchedulerController {
	private Socket serverSocket;
	private Tier1MovieSchedulerView view;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private Gson gson;

	// constructor
	public Tier1MovieSchedulerController(Tier1MovieSchedulerView view) {
		try {
			this.view = view;
			serverSocket = new Socket("localhost", 1098);

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
		gson = new Gson();

		switch (choice) {
		case 0:
			System.exit(1);
			break;

		case 1:
			// case 1 should get a list of rooms from t2, display it (does it receive an
			// array list of rooms?), select Id,
			// display list of days of the week, select day, display standardized time,
			// select time, get a list of rented movies from
			// t2, display list of rented movies, select the id of a rented movie, create
			// scheduled movie with info, send scheduled movie
			// then ask if continue (repeat) or finish (SENDSCHEDULE package to let t2 know
			// we're done) or CANCEL (send CANCELSCHEDULE package
			// to let t2 know we reset the schedule).
			Package GETROOMS = new Package("GETROOMS");
			// send package to tier 2 server
			String json = gson.toJson(GETROOMS);
			try {
				// sending 'GETROOMS' package to tier 2 in json format
				outputStream.writeUTF(json);
				String answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				view.showRooms(request.getBody());
			} catch (IOException e) {

				e.printStackTrace();
			}
			// Send request for receiving rented movies
			Package GETMOVIES = new Package("GETMOVIES");
			String json1 = gson.toJson(GETMOVIES);
			try {
				// sending 'GETMOVIES' package to tier 2 in json format
				outputStream.writeUTF(json1);
				String answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				view.showMovies(request.getBody());
			} catch (IOException e) {

				e.printStackTrace();
			}
			view.showTime(
					"Available days: Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday \n Select time: 10:00, 13:00, 16:00, 20:00");
			break;
		case 2:
			// finishes schedule, sends package
			Package SENDSCHEDULE = new Package("SENDSCHEDULE");
			String json9 = gson.toJson(SENDSCHEDULE);
			try {
				// sending 'SENDSCHEDULE' package to tier 2 in json format
				outputStream.writeUTF(json9);
				String answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				view.showSchedule(request.getBody());
			} catch (IOException e) {

				e.printStackTrace();
			}
			break;
		case 3:
			// cancel schedule, send package
			Package CANCELSCHEDULE = new Package("CANCELSCHEDULE");
			String json10 = gson.toJson(CANCELSCHEDULE);
			try {
				// sending 'CANCELSCHEDULE' package to tier 2 in json format
				outputStream.writeUTF(json10);
				String answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				view.showSchedule(request.getBody());
			} catch (IOException e) {

				e.printStackTrace();
			}
			break;

		case 4: // get all rooms
			view.showRooms("Getting rooms...\n");
			Package GETROOMS1 = new Package("GETROOMS");

			// send to tier 2 server
			String json4 = gson.toJson(GETROOMS1);
			try {
				// sending 'GETROOMS' package to tier 2 in json format
				outputStream.writeUTF(json4);
				String answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				view.showRooms("package: " + request.getBody());
			} catch (IOException e) {

				e.printStackTrace();
			}
			break;

		case 5: // get current schedule, empty package request to get schedule
			view.showSchedule("Getting schedule...\n");
			Package GETSCHEDULE = new Package("GETSCHEDULE");

			// send to tier 2 server
			String json6 = gson.toJson(GETSCHEDULE);
			view.showSchedule("Request sent.");
			try {
				// sending 'GETSCHEDULE' package to tier 2 in json format
				outputStream.writeUTF(json6);
				String answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				view.showSchedule("package: " + request.getBody());
			} catch (IOException e) {

				e.printStackTrace();
			}
			break;

		default:
			view.showSchedule("INVALID INPUT");
			break;
		}

	}

	public void deleteRoom(String ID) {
		Package REMOVEROOM = new Package("REMOVEROOM", ID);

		// send to tier 2 server
		String json5 = gson.toJson(REMOVEROOM);
		view.showRooms("Id sent.");
		try {
			// sending 'REMOVEROOM' package to tier 2 in json format
			outputStream.writeUTF(json5);
			String answer = inputStream.readUTF();
			Package request = gson.fromJson(answer, Package.class);
			view.showRooms("package: " + request.getBody());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void addRoom(String size, String description) {
		
		Room room = new Room(Integer.parseInt(size) , description);
		Package ADDROOM = new Package("ADDROOM", room);

		// send to tier 2 server
		String json3 = gson.toJson(ADDROOM);

		try {
			// sending 'ADDROOM' package to tier 2 in json format
			outputStream.writeUTF(json3);
			String answer = inputStream.readUTF();
			Package request = gson.fromJson(answer, Package.class);
			view.showRooms(request.getBody());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void addScheduledMovie(String roomId, String movieId, String day, String time) {
		Room room = null;
		// After selecting id, send package with it to t2
		Package GETROOM = new Package("GETROOM", roomId);
		String json7 = gson.toJson(GETROOM);
		try {
			// sending 'GETROOM' package to tier 2 in json format
			outputStream.writeUTF(json7);
			String answer = inputStream.readUTF();
			Package request = gson.fromJson(answer, Package.class);
			view.showSchedule("package: " + request.getRoom().toString());
			room = request.getRoom();
		} catch (IOException e) {

			e.printStackTrace();
		}
		Movie movie = null;
		// After selecting id, send package with it to t2
		Package GETMOVIE = new Package("GETMOVIE", movieId);
		String json8 = gson.toJson(GETMOVIE);
		try {
			// sending 'GETMOVIE' package to tier 2 in json format
			outputStream.writeUTF(json8);
			String answer = inputStream.readUTF();
			Package request = gson.fromJson(answer, Package.class);
			view.showSchedule("package: " + request.getMovie().toString());
			movie = request.getMovie();
		} catch (IOException e) {

			e.printStackTrace();
		}
		ScheduledMovie scheduledMovie = new ScheduledMovie(time, day, movie, room);

		Package SCHEDULEDMOVIE = new Package("SCHEDULEDMOVIE", scheduledMovie);
		String json2 = gson.toJson(SCHEDULEDMOVIE);
		try {
			// sending 'SCHEDULEDMOVIE' package to tier 2 in json format
			outputStream.writeUTF(json2);
			String answer = inputStream.readUTF();
			Package request = gson.fromJson(answer, Package.class);
			view.showSchedule("package: " + request.getBody());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}