package tier2.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Tier2MovieSchedulerServer implements Runnable {

	private ServerSocket welcomeSocket;
	private Tier2MovieSchedulerController controller;

	public Tier2MovieSchedulerServer(int port, Tier2MovieSchedulerController controller) throws IOException {
		super();
		this.controller = controller;
		this.welcomeSocket = new ServerSocket(port);
	}

	@Override
	public void run() {
		while (true)
	      {
	         controller.getView().show("Waiting for a client...");
	         try
	         {
	            Socket socket = welcomeSocket.accept();
	            Tier2MovieSchedulerThreadHandler c;
	            c = new Tier2MovieSchedulerThreadHandler(socket, controller.getView());
	            Thread t = new Thread(c);
	            t.start();
	            controller.getView().show("Client connected");
	         }
	         catch (IOException e)
	         {
	        	 controller.getView().show("Error in server. Message: " + e.getMessage());
	         }
	      }
	}

}
