package common;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Package {
	public static final String SCHEDULEDMOVIE = "SCHEDULEDMOVIE";
	public static final String ADDROOM = "ADDROOM";
	public static final String GETROOMS = "GETROOMS";
	public static final String REMOVEROOM = "REMOVEROOM";
	public static final String GETMOVIES = "GETMOVIES";
	public static final String SENDSCHEDULE = "SENDSCHEDULE";
	public static final String GETSCHEDULE = "GETSCHEDULE";
	public static final String GETROOM = "GETROOM";
	public static final String GETMOVIE = "GETMOVIE";
	public static final String CANCELSCHEDULE = "CANCELSCHEDULE";
	
	@SerializedName("Header")
	@Expose
	private String header;
	@SerializedName("Body")
	@Expose
	private String body;
	@SerializedName("Room")
	@Expose
	private Room room;
	@SerializedName("Movie")
	@Expose
	private Movie movie;
	@SerializedName("ScheduleList")
	@Expose
	private ArrayList<ScheduledMovie> List;
	@SerializedName("ScheduledMovie")
	@Expose
	private ScheduledMovie scheduledMovie;
	@SerializedName("Schedule")
	@Expose
	private Schedule schedule;

	

	public Package(String header) {
		this.header = header;
	}

	public Package(String header, String body) {
		this.header = header;
		this.body = body;
	}
	public Package(String header, Room room) {
		this.header = header;
		this.room = room;
	}
	
	public Package(String header, Movie movie) {
		this.header = header;
		this.movie = movie;
	}
	
	public Package(String header, ScheduledMovie scheduledMovie) {
		this.header = header;
		this.scheduledMovie = scheduledMovie;
	}
	
	public Package(String header, Schedule schedule)
	{
		this.header = header;
		this.schedule = schedule;
	}
	
	public Package(String header, ArrayList<ScheduledMovie> List) {
		this.header = header;
		this.List = List;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getHeader() {
		return header;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public ScheduledMovie getScheduledMovie() {
		return scheduledMovie;
	}
	
	public Schedule getSchedule() {
		return schedule;
	}
}
