package Tier3;

import Tier3.controller.Tier3MovieCreatorController;
import Tier3.view.Console;
import Tier3.view.Tier3MovieCreatorView;

public class Tier3MovieCreator {

	public static void main(String[] args) {
		Tier3MovieCreatorView view = new Console();
		//Remove Hardcoding
		Tier3MovieCreatorController controller = new Tier3MovieCreatorController(view, 1097);
		Thread thread = new Thread(controller);
		thread.start();
	}
}
