package tier1.view;

import java.util.Scanner;

import tier1.controller.Tier1MovieSchedulerController;

public class Tier1MovieSchedulerConsole implements Tier1MovieSchedulerView, Runnable {

	private Scanner input;
	private Tier1MovieSchedulerController controller;

	public Tier1MovieSchedulerConsole() {
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
	public void startView(Tier1MovieSchedulerController controller) {
		this.controller = controller;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		boolean continueWorking = true;
		while (continueWorking) {
			// Read input from user input.

			System.out.println("1) Create Schedule");
			System.out.println("2) Create Room");
			System.out.println("3) Get Rooms");
			System.out.println("4) Delete Room");
			System.out.println("5) Get Schedule");
			System.out.println("0) Quit");
			System.out.print("\nSelect: ");

			int choice = input.nextInt();
			input.nextLine();

			controller.execute(choice);
			if (choice == 0) {
				continueWorking = false;
			}
		}
	}
}
