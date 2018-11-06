package tier2.view;

import tier2.controller.Tier2MovieCreatorController;

public interface Tier2MovieCreatorView {
	String get(String string);

	void show(String text);

	void startView(Tier2MovieCreatorController controller);
}
