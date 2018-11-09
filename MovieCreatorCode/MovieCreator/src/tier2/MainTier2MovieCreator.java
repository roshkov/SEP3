package tier2;

import java.io.IOException;
import java.rmi.RemoteException;

import Common.Tier3MovieCreatorControllerInterface;
import Tier3.controller.Tier3MovieCreatorController;
import tier2.controller.Tier2MovieCreatorController;
import tier2.controller.Tier2MovieCreatorServer;
import tier2.model.Tier2MovieCreatorModel;
import tier2.view.Console;
import tier2.view.Tier2MovieCreatorView;

public class MainTier2MovieCreator {
	public static void main(String[] args) throws RemoteException
	{
		Tier2MovieCreatorModel model = new Tier2MovieCreatorModel();
		Tier2MovieCreatorView view = new Console();
		Tier3MovieCreatorControllerInterface tier3Controller = new Tier3MovieCreatorController();
		
		Tier2MovieCreatorController controller = new Tier2MovieCreatorController(model, view, tier3Controller);
		
		view.startView(controller);
		
		//Start server here so that when Tier 1 uses Tier2 controller u don't start new server everytime.
		try {
			Tier2MovieCreatorServer server = new Tier2MovieCreatorServer(1099, view, controller);
			Thread t = new Thread(server);
			t.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
