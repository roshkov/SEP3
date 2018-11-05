package Tier3.view;

public class Console implements Tier3MovieCreatorView {

	public Console()
	{
		
	}
	@Override
	public void show(String text) {
		System.out.println(text);
	}

}
