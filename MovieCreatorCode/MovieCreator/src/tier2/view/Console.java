package tier2.view;

import java.util.Scanner;

import tier2.controller.Tier2MovieCreatorController;

public class Console implements Tier2MovieCreatorView, Runnable{

	
	private Scanner input;
	private Tier2MovieCreatorController controller;
	
	public Console()
	{
		input = new Scanner(System.in);
	}
	@Override
	public String get(String text) {
		System.out.print("Enter " + text + ": ");
		return input.nextLine();
	}

	@Override
	public void show(String text) {
		System.out.println(text);
		
	}

	@Override
	public void startView(Tier2MovieCreatorController controller) {
		this.controller = controller;
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run()
	{
		boolean continueWorking = true;
		while (continueWorking)
		{
			// Read input from user input.
			System.out.println("Movie Creator Server");
			System.out.println("--------------");
			System.out.println("0) Terminate Server");
			System.out.println("1) Check movies from db");
			System.out.print("Select an item 0-1: ");
			
			int choice = input.nextInt();
			input.nextLine();

			controller.execute(choice);
			if (choice == 0) {
				continueWorking = false;
		}
	}
	}
}

