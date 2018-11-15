package tier1.view;

import tier1.controller.Tier1MovieManagerController;

public interface Tier1MovieManagerView {
	
	String get(String string);

	void show(String text);

	void startView(Tier1MovieManagerController controller);
	
}
