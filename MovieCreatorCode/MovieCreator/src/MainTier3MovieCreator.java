

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import Common.Tier3MovieCreatorControllerInterface;
import Tier3.controller.Tier3MovieCreatorController;
import Tier3.view.Console;
import Tier3.view.Tier3MovieCreatorView;


public class MainTier3MovieCreator {

	public static void main(String[] args) {
		//Set up your own path for the policy file. It is needed in the future in case components from different projects will communicate
		// We might need to work with it to make it more specific because now the only thing it does is to allow any connection
		System.setProperty("java.security.policy", "file:C:\\Users\\Claudiu\\Desktop\\Programming\\SEP3.policy");
		try {
			 if (System.getSecurityManager() == null) {
			        System.setSecurityManager(new SecurityManager());
			    }
			Tier3MovieCreatorController controller = new Tier3MovieCreatorController();
			Tier3MovieCreatorView view = new Console();
			controller.setView(view);

			LocateRegistry.createRegistry(1099);
			Naming.rebind(Tier3MovieCreatorControllerInterface.SERVICE_NAME, controller);

			view.show("Database Server is running...");
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
