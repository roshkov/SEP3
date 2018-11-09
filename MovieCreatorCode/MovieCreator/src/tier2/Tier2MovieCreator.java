package tier2;

import tier2.controller.Tier2MovieCreatorController;
import tier2.view.Tier2MovieCreatorView;

public class Tier2MovieCreator {
	public static void main(String[] args) {
		Tier2MovieCreatorView view = new Tier2MovieCreatorView();
		Tier2MovieCreatorController controller = new Tier2MovieCreatorController(view);
		Thread thread = new Thread(controller);
		thread.start();
	}
}
