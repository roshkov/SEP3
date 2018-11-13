package tier1;

import tier1.controller.Tier1MovieCreatorController;
import tier1.view.Tier1MovieCreatorConsole;
import tier1.view.Tier1MovieCreatorView;





public class Tier1MovieCreator {

	
	public static void main(String[] args) 
	{	//view
		Tier1MovieCreatorView view = new Tier1MovieCreatorConsole();					
		//controller
		Tier1MovieCreatorController controller = new Tier1MovieCreatorController(view); 
		//starts thread in it
		view.startView(controller);   
	}
}
