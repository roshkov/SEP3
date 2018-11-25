package tier1;

import tier1.controller.Tier1MovieSchedulerController;
import tier1.view.Tier1MovieSchedulerConsole;
import tier1.view.Tier1MovieSchedulerView;

public class Tier1MovieScheduler {
	public static void main(String[] args) 
	{	//view
		Tier1MovieSchedulerView view = new Tier1MovieSchedulerConsole();					
		//controller
		Tier1MovieSchedulerController controller = new Tier1MovieSchedulerController(view); 
		//starts thread in it
		view.startView(controller);   
	}
}
