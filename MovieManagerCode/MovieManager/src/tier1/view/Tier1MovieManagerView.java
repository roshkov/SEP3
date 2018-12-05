package tier1.view;

import tier1.controller.Tier1MovieManagerController;

public interface Tier1MovieManagerView {
	
	String get();

	void showMovies(String text);

	void showError();
	
	void startView(Tier1MovieManagerController controller);
}
