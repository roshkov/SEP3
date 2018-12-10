package tier2.view;
/**
 * Used to display things like error messages in the console
 * @author Stefan
 *
 */
public class Console implements Tier2MovieCreatorView {

	public Console() {
		
	}

	@Override
	public void show(String text) {
		System.out.println(text);

	}

}
