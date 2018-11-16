package tier1;

import tier1.controller.Tier1MovieManagerController;
import tier1.view.Tier1MovieManagerConsole;
import tier1.view.Tier1MovieManagerView;





public class Tier1MovieManager {

	
	public static void main(String[] args) 
	{	//view
		Tier1MovieManagerView view = new Tier1MovieManagerConsole();					
		//controller
		Tier1MovieManagerController controller = new Tier1MovieManagerController(view); 
		//starts thread in it
		view.startView(controller);   
	}
}
