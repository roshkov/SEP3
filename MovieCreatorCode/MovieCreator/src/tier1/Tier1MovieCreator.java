package tier1;


import common.Init;
import tier1.controller.Tier1MovieCreatorController;
import tier1.view.Tier1MovieCreatorGUI;
import tier1.view.Tier1MovieCreatorView;





public class Tier1MovieCreator {

	
	public static void main(String[] args) 
	{	//Init
		Init.getInstance().getData();
		//view
		Tier1MovieCreatorView view = new Tier1MovieCreatorGUI();					
		//controller
		Tier1MovieCreatorController controller = new Tier1MovieCreatorController(view); 
		//starts thread in it
		view.startView(controller);   
	}
}
