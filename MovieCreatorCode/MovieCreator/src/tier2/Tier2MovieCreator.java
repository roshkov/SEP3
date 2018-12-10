package tier2;

import java.io.IOException;
import java.net.UnknownHostException;

import common.Init;
import tier2.controller.Tier2MovieCreatorController;
import tier2.view.Console;
import tier2.view.Tier2MovieCreatorView;

public class Tier2MovieCreator {

	public static void main(String[] args) throws UnknownHostException, IOException {

		//Init
		Init.getInstance().getData();
		
		Tier2MovieCreatorView view = new Console();
		Tier2MovieCreatorController controller = new Tier2MovieCreatorController(view);
	}

}
