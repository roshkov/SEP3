package common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Seat {

	@SerializedName("Booked")
	@Expose
	private boolean booked = false;

	public Seat(boolean booked) {
		this.booked = booked;
	}
	public Seat() {
		booked = false;
	}
}
