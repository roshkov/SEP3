package tier3.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import tier2.controller.Tier2MovieCreatorThreadHandler;
import tier3.controller.Tier3MovieCreatorController;

public class Tier3MovieCreatorServer implements Runnable {
	
	private ServerSocket welcomeSocket;
	private Tier3MovieCreatorController controller;

	public Tier3MovieCreatorServer(int port, Tier3MovieCreatorController controller) throws IOException {
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
	            Tier3MovieCreatorThreadHandler c;
	            c = new Tier3MovieCreatorThreadHandler(socket, controller.getView(), controller.getDatabase());
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
