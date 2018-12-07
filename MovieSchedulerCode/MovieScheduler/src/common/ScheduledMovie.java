package common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduledMovie {
	@SerializedName("Seats")
	@Expose
	private Seat[] seats;
	@SerializedName("Time")
	@Expose
	private String time;
	@SerializedName("Day")
	@Expose
	private String day;
	@SerializedName("Movie")
	@Expose
	private Movie movie;
	@SerializedName("Room")
	@Expose
	private Room room;
	
	public ScheduledMovie(String time, String day, Movie movie, Room room)
	{
		this.time = time;
		this.day = day;
		this.movie = movie;
		this.room = room;
		if(room != null)
		{
		this.seats = new Seat[room.getSize()];
		for(int i = 0; i < room.getSize(); i++)
		{
			seats[i] = new Seat(false);
		}
		}
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}
