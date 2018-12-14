package tier2.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Tier2MovieCreatorServer implements Runnable {
	
	private ServerSocket welcomeSocket;
	private Tier2MovieCreatorController controller;

	public Tier2MovieCreatorServer(int port, Tier2MovieCreatorController controller) throws IOException {
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
	            Tier2MovieCreatorThreadHandler c;
	            c = new Tier2MovieCreatorThreadHandler(socket, controller.getView());
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
