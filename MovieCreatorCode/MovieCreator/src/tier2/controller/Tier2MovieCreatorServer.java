package tier2.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import tier2.view.Tier2MovieCreatorView;

public class Tier2MovieCreatorServer implements Runnable {
	
	private ServerSocket welcomeSocket;
	private Tier2MovieCreatorView view;
	private Tier2MovieCreatorController controller;
	
	//Haven't decided if we use observer pattern or not, using view instead of model.
	public Tier2MovieCreatorServer(int port, Tier2MovieCreatorView view, Tier2MovieCreatorController controller) throws IOException
	{
		super();
		this.view = view;
		this.welcomeSocket = new ServerSocket(port);
		this.controller = controller;
	}

	@Override
	public void run() {
		while (true)
	      {
	         view.show("Waiting for a client...");
	         try
	         {
	            Socket socket = welcomeSocket.accept();
	            Tier2MovieCreatorThreadHandler c;
	            c = new Tier2MovieCreatorThreadHandler(socket, view, controller);
	            Thread t = new Thread(c);
	            t.start();
	         }
	         catch (IOException e)
	         {
	            view.show("Error in server. Message: " + e.getMessage());
	         }
	      }
	}

}
