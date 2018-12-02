package tier1.view;

import tier1.controller.Tier1MovieSchedulerController;

public interface Tier1MovieSchedulerView {

	void showRooms(String text); //displays the Room related information
	
	void showMovies(String text); //displays the Movies related information

	void showSchedule(String text); //displays the Schedule related information
	
	void showTime(String text); //displays the list of available days and times

	void startView(Tier1MovieSchedulerController controller);

}
