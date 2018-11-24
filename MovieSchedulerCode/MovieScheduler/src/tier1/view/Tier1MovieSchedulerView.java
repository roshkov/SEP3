package tier1.view;

import tier1.controller.Tier1MovieSchedulerController;

public interface Tier1MovieSchedulerView {
	
	String get(String string);

	void show(String text);

	void startView(Tier1MovieSchedulerController controller);
	
}
