package tier3;

import tier3.controller.Tier3MovieCreatorController;
import tier3.view.Console;
import tier3.view.Tier3MovieCreatorView;

public class Tier3MovieCreator {

	public static void main(String[] args) {
		Tier3MovieCreatorView view = new Console();
		//Remove Hardcoding
		Tier3MovieCreatorController controller = new Tier3MovieCreatorController(view, 1097);
	}
}
