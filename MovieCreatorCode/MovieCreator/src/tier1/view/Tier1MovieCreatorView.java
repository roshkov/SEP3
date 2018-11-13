package tier1.view;

import tier1.controller.Tier1MovieCreatorController;

public interface Tier1MovieCreatorView {
	
	String get(String string);

	void show(String text);

	void startView(Tier1MovieCreatorController controller);
	
}
