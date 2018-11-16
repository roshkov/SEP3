package tier2.view;

public class Console implements Tier2MovieManagerView {

	public Console() {
	}

	@Override
	public void show(String text) {
		System.out.println(text);

	}
}
