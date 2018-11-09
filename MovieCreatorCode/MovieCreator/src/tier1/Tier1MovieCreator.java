package tier1;

import tier1.controller.Tier1MovieCreatorController;
import tier1.view.Tier1MovieCreatorView;

public class Tier1MovieCreator {
	public static void main(String[] args) {
	Tier1MovieCreatorView view = new Tier1MovieCreatorView();
	Tier1MovieCreatorController controller = new Tier1MovieCreatorController(view);
	Thread thread = new Thread(controller);
	thread.start();
	}
}
